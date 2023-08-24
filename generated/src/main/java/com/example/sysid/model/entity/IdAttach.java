package com.example.sysid.model.entity;

import com.example.sysid.model.enums.Flg;
import com.example.sysid.model.enums.PairAccountStatus;
import com.example.sysid.model.enums.PairServiceType;
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
@Table(name = "ID_ATTACH")
public class IdAttach extends AbstractIdAttach {

    /**  */
    @Column(name = "STORE_ID")
    String storeId;

    /**  */
    @Column(name = "PAIR_SERVICE_TYPE")
    PairServiceType pairServiceType;

    /**  */
    @Column(name = "PAIR_STORE_ID")
    String pairStoreId;

    /**  */
    @Column(name = "PAIR_CLIENT_USER_ID")
    String pairClientUserId;

    /**  */
    @Column(name = "ACCESS_TOKEN")
    String accessToken;

    /**  */
    @Column(name = "PAIR_ACCESS_TOKEN")
    String pairAccessToken;

    /**  */
    @Column(name = "PAIR_TOKEN_VALID_DATE")
    LocalDate pairTokenValidDate;

    /**  */
    @Column(name = "PAIR_REFRESH_TOKEN")
    String pairRefreshToken;

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

    /**  */
    @Column(name = "PAIR_TOKEN_EXPIRED_FLG")
    Flg pairTokenExpiredFlg;

    /**  */
    @Column(name = "PAIR_ACCOUNT_STATUS")
    PairAccountStatus pairAccountStatus;

    /** 
     * Returns the storeId.
     * 
     * @return the storeId
     */
    public String getStoreId() {
        return storeId;
    }

    /** 
     * Sets the storeId.
     * 
     * @param storeId the storeId
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /** 
     * Returns the pairServiceType.
     * 
     * @return the pairServiceType
     */
    public PairServiceType getPairServiceType() {
        return pairServiceType;
    }

    /** 
     * Sets the pairServiceType.
     * 
     * @param pairServiceType the pairServiceType
     */
    public void setPairServiceType(PairServiceType pairServiceType) {
        this.pairServiceType = pairServiceType;
    }

    /** 
     * Returns the pairStoreId.
     * 
     * @return the pairStoreId
     */
    public String getPairStoreId() {
        return pairStoreId;
    }

    /** 
     * Sets the pairStoreId.
     * 
     * @param pairStoreId the pairStoreId
     */
    public void setPairStoreId(String pairStoreId) {
        this.pairStoreId = pairStoreId;
    }

    /** 
     * Returns the pairClientUserId.
     * 
     * @return the pairClientUserId
     */
    public String getPairClientUserId() {
        return pairClientUserId;
    }

    /** 
     * Sets the pairClientUserId.
     * 
     * @param pairClientUserId the pairClientUserId
     */
    public void setPairClientUserId(String pairClientUserId) {
        this.pairClientUserId = pairClientUserId;
    }

    /** 
     * Returns the accessToken.
     * 
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /** 
     * Sets the accessToken.
     * 
     * @param accessToken the accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /** 
     * Returns the pairAccessToken.
     * 
     * @return the pairAccessToken
     */
    public String getPairAccessToken() {
        return pairAccessToken;
    }

    /** 
     * Sets the pairAccessToken.
     * 
     * @param pairAccessToken the pairAccessToken
     */
    public void setPairAccessToken(String pairAccessToken) {
        this.pairAccessToken = pairAccessToken;
    }

    /** 
     * Returns the pairTokenValidDate.
     * 
     * @return the pairTokenValidDate
     */
    public LocalDate getPairTokenValidDate() {
        return pairTokenValidDate;
    }

    /** 
     * Sets the pairTokenValidDate.
     * 
     * @param pairTokenValidDate the pairTokenValidDate
     */
    public void setPairTokenValidDate(LocalDate pairTokenValidDate) {
        this.pairTokenValidDate = pairTokenValidDate;
    }

    /** 
     * Returns the pairRefreshToken.
     * 
     * @return the pairRefreshToken
     */
    public String getPairRefreshToken() {
        return pairRefreshToken;
    }

    /** 
     * Sets the pairRefreshToken.
     * 
     * @param pairRefreshToken the pairRefreshToken
     */
    public void setPairRefreshToken(String pairRefreshToken) {
        this.pairRefreshToken = pairRefreshToken;
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

    /** 
     * Returns the pairTokenExpiredFlg.
     * 
     * @return the pairTokenExpiredFlg
     */
    public Flg getPairTokenExpiredFlg() {
        return pairTokenExpiredFlg;
    }

    /** 
     * Sets the pairTokenExpiredFlg.
     * 
     * @param pairTokenExpiredFlg the pairTokenExpiredFlg
     */
    public void setPairTokenExpiredFlg(Flg pairTokenExpiredFlg) {
        this.pairTokenExpiredFlg = pairTokenExpiredFlg;
    }

    /** 
     * Returns the pairAccountStatus.
     * 
     * @return the pairAccountStatus
     */
    public PairAccountStatus getPairAccountStatus() {
        return pairAccountStatus;
    }

    /** 
     * Sets the pairAccountStatus.
     * 
     * @param pairAccountStatus the pairAccountStatus
     */
    public void setPairAccountStatus(PairAccountStatus pairAccountStatus) {
        this.pairAccountStatus = pairAccountStatus;
    }
}
