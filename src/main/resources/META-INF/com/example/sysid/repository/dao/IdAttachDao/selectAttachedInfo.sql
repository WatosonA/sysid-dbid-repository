/**
 * 他システムID紐付けテーブルから店舗IDとクライアントユーザIDを取得
 */
SELECT
-- $Id: selectAttachedInfo.sql 2023-07-24 11:40:52 src/main/resources/META-INF/com/example/demo/repository/dao/IdAttachDao/selectAttachedInfo.sql $
    IA.STORE_ID,
    IA.PAIR_STORE_ID,
    IA.PAIR_CLIENT_USER_ID
FROM
    ID_ATTACH IA
WHERE
    IA.PAIR_SERVICE_TYPE = 'TBL'
    AND IA.STORE_ID IN /* storeIdList */('KR00000001','KR00000002')
