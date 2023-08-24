CREATE TABLE ID_ATTACH
(
    STORE_ID                        CHAR(10) NOT NULL,
    PAIR_SERVICE_TYPE               CHAR(3) NOT NULL,
    PAIR_STORE_ID                   VARCHAR(99),
    PAIR_CLIENT_USER_ID             VARCHAR(255) NOT NULL,
    ACCESS_TOKEN                    VARCHAR(100),
    PAIR_ACCESS_TOKEN               VARCHAR(255) NOT NULL,
    PAIR_TOKEN_VALID_DATE           DATE,
    PAIR_REFRESH_TOKEN              VARCHAR(255),
    UPD_DATE                        DATE NOT NULL,
    UPD_APL                         VARCHAR(200) NOT NULL,
    REG_DATE                        DATE NOT NULL,
    REG_APL                         VARCHAR(200) NOT NULL,
    VERSION                         INT UNSIGNED NOT NULL,
    PAIR_TOKEN_EXPIRED_FLG          VARCHAR(1) DEFAULT '0' NOT NULL,
    PAIR_ACCOUNT_STATUS             VARCHAR(1) DEFAULT '0' NOT NULL
)
;

CREATE TABLE SET_VALUE_MST
(
    SET_VALUE_SEQ                   VARCHAR(20) NOT NULL,
    SET_VALUE_KBN                   CHAR(4) NOT NULL,
    SET_VALUE                       VARCHAR(200) NOT NULL,
    UPD_DATE                        DATE NOT NULL,
    UPD_APL                         VARCHAR(200) NOT NULL,
    REG_DATE                        DATE NOT NULL,
    REG_APL                         VARCHAR(200) NOT NULL,
    VERSION                         INT UNSIGNED NOT NULL
)
;
