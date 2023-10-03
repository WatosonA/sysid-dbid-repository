package com.example.sysid.test.spock

import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*

import org.dbunit.database.DatabaseDataSourceConnection
import org.dbunit.operation.DatabaseOperation
import org.seasar.doma.MapKeyNamingType
import org.seasar.doma.boot.autoconfigure.DomaConfig
import org.seasar.doma.jdbc.builder.SelectBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.lang.NonNull

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
    @Autowired
    DomaConfig config

    /**
     * クロージャの内容でDBに対象テーブルのデータを削除＆挿入する。
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
     */
    @SuppressWarnings("unchecked")
    protected void cleanInsert(@DelegatesTo(TableDataSpec) Closure<TableDataSpec>... tableDatas) {
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitDatabaseConnection, ClosureUtil.toDataSet(tableDatas))
    }

    /**
     * Map形式のリストをクロージャの内容で比較する。
     * @param actual 実際の値
     * @param expected 期待値データ表現のクロージャ
     * <pre>
     * クロージャの形式：
     * <code>
     * { // １行目はフィールド名、２行目以降は値
     *     id | name    | description
     *     1  | "aaaaa" | "bbbbbbbbbbbb"
     * }
     * </code>
     * </pre>
     */
    protected void assertMapList(@NonNull List<Map<String, ?>> actual, @NonNull Closure<?> expected) {
        assert !actual.empty
        def expList = ClosureUtil.toMapList(expected)
        actual.eachWithIndex { actualMap, index ->
            def expectedMap = expList.get(index)
            assert actualMap == expectedMap
        }
    }

    /**
     * DTOなどのObjectのリストをクロージャの内容で比較する。
     * @param actual 実際の値
     * @param expected 期待値データ表現のクロージャ
     * <pre>
     * クロージャの形式：
     * <code>
     * { // １行目はフィールド名、２行目以降は値
     *     id | name    | description
     *     1  | "aaaaa" | "bbbbbbbbbbbb"
     * }
     * </code>
     * </pre>
     */
    protected void assertObjectList(@NonNull List<?> actual, @NonNull Closure<?> expected) {
        assert !actual.empty
        def expList = ClosureUtil.toMapList(expected)
        actual.eachWithIndex { act, index ->
            def actualObj = describe(act)
            def expectedObj = expList.get(index)
            assert actualObj == expectedObj
        }
    }

    /**
     * テーブルにデータが存在しないことを検証する。
     * @param tableName テーブル名
     */
    protected void assertTableNotExists(@NonNull String tableName) {
        assert dbUnitDatabaseConnection.createQueryTable(tableName, "select 1 from " + tableName).getRowCount() == 0
    }

    /**
     * テーブルのデータを期待値のエンティティと比較する。
     * @param <ENTITY> エンティティクラス
     * @param tableName テーブル名
     * @param expected 期待値のエンティティ
     * @param ignoreCols 比較しないカラム名を羅列
     */
    protected <ENTITY> void assertTable(@NonNull String tableName, @NonNull ENTITY expected, String... ignoreCols) {
        Optional<ENTITY> actual = SelectBuilder.newInstance(config).sql(
                "select * from " + tableName).getOptionalEntitySingleResult(expected.getClass())
        assert !actual.empty
        assertThat(actual.get(), samePropertyValuesAs(expected, ignoreCols))
    }

    /**
     * テーブルのデータを期待値のエンティティリストと比較する。
     * @param <ENTITY> エンティティクラス
     * @param tableName テーブル名
     * @param expected 期待値のエンティティリスト
     * @param ignoreCols 比較しないカラム名を羅列
     */
    protected <ENTITY> void assertTable(
            @NonNull String tableName, @NonNull String sortColumn, @NonNull List<ENTITY> expected, String... ignoreCols) {
        List<ENTITY> actual = SelectBuilder.newInstance(config).sql(
                "SELECT * FROM " + tableName + " ORDER BY " + sortColumn).getEntityResultList(expected.getClass())
        assert !actual.empty
        actual.eachWithIndex { act, index ->
            assertThat(act, samePropertyValuesAs(expected.get(index), ignoreCols))
        }
    }

    /**
     * テーブルのデータをクロージャの内容で比較する。
     * @param tableName テーブル名
     * @param sortColumn テーブルデータのソートカラム名（期待値と比較に順序を合わせるため）
     * @param expected 期待値データ表現のクロージャ
     * <pre>
     * クロージャの形式：
     * <code>
     * { // １行目はフィールド名、２行目以降は値
     *     id | name    | description
     *     1  | "aaaaa" | "bbbbbbbbbbbb"
     * }
     * </code>
     * </pre>
     * @param ignoreCols 比較しないカラム名を羅列
     */
    protected void assertTable(
            @NonNull String tableName, @NonNull String sortColumn, @NonNull Closure<?> expected, String... ignoreCols) {
        List<Map<String, Object>> actual = SelectBuilder.newInstance(config).sql(
                "SELECT * FROM " + tableName + " ORDER BY " + sortColumn).getMapResultList(MapKeyNamingType.NONE)
        assert !actual.empty
        def expList = ClosureUtil.toMapList(expected)
        actual.eachWithIndex { act, index ->
            if (ignoreCols != null) ignoreCols.each { act.remove(it) }
            def expectedObj = expList.get(index)
            assert act == expectedObj
        }
    }

    /**
     * JavaBeansをMap形式に変換する。
     * @param obj 変換対象のオブジェクト
     * @return 変換されたMapオブジェクト
     */
    protected Map<String, ?> describe(@NonNull Object obj) {
        Map<String, ?> beanMap = new HashMap<>()
        obj.getClass().getDeclaredFields().each {
            it.setAccessible(true)
            def value = it.get(obj)
            if (value != null) {
                beanMap.put(it.getName(), value)
            }
        }
        beanMap
    }
}
