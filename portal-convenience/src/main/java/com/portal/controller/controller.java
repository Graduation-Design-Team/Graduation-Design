package com.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.ItemsSearch;
import com.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/convenience")
public class controller {
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
    public PageInfo<ItemsSearch> listSearch(Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<ItemsSearch> listSearch = searchService.getListSearch();
        PageInfo<ItemsSearch> itemsSearchPageInfo = new PageInfo<>(listSearch);
        return itemsSearchPageInfo;
    }

    /**发布寻物启示
     * @param itemsSearch 丢失物品的信息
     */
    @PostMapping("/publish")
    public String publish(ItemsSearch itemsSearch){
        int i=searchService.insertItemsSearch(itemsSearch);
        if(i==0){
            return "error";
        }
        return "success";
    }
}
