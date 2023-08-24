package com.example.sysid.test.spock

import org.apache.commons.beanutils.BeanUtils
import org.dbunit.database.DatabaseDataSourceConnection
import org.dbunit.operation.DatabaseOperation
import org.springframework.beans.factory.annotation.Autowired

import com.example.sysid.test.dbunit.ClosureUtil
import com.example.sysid.test.dbunit.ClosureUtil.TableDataSpec

import spock.lang.Specification

/**
 * Spock+Groovyテストの拡張クラス.
 * @author akira.wada
 */
abstract class CustomSpecification extends Specification {

    @Autowired
    DatabaseDataSourceConnection dbUnitDatabaseConnection

    /**
     * クロージャの内容でDBに対象テーブルのデータを削除＆挿入する。
     * @param closure テーブルデータ表現のクロージャ
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
     */
    @SuppressWarnings("unchecked")
    protected void cleanInsert(@DelegatesTo(TableDataSpec) Closure<TableDataSpec>... tableDatas) {
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitDatabaseConnection, ClosureUtil.toDataSet(tableDatas))
    }

    /**
     * Map形式のリストをクロージャの内容で比較する。
     * @param actual 実際の値
     * @param expected 期待値
     */
    protected void assertMapList(List<Map<String, ?>> actual, Closure<?> expected) {
        def expList = ClosureUtil.toMapList(expected)
        actual.eachWithIndex { actualMap, index ->
            def expectedMap = expList.get(index)
            assert actualMap == expectedMap
        }
    }

    /**
     * DTOなどのObjectのリストをクロージャの内容で比較する。
     * @param actual 実際の値
     * @param expected 期待値
     */
    protected void assertObjectList(List<?> actual, Closure<?> expected) {
        def expList = ClosureUtil.toMapList(expected)
        actual.eachWithIndex { act, index ->
            def actualObj = BeanUtils.describe(act)
            def expectedObj = expList.get(index)
            assert actualObj == expectedObj
        }
    }
}
