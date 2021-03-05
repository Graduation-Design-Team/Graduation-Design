package com.portal.controller;

import com.portal.pojo.NavCategoryEntity;
import com.portal.service.NavCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.PatternUtils;

import java.util.List;

/**
 * 导航栏分类Controller
 */
@RestController
@RequestMapping("/nav")
@Api("导航栏分类接口")
public class NavCategoryController {

    @Autowired
    private NavCategoryService navCategoryService;

    /**
     * 根据父id获取父id下的子分类
     *
     * @param id
     * @return
     */
    @ApiOperation("获取导航栏分类")
    @GetMapping(value = "/search/{id}")
    public ResponseEntity<List<NavCategoryEntity>> getNavCategoryById(
            @PathVariable @ApiParam(value = "接收传递过来的id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        List<NavCategoryEntity> list = navCategoryService.getNavCategoryById(Long.parseLong(id));
        return ResponseEntity.ok(list);
    }

    /**
     * 添加导航栏分类
     *
     * @param navCategoryEntity
     * @return @RequestBody 调试的时候设置content-type:application/json
     */
    @ApiOperation("添加导航栏分类")
    @PostMapping(value = "/add", headers = "content-type=application/json")
    public ResponseEntity<Void> addNavCategory(
            @RequestBody @ApiParam(value = "接收传递过来的分类信息", required = true) NavCategoryEntity navCategoryEntity) {
        if (navCategoryEntity.getNavIsDel() == null) {
            throw new RuntimeException("navIsDel不合法");
        }
        if (navCategoryEntity.getNavIsParent() == null) {
            throw new RuntimeException("navIsParent不合法");
        }
        if (navCategoryEntity.getNavName() == null) {
            throw new RuntimeException("navName不合法");
        }
        if (navCategoryEntity.getNavParentId() == null) {
            throw new RuntimeException("navParentId不合法");
        }
        if (navCategoryEntity.getNavSort() == null) {
            throw new RuntimeException("navSort不合法");
        }
        //设置PosIsDel默认为0
        navCategoryEntity.setNavIsDel((byte) 0);
        navCategoryService.addNavCategory(navCategoryEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改导航栏分类
     *
     * @param navCategoryEntity
     * @return
     */
    @ApiOperation("修改导航栏分类")
    @PostMapping(value = "/update", headers = "content-type=application/json")
    public ResponseEntity<Void> updateNavCategory(
            @RequestBody @ApiParam(value = "接收用户传递过来的分类信息", required = true) NavCategoryEntity navCategoryEntity) {
        if (navCategoryEntity.getNavId() == null) {
            throw new RuntimeException("navId不合法");
        }
        if (navCategoryEntity.getNavIsDel() == null) {
            throw new RuntimeException("navIsDel不合法");
        }
        if (navCategoryEntity.getNavIsParent() == null) {
            throw new RuntimeException("navIsParent不合法");
        }
        if (navCategoryEntity.getNavName() == null) {
            throw new RuntimeException("navName不合法");
        }
        if (navCategoryEntity.getNavParentId() == null) {
            throw new RuntimeException("navParentId不合法");
        }
        if (navCategoryEntity.getNavSort() == null) {
            throw new RuntimeException("navSort不合法");
        }
        navCategoryService.updateNavCategory(navCategoryEntity);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("删除导航栏分类")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deleteNavCategory(
            @ApiParam(value = "接收用户传递过来要删除的分类id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        navCategoryService.deleteNavCategory(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("开启被删除的导航分类")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openNavCategory(@ApiParam(value = "接收用户传递过来要删除的分类id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        navCategoryService.openNavCategory(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
