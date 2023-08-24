package com.example.sysid.repository.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.sysid.model.dto.result.ResultIdAttachDto;

import jakarta.annotation.Nonnull;

/**
 * 他システムID紐付けDao
 */
@ConfigAutowireable
@Dao
public interface IdAttachDao {

    /**
     * 他システム紐付けされた店舗の各種IDを取得.
     * @param storeIdList 店舗IDリスト
     * @return 他システム紐付け情報取得結果リスト
     */
    @Nonnull
    @Select
    List<ResultIdAttachDto> selectAttachedInfo(@Nonnull List<String> storeIdList);

}
