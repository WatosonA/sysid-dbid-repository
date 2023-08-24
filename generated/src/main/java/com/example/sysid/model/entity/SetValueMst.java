package com.example.sysid.model.entity;

import java.time.LocalDate;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * 
 */
@Entity(metamodel = @Metamodel)
@Table(name = "SET_VALUE_MST")
public class SetValueMst extends AbstractSetValueMst {

    /**  */
    @Column(name = "SET_VALUE_SEQ")
    String setValueSeq;

    /**  */
    @Column(name = "SET_VALUE_KBN")
    String setValueKbn;

    /**  */
    @Column(name = "SET_VALUE")
    String setValue;

    /**  */
    @Column(name = "UPD_DATE")
    LocalDate updDate;

    /**  */
    @Column(name = "UPD_APL")
    String updApl;

    /**  */
    @Column(name = "REG_DATE")
    LocalDate regDate;

    /**  */
    @Column(name = "REG_APL")
    String regApl;

    /**  */
    @Version
    @Column(name = "VERSION")
    Long version;

    /** 
     * Returns the setValueSeq.
     * 
     * @return the setValueSeq
     */
    public String getSetValueSeq() {
        return setValueSeq;
    }

    /** 
     * Sets the setValueSeq.
     * 
     * @param setValueSeq the setValueSeq
     */
    public void setSetValueSeq(String setValueSeq) {
        this.setValueSeq = setValueSeq;
    }

    /** 
     * Returns the setValueKbn.
     * 
     * @return the setValueKbn
     */
    public String getSetValueKbn() {
        return setValueKbn;
    }

    /** 
     * Sets the setValueKbn.
     * 
     * @param setValueKbn the setValueKbn
     */
    public void setSetValueKbn(String setValueKbn) {
        this.setValueKbn = setValueKbn;
    }

    /** 
     * Returns the setValue.
     * 
     * @return the setValue
     */
    public String getSetValue() {
        return setValue;
    }

    /** 
     * Sets the setValue.
     * 
     * @param setValue the setValue
     */
    public void setSetValue(String setValue) {
        this.setValue = setValue;
    }

    /** 
     * Returns the updDate.
     * 
     * @return the updDate
     */
    public LocalDate getUpdDate() {
        return updDate;
    }

    /** 
     * Sets the updDate.
     * 
     * @param updDate the updDate
     */
    public void setUpdDate(LocalDate updDate) {
        this.updDate = updDate;
    }

    /** 
     * Returns the updApl.
     * 
     * @return the updApl
     */
    public String getUpdApl() {
        return updApl;
    }

    /** 
     * Sets the updApl.
     * 
     * @param updApl the updApl
     */
    public void setUpdApl(String updApl) {
        this.updApl = updApl;
    }

    /** 
     * Returns the regDate.
     * 
     * @return the regDate
     */
    public LocalDate getRegDate() {
        return regDate;
    }

    /** 
     * Sets the regDate.
     * 
     * @param regDate the regDate
     */
    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    /** 
     * Returns the regApl.
     * 
     * @return the regApl
     */
    public String getRegApl() {
        return regApl;
    }

    /** 
     * Sets the regApl.
     * 
     * @param regApl the regApl
     */
    public void setRegApl(String regApl) {
        this.regApl = regApl;
    }

    /** 
     * Returns the version.
     * 
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /** 
     * Sets the version.
     * 
     * @param version the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}
