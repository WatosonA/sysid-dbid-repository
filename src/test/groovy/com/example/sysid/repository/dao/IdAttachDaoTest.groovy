package com.example.sysid.repository.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.transaction.annotation.Transactional

import com.example.sysid.TestConfigration
import com.example.sysid.test.spock.CustomSpecification
import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener

import spock.lang.Stepwise
import spock.lang.Unroll

/**
 * Groovy+Spock+DBUnitを使ったDaoテストサンプル.
 * 
 * データの準備やAssertでMarkdownのテーブルのような書き方をClosureで実現しており、
 * データがCSVなど外だしにならないことでテストメソッドで完結させて可読性を向上させている。
 * また、データ準備ではテストケースに関係のないデータをUtilで補完させることでテストケースをシンプルに見やすくしている。
 * EXCELやCSV方式よりも処理速度が早いがカラム数の多いテーブルを扱う場合はtap+Dao.insertで実装したほうが見やすい。
 * @see https://spock-framework-reference-documentation-ja.readthedocs.io/ja/latest/index.html
 * @author akira.wada
 */
@SpringBootTest
@Import(TestConfigration.class)
@Transactional
@Rollback
@Stepwise
@Unroll
@TestExecutionListeners(
[ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class ]
)
class IdAttachDaoTest extends CustomSpecification {

    @Autowired
    IdAttachDao target


    def "selectAttachedInfo_パラメータ１件、DB０件"() {
        setup:
        def dataTable = {
            table 'ID_ATTACH'
            rows {
                STORE_ID     | PAIR_SERVICE_TYPE | PAIR_STORE_ID | PAIR_CLIENT_USER_ID
                "KR00000000" | "TBL"             | "H000000000"  | "CL00000"
                "KR00000002" | "TBL"             | "H000000002"  | "CL00002"
            }
        }
        cleanInsert(dataTable)
        def param = Arrays.asList("KR00000001")

        when:
        def actual = target.selectAttachedInfo(param)

        then:
        assert actual.size() == 0
    }

    def "selectAttachedInfo_パラメータ１件、DB１件"() {
        setup:
        cleanInsert( {
            table 'ID_ATTACH'
            rows {
                STORE_ID     | PAIR_SERVICE_TYPE | PAIR_STORE_ID | PAIR_CLIENT_USER_ID
                "KR00000000" | "TBL"             | "H000000000"  | "CL00000"
                "KR00000002" | "TBL"             | "H000000002"  | "CL00002"
            }
        }, {
            table 'SET_VALUE_MST' // テストケースとは関係ないが、複数テーブルを準備する例として。
            rows {
                SET_VALUE_SEQ | SET_VALUE_KBN | SET_VALUE
                "1"           | "0001"        | "1"
            }
        }
        )
        def param = Arrays.asList("KR00000002")

        when:
        def actual = target.selectAttachedInfo(param)

        then:
        assert actual.size() == 1
        assertObjectList(actual, {
            storeId      | pairStoreId  | pairClientUserId
            "KR00000002" | "H000000002" | "CL00002"
        })
    }
}
