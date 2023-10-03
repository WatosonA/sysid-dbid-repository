package com.example.sysid.model.enums;

import org.seasar.doma.Domain;
import org.springframework.lang.NonNull;

import lombok.Getter;

/**
 * フラグ値のEnum。
 */
@Domain(valueType = String.class, factoryMethod = "of")
@Getter
public enum Flg {

    /** OFF */
    OFF("0"),
    /** ON */
    ON("1");

    /** コード */
    private String value;

    /**
     * コンストラクタ
     *
     * @param code コード
     */
    private Flg(final String value) {
        this.value = value;
    }

    public static Flg of(String code) {
        if (code == null) {
            return null;
        }
        for (Flg valueType : Flg.values()) {
            if (valueType.value.equals(code)) {
                return valueType;
            }
        }
        throw new IllegalArgumentException("存在しないEnum値です。:" + code + " " + Flg.class.getCanonicalName());
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }

}
