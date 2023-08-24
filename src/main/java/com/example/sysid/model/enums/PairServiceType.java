package com.example.sysid.model.enums;

import org.seasar.doma.Domain;
import org.springframework.lang.NonNull;

/**
 * 対向サービス区分
 */
@Domain(factoryMethod = "of", valueType = String.class, accessorMethod = "getCode")
public enum PairServiceType {

    TBL("TBL", "食べログ"),
    GRV("GRV", "ぐるなび");

    private String code;
    private String display;

    private PairServiceType(String code, String display) {
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
    public static PairServiceType of(String code) {
        for (PairServiceType value : PairServiceType.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }

        throw new IllegalArgumentException(code);
    }

}
