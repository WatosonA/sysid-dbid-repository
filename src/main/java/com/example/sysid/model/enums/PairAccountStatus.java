package com.example.sysid.model.enums;

import org.seasar.doma.Domain;
import org.springframework.lang.NonNull;

/**
 * 対向アカウントステータス
 */
@Domain(factoryMethod = "of", valueType = String.class, accessorMethod = "getCode")
public enum PairAccountStatus {

    AVAILABLE("0", "利用可能"),
    AUTH_ERROR("1", "認証エラー");

    private String code;
    private String display;

    private PairAccountStatus(String code, String display) {
        this.code = code;
        this.display = display;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getDisplay() {
        return display;
    }

    @NonNull
    public String getName() {
        return name();
    }

    @NonNull
    @Override
    public String toString() {
        return code;
    }

    @NonNull
    public static PairAccountStatus of(String code) {
        for (PairAccountStatus value : PairAccountStatus.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }

        throw new IllegalArgumentException(code);
    }

}
