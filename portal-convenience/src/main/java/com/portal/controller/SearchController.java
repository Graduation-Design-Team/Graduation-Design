package com.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.ItemsSearch;
import com.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/test")
    public String test() {
        return "便民服务测试";
    }

    /**
     * 查询寻物启示列表
     */
    @GetMapping("/listsearch")
    public PageInfo<ItemsSearch> listSearch(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsSearch> listSearch = searchService.getListSearch();
        PageInfo<ItemsSearch> itemsSearchPageInfo = new PageInfo<>(listSearch);
        return itemsSearchPageInfo;
    }

    /**
     * 发布寻物启示(每位用户只能发布3条寻物启事)
     *
     * @param itemsSearch 丢失物品的信息
     */
    @PostMapping("/publish")
    public String publish(ItemsSearch itemsSearch) {
        int i = searchService.insertItemsSearch(itemsSearch);
        if (i == 0) {
            return "error";
        }
        return "success";
    }

    /**
     * 管理员 逻辑删除一条寻物信息
     *
     * @param itemsSearch itemId
     * @return
     */
    @PutMapping("/searchdeletebyadmin")
    public String deleteByAdmin(ItemsSearch itemsSearch) {
        int i = searchService.updateStatusByAdmin(itemsSearch);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 用户逻辑删除自己发布的一条寻物信息
     *
     * @param itemsSearch userId,itemId
     * @return
     */
    @PutMapping("/searchdeletebyuser")
    public String deleteByUser(ItemsSearch itemsSearch) {
        int i = searchService.updateStatusByUser(itemsSearch);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 根据itemId查询一条寻物启事详情
     */
    @GetMapping("/detail")
    public ItemsSearch getSearchById(Integer itemId) {
        return searchService.getSearchById(itemId);
    }

    /**
     * 用户修改自己发布的寻物启事的详情
     */
    @PutMapping("/update")
    public String editSearch(ItemsSearch itemsSearch) {
        Integer i = searchService.updateSearch(itemsSearch);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 统计当天发布寻物启事的条数
     */
    @GetMapping("/day/statistics")
    public Integer getNumWithDay() {
        return searchService.getNumWithDay();
    }

    /**
     * 统计本周发布寻物启事的条数
     */
    @GetMapping("/week/statistics")
    public Integer getNumWithWeek() {
        return searchService.getNumWithWeek();
    }

    /**
     * 统计本月发布寻物启事的条数
     */
    @GetMapping("/month/statistics")
    public Integer getNumWithMonth() {
        return searchService.getNumWithMonth();
    }

    /**
     * 查询最近一天发布的寻物启事列表
     */
    @GetMapping("/day")
    public PageInfo<ItemsSearch> getSearchWithDay(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsSearch> listSearch = searchService.getSearchWithDay();
        PageInfo<ItemsSearch> itemsSearchPageInfo = new PageInfo<>(listSearch);
        return itemsSearchPageInfo;
    }

    /**
     * 查询最近一周发布的寻物启事列表
     */
    @GetMapping("/week")
    public PageInfo<ItemsSearch> getSearchWithWeek(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsSearch> listSearch = searchService.getSearchWithWeek();
        PageInfo<ItemsSearch> itemsSearchPageInfo = new PageInfo<>(listSearch);
        return itemsSearchPageInfo;
    }

    /**
     * 查询最近一个月发布的寻物启事列表
     */
    @GetMapping("/month")
    public PageInfo<ItemsSearch> getSearchWithMonth(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsSearch> listSearch = searchService.getSearchWithMonth();
        PageInfo<ItemsSearch> itemsSearchPageInfo = new PageInfo<>(listSearch);
        return itemsSearchPageInfo;
    }

    /**
     * 根据发布时间降序查询寻物启事列表
     */
    @GetMapping("/desc/time")
    public PageInfo<ItemsSearch> getSearchDescByTime(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ItemsSearch> listSearch = searchService.getSearchDescByTime();
        PageInfo<ItemsSearch> itemsSearchPageInfo = new PageInfo<>(listSearch);
        return itemsSearchPageInfo;
    }

    /**
     * 用户查询自己发布的寻物启事列表
     */
    @GetMapping("/self")
    public List<ItemsSearch> getSearchSelf(Integer userId) {
        return searchService.getSearchSelf(userId);
    }
}
