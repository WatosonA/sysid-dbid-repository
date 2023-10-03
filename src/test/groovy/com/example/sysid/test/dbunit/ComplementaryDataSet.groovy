package com.example.sysid.test.dbunit

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

import org.dbunit.dataset.Column
import org.dbunit.dataset.DefaultDataSet
import org.dbunit.dataset.DefaultTable
import org.dbunit.dataset.datatype.DataType
import org.seasar.doma.Domain

import com.example.sysid.model.entity.IdAttach
import com.example.sysid.model.entity.SetValueMst
import com.example.sysid.model.enums.Flg
import com.example.sysid.model.enums.PairAccountStatus
import com.example.sysid.model.enums.PairServiceType
import com.google.common.base.CaseFormat


/**
 * データセット補完クラス.
 * 各テストケースのデータ準備で省略したカラム値を補完するためのデータ定義を司る。
 * 
 * 各テストケースではケースに関係あるカラム値のみ定義する（他は省略する）ことで可読性の向上を目的に使用する。
 * また、エンハンス等でテーブルにNotNullカラムが追加された場合もこのクラスに追記することで各テストケースでは対応不要にする。
 * @author akira.wada
 */
class ComplementaryDataSet {

    /** デフォルトデータセット */
    static DefaultDataSet defaultDataSet = new DefaultDataSet()
    static {
        LocalDateTime defaultDateTime = LocalDateTime.parse(
                "2023-01-01 01:01:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
        /*
         * defaultTableListに１レコード分のエンティティを定義
         */
        List<?> defaultTableList = Arrays.asList(
                new IdAttach().tap {
                    storeId = "KR90000001"
                    pairServiceType = PairServiceType.TBL
                    pairStoreId = "H900000001"
                    pairClientUserId = "CL999000"
                    accessToken = "at-xxxx"
                    pairAccessToken = "pat-xxxx"
                    pairTokenValidDate = LocalDateTime.parse(
                            "2023-01-01 01:01:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toLocalDate()
                    pairRefreshToken = "prt-xxxx"
                    updDate = defaultDateTime.toLocalDate()
                    updApl = "test"
                    regDate = defaultDateTime.toLocalDate()
                    regApl = "test"
                    version = 1
                    pairTokenExpiredFlg = Flg.OFF
                    pairAccountStatus = PairAccountStatus.AVAILABLE
                },
                new SetValueMst().tap {
                    setValueSeq = "0"
                    setValueKbn = "0000"
                    setValue = "0"
                    updDate = defaultDateTime.toLocalDate()
                    updApl = "test"
                    regDate = defaultDateTime.toLocalDate()
                    regApl = "test"
                    version = 1
                }
                )
        // EntityからDataSetへ変換
        defaultTableList.each {
            Map<String, Object> beanMap = describe(it)
            List<Column> colums = []
            List<?> values = []
            beanMap.entrySet().each { entry ->
                colums.add new Column(
                        CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, entry.getKey()),
                        DataType.forObject(entry.getValue()))
                values.add (entry.getValue() as Object)
            }
            DefaultTable dataTable = new DefaultTable(
                    CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, it.getClass().getSimpleName()),
                    (colums as Column[]), new ArrayList().tap { it.add (values as Object[]) } )
            defaultDataSet.addTable(dataTable)
        }
    }

    private static Map<String, ?> describe(Object obj) {
        Map<String, ?> beanMap = new HashMap<>()
        obj.getClass().getDeclaredFields().each {
            it.setAccessible(true)
            def value = it.get(obj)
            if (value != null) {
                // Doma:DomainはDB定義型対応型へ変換
                value = value.getClass().isAnnotationPresent(Domain.class) ? value.value : value
                value = value.getClass() == LocalDateTime.class // java.sql.Timestampへの変換エラーが出てしまうため個別に変換
                        ? ((LocalDateTime)value).toInstant(ZoneOffset.of("+9")).toEpochMilli() : value
                value = value.getClass() == LocalDate.class // java.sql.Timestampへの変換エラーが出てしまうため個別に変換
                        ? ((LocalDate)value).toEpochDay() : value
                beanMap.put(it.getName(), value)
            }
        }
        beanMap
    }
}
