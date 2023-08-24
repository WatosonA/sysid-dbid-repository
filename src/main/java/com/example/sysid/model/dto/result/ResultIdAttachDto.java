package com.example.sysid.model.dto.result;

import org.seasar.doma.Entity;

import lombok.Data;

/**
 * 他システムID紐付け取得結果DTO
 */
@Entity
@Data
public class ResultIdAttachDto {

    /** 店舗ID */
    private String storeId;

    /** 対向店舗ID */
    private String pairStoreId;

    /** 対向クライアントID */
    private String pairClientUserId;
}
