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
}
