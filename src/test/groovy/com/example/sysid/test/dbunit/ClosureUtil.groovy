package com.example.sysid.test.dbunit

import org.dbunit.dataset.Column
import org.dbunit.dataset.DefaultDataSet
import org.dbunit.dataset.DefaultTable
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.datatype.DataType

import groovy.transform.ToString

/**
 * Groovy Closureを使用したMarkdownライクなデータ定義を実現するユーティリティ.
 * @author akira.wada
 */
class ClosureUtil {

    /**
     * クロージャの内容からデータセットを生成.
     * @param tableDatas テーブルデータ表現のクロージャ
     * <pre>
     * クロージャの形式：
     * <code>
     * def dataTable = {
     *     table 'SAMPLE_TABLE' // テーブル名
     *     rows {   // １行目はカラム、２行目以降は値
     *         ID | NAME    | DESCRIPTION
     *         1  | "aaaaa" | "bbbbbbbbbbbb"
     *     }
     * }
     * </code>
     * </pre>
     * @return データセット
     */
    @SuppressWarnings("unchecked")
    static IDataSet toDataSet(@DelegatesTo(TableDataSpec) Closure<TableDataSpec>... tableDatas) {
        DefaultDataSet dataset = new DefaultDataSet()
        toDataSet(dataset, tableDatas)
    }

    @SuppressWarnings("unchecked")
    static IDataSet toDataSet(DefaultDataSet dataset, @DelegatesTo(TableDataSpec) Closure<TableDataSpec>... tableDatas) {
        Map<String, List> tableDataMap = [:]
        tableDatas.each {
            TableDataSpec spec = new TableDataSpec()
            def handler = it.rehydrate(spec, this, this)
            handler.resolveStrategy = Closure.DELEGATE_ONLY
            handler()
            tableDataMap.put(spec.tableName, spec.rows)
        }
        tableDataMap.each { tableName, rows ->
            Column[] columns = rows.head().values
            List<Column> addedColumnList = new ArrayList(Arrays.asList(columns))
            List<List<?>> addedValueList = []
            rows.tail().each { Row row ->
                addedValueList.add row.values
            }
            DefaultTable defaultTable = ComplementaryDataSet.defaultDataSet.getTable(tableName)
            defaultTable.getTableMetaData().getColumns().eachWithIndex { Column defaultColumn, columnIndex ->
                // クロージャで定義されていないカラム値はデフォルトデータセットから補完
                if (!(columns.any() { it.columnName == defaultColumn.columnName })) {
                    addedColumnList.add(defaultColumn)
                    addedValueList.each { rowValueList ->
                        rowValueList.add(defaultTable.getValue(0, defaultColumn.columnName))
                    }
                }
            }
            DefaultTable dataTable = new DefaultTable(tableName, (addedColumnList as Column[]))
            addedValueList.each {
                dataTable._rowList.add(it as Object[])
            }
            dataset.addTable(dataTable)
        }
        dataset
    }

    /**
     * クロージャの内容からMapListを生成.
     * @param data データ表現のクロージャ
     * <pre>
     * クロージャの形式：
     * <code>
     * def data = {
     *     storeId      | StoreName    | updDate
     *     "KR00000000" | "aaaaa"      | "2023-07-31 00:00:00"
     * }
     * </code>
     * </pre>
     * @return マップリスト
     */
    static List<Map<String, Object>> toMapList(Closure<?> data) {
        List<Map<String, Object>> mapList = []
        List<Row> dataList = ClosureTableParser.asListOfRowsNormal(data)
        List<Column> colmunList = dataList.head().values
        dataList.tail().each { Row rowData ->
            Map<String, Object> rowMap = [:]
            colmunList.eachWithIndex { String column, columnIndex ->
                rowMap.put(column, rowData.values.get(columnIndex))
            }
            mapList.add(rowMap)
        }
        mapList
    }

    static class TableDataSpec {
        String tableName
        List<Row> rows
        void table(String tableName) {
            this.tableName = tableName
        }
        void rows(Closure<?> rows) {
            this.rows = ClosureTableParser.asListOfRows(rows)
        }
    }

    static class ClosureTableParser {

        private static ThreadLocal<List> context = new ThreadLocal<>()

        static or(self, arg) {
            appendRow(self, arg)
        }

        static or(Integer self, Integer arg) {
            appendRow(self, arg)
        }

        static or(Boolean self, Boolean arg) {
            appendRow(self, arg)
        }

        static appendRow(value, nextValue) {
            Row row = new Row(values: [value])
            context.get().add(row)
            row.or(nextValue)
        }

        static asListOfRows(Closure tableData) {
            context.set([])
            use(ClosureTableParser) {
                tableData.delegate = new PropertyColumnConverter()
                tableData.resolveStrategy = Closure.DELEGATE_FIRST
                tableData()
            }
            context.get()
        }
        static asListOfRowsNormal(Closure tableData) {
            context.set([])
            use(ClosureTableParser) {
                tableData.delegate = new PropertyDefaultConverter()
                tableData.resolveStrategy = Closure.DELEGATE_FIRST
                tableData()
            }
            context.get()
        }
    }

    static class PropertyColumnConverter {
        Column getProperty(String property) {
            new Column(property, DataType.UNKNOWN)
        }
    }

    static class PropertyDefaultConverter {
        String getProperty(String property) {
            property
        }
    }

    @ToString
    static class Row {
        List values = []

        Row or(arg) {
            values.add(arg)
            this
        }
    }
}
