package com.example.sysid.model.enums;

import org.seasar.doma.Domain;
import org.springframework.lang.NonNull;

import lombok.Getter;

/**
 * 対向アカウントステータス
 */
@Domain(factoryMethod = "of", valueType = String.class)
@Getter
public enum PairAccountStatus {

    AVAILABLE("0", "利用可能"),
    AUTH_ERROR("1", "認証エラー");

    private String value;
    private String display;

    private PairAccountStatus(String value, String display) {
        this.value = value;
        this.display = display;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }

    @NonNull
    public static PairAccountStatus of(String code) {
        for (PairAccountStatus value : PairAccountStatus.values()) {
            if (value.value.equals(code)) {
                return value;
            }
        }

        throw new IllegalArgumentException(code);
    }

}
