package com.example.sysid.model.enums;

import org.seasar.doma.Domain;
import org.springframework.lang.NonNull;

import lombok.Getter;

/**
 * 対向サービス区分
 */
@Domain(factoryMethod = "of", valueType = String.class)
@Getter
public enum PairServiceType {

    TBL("TBL", "食べログ"),
    GRV("GRV", "ぐるなび");

    private String value;
    private String display;

    private PairServiceType(String value, String display) {
        this.value = value;
        this.display = display;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }

    @NonNull
    public static PairServiceType of(String code) {
        for (PairServiceType value : PairServiceType.values()) {
            if (value.value.equals(code)) {
                return value;
            }
        }

        throw new IllegalArgumentException(code);
    }

}
