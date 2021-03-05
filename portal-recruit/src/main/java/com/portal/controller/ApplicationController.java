package com.portal.controller;


import com.portal.pojo.ApplicationEntity;
import com.portal.service.ApplicationService;
import com.portal.service.PosDetailEntityService;
import com.portal.service.UserEntityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.PatternUtils;

import java.util.List;


@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private PosDetailEntityService posDetailEntityService;

    @ApiOperation("查询用户的应聘记录")
    @GetMapping(value = "/search/{userId}")
    public ResponseEntity<List<ApplicationEntity>> searchApplication(@PathVariable @ApiParam(value = "获取用户的id", required = true) String userId) {
        if(userId == null || !PatternUtils.isNum(userId) || Long.parseLong(userId) < 0) {
            throw new RuntimeException("userId不合法");
        }
        List<ApplicationEntity> list = applicationService.searchApplication(Long.parseLong(userId));
        return ResponseEntity.ok(list);
    }

    @ApiOperation("添加用户应聘记录")
    @PostMapping(value = "/add", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> addApplication(@ApiParam(value = "获取用户的id", required = true) String userId, @ApiParam(value = "获取职位的id", required = true) String detailId) {
        if(userId == null || !PatternUtils.isNum(userId) || Long.parseLong(userId) < 0) {
            throw new RuntimeException("userId不合法");
        }

        if(detailId == null || !PatternUtils.isNum(detailId) || Long.parseLong(detailId) < 0) {
            throw new RuntimeException("detailId不合法");
        }

        //判断该用户是否存在
        if(!userEntityService.isExistsUserById(Long.parseLong(userId))) {
            throw new RuntimeException("userId不存在");
        }

        //判断该职位是否存在
        if(!posDetailEntityService.existsPosDetailById(Long.parseLong(detailId))) {
            throw new RuntimeException("detailId不存在");
        }

        applicationService.addApplication(Long.parseLong(userId),Long.parseLong(detailId));
        return ResponseEntity.ok().build();
    }


    @ApiOperation("更新用户应聘记录")
    @PostMapping(value = "/update", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> updateApplication(@ApiParam(value = "获取应聘的id", required = true) String appId, @ApiParam(value = "获取职位的id", required = true) String detailId) {
        if(appId == null || !PatternUtils.isNum(appId) || Long.parseLong(appId) < 0) {
            throw new RuntimeException("appId不合法");
        }

        if(detailId == null || !PatternUtils.isNum(detailId) || Long.parseLong(detailId) < 0) {
            throw new RuntimeException("detailId不合法");
        }

        //判断该职位是否存在
        if(!posDetailEntityService.existsPosDetailById(Long.parseLong(detailId))) {
            throw new RuntimeException("detailId不存在");
        }
        applicationService.updateApplication(Long.parseLong(appId),Long.parseLong(detailId));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("删除用户应聘记录")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deleteApplication(@ApiParam(value = "获取应聘的id", required = true) String appId) {
        if(appId == null || !PatternUtils.isNum(appId) || Long.parseLong(appId) < 0) {
            throw new RuntimeException("appId不合法");
        }
        applicationService.deleteApplication(Long.parseLong(appId));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("启用用户的应聘记录")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openApplication(@ApiParam("获取应聘的id")String appId) {
        if(appId == null || !PatternUtils.isNum(appId) || Long.parseLong(appId) < 0) {
            throw new RuntimeException("appId不合法");
        }
        applicationService.openApplication(Long.parseLong(appId));
        return ResponseEntity.ok().build();
    }
}
