package com.portal.service;

import com.portal.pojo.ItemsClaim;

import java.util.List;

public interface ClaimService {
    List<ItemsClaim> getListClaim();

    int insertItemsClaim(ItemsClaim itemsClaim);

    int updateStatusByAdmin(ItemsClaim itemsClaim);

    int updateStatusByUser(ItemsClaim itemsClaim);

    ItemsClaim getClaimById(Integer itemId);

    Integer updateSearch(ItemsClaim itemsClaim);

    Integer getNumWithDay();

    Integer getNumWithWeek();

    Integer getNumWithMonth();

    List<ItemsClaim> getClaimWithDay();

    List<ItemsClaim> getClaimWithWeek();

    List<ItemsClaim> getClaimWithMonth();

    List<ItemsClaim> getClaimDescByTime();

    List<ItemsClaim> getClaimSelf(Integer userId);
}
