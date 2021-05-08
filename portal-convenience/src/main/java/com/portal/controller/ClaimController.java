package com.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.ItemsClaim;
import com.portal.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claim")
public class ClaimController {
    @Autowired
    private ClaimService claimService;

    /**
     * 查询失物招领列表
     */
    @GetMapping("/listclaim")
    public PageInfo<ItemsClaim> listClaim(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsClaim> listClaim = claimService.getListClaim();
        PageInfo<ItemsClaim> itemsClaimPageInfo = new PageInfo<>(listClaim);
        return itemsClaimPageInfo;
    }

    /**
     * 发布失物招领(每位用户只能发布3条食物招领)
     */
    @PostMapping("/publish")
    public String publish(ItemsClaim itemsClaim) {
        int i = claimService.insertItemsClaim(itemsClaim);
        if (i == 0) {
            return "error";
        }
        return "success";
    }

    /**
     * 管理员 逻辑删除一条招领信息
     */
    @PutMapping("/claimdeletebyadmin")
    public String deleteByAdmin(ItemsClaim itemsClaim) {
        int i = claimService.updateStatusByAdmin(itemsClaim);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 用户逻辑删除自己发布的一条招领信息
     */
    @PutMapping("/claimdeletebyuser")
    public String deleteByUser(ItemsClaim itemsClaim) {
        int i = claimService.updateStatusByUser(itemsClaim);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 根据itemId查询一条招领详情
     */
    @GetMapping("/detail")
    public ItemsClaim getClaimById(Integer itemId) {
        return claimService.getClaimById(itemId);
    }

    /**
     * 用户修改自己发布的失物招领的详情
     */
    @PutMapping("/update")
    public String editClaim(ItemsClaim itemsClaim) {
        Integer i = claimService.updateSearch(itemsClaim);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 统计当天发布失物招领的条数
     */
    @GetMapping("/day/statistics")
    public Integer getNumWithDay() {
        return claimService.getNumWithDay();
    }

    /**
     * 统计本周发布失物招领的条数
     */
    @GetMapping("/week/statistics")
    public Integer getNumWithWeek() {
        return claimService.getNumWithWeek();
    }

    /**
     * 统计本月发布失物招领的条数
     */
    @GetMapping("/month/statistics")
    public Integer getNumWithMonth() {
        return claimService.getNumWithMonth();
    }

    /**
     * 查询最近一天发布的失物招领列表
     */
    @GetMapping("/day")
    public PageInfo<ItemsClaim> getClaimWithDay(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsClaim> listClaim = claimService.getClaimWithDay();
        PageInfo<ItemsClaim> itemsClaimPageInfo = new PageInfo<>(listClaim);
        return itemsClaimPageInfo;
    }

    /**
     * 查询最近一周发布的失物招领列表
     */
    @GetMapping("/week")
    public PageInfo<ItemsClaim> getClaimWithWeek(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsClaim> listClaim = claimService.getClaimWithWeek();
        PageInfo<ItemsClaim> itemsClaimPageInfo = new PageInfo<>(listClaim);
        return itemsClaimPageInfo;
    }

    /**
     * 查询最近一个月发布的失物招领列表
     */
    @GetMapping("/month")
    public PageInfo<ItemsClaim> getClaimWithMonth(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsClaim> listClaim = claimService.getClaimWithMonth();
        PageInfo<ItemsClaim> itemsClaimPageInfo = new PageInfo<>(listClaim);
        return itemsClaimPageInfo;
    }

    /**
     * 根据发布时间降序查询失物招领列表
     */
    @GetMapping("/desc/time")
    public PageInfo<ItemsClaim> getClaimDescByTime(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsClaim> listClaim = claimService.getClaimDescByTime();
        PageInfo<ItemsClaim> itemsClaimPageInfo = new PageInfo<>(listClaim);
        return itemsClaimPageInfo;
    }

    /**
     * 用户查询自己发布的失物招领列表
     */
    @GetMapping("/self")
    public List<ItemsClaim> getClaimSelf(Integer userId) {
        return claimService.getClaimSelf(userId);
    }
}
