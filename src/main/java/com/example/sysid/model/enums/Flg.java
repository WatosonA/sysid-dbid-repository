package com.example.sysid.model.enums;

import org.seasar.doma.Domain;
import org.springframework.lang.NonNull;

/**
 * フラグ値のEnum。
 */
@Domain(valueType = String.class, factoryMethod = "of", accessorMethod = "getCode")
public enum Flg {

    /** OFF */
    OFF("0"),
    /** ON */
    ON("1");

    /** コード */
    private String code;

    /**
     * コンストラクタ
     *
     * @param code コード
     */
    private Flg(final String code) {
        this.code = code;
    }

    public static Flg of(String code) {
        if (code == null) {
            return null;
        }
        for (Flg valueType : Flg.values()) {
            if (valueType.getCode().equals(code)) {
                return valueType;
            }
        }
        throw new IllegalArgumentException("存在しないEnum値です。:" + code + " " + Flg.class.getCanonicalName());
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    @Override
    public String toString() {
        return code;
    }

}
