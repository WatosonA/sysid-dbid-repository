package com.example.sysid.test.dbunit

import java.time.ZoneId

import org.apache.commons.beanutils.BeanUtils
import org.apache.commons.lang3.time.DateUtils
import org.dbunit.dataset.Column
import org.dbunit.dataset.DefaultDataSet
import org.dbunit.dataset.DefaultTable
import org.dbunit.dataset.datatype.DataType

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
        def defaultUpdDate = DateUtils.parseDate("2023/01/01 01:01:01", "yyyy/MM/dd HH:mm:ss")
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        def defaultRegDate = DateUtils.parseDate("2023/01/01 01:01:01", "yyyy/MM/dd HH:mm:ss")
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
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
                    pairTokenValidDate = DateUtils.parseDate("2023/02/01 01:01:01", "yyyy/MM/dd HH:mm:ss")
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    pairRefreshToken = "prt-xxxx"
                    updDate = defaultUpdDate
                    updApl = "test"
                    regDate = defaultRegDate
                    regApl = "test"
                    version = 1
                    pairTokenExpiredFlg = Flg.OFF
                    pairAccountStatus = PairAccountStatus.AVAILABLE
                },
                new SetValueMst().tap {
                    setValueSeq = "0"
                    setValueKbn = "0000"
                    setValue = "0"
                    updDate = defaultUpdDate
                    updApl = "test"
                    regDate = defaultRegDate
                    regApl = "test"
                    version = 1
                }
                )
        // EntityからDataSetへ変換
        defaultTableList.each {
            Map<String, Object> beanMap = BeanUtils.describe(it)
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
}
