package com.portal.controller;

import com.portal.pojo.ApplicationProcessEntity;
import com.portal.service.ApplicationProcessEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.PatternUtils;

import java.util.List;

@Api("用户应聘进程类")
@RestController
@RequestMapping("/process")
public class ApplicationProcessEntityController {

  @Autowired private ApplicationProcessEntityService applicationProcessEntityService;

  @ApiOperation("查询用户的应聘进程")
  @PostMapping(value = "/search/{appId}")
  public ResponseEntity<List<ApplicationProcessEntity>> search(
      @PathVariable @ApiParam(value = "获取应聘记录的id", required = true) String appId) {
    if (appId == null || !PatternUtils.isNum(appId) || Long.parseLong(appId) < 0) {
      throw new RuntimeException("appId不合法");
    }
    List<ApplicationProcessEntity> list =
        applicationProcessEntityService.search(Long.parseLong(appId));
    return ResponseEntity.ok(list);
  }

    /**
     * 状态码
     * 0: 失败 1：投递简历 2：安排简历评估 3：安排笔试 4：面试安排 5：offer发放
     *
     * @param appId
     * @param processCode
     * @return
     */
  @ApiOperation("保存应聘进程 ")
  @PostMapping(value = "/add", headers = "content-type=application/x-www-form-urlencoded")
  public ResponseEntity<Void> add(
      @ApiParam(value = "获取应聘记录id", required = true) String appId,
      @ApiParam(value = "获取应聘记录code 0: 失败 1：投递简历 2：安排简历评估 3：安排笔试 4：面试安排 5：offer发放") String processCode) {
    if (appId == null || !PatternUtils.isNum(appId) || Long.parseLong(appId) < 0) {
      throw new RuntimeException("appId不合法");
    }
    applicationProcessEntityService.add(
        new ApplicationProcessEntity(
            Long.parseLong(appId),
            processCode == null ? null : Long.parseLong(processCode),
            ApplicationProcessEntity.newDateTime()));
    return ResponseEntity.ok().build();
  }


  @ApiOperation("删除用户的应聘进程")
  @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
  public ResponseEntity<Void> delete(@ApiParam(value = "获取要删除记录的Id",required = true) String id) {
    if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
      throw new RuntimeException("id不合法");
    }
    applicationProcessEntityService.delete(Long.parseLong(id));
    return ResponseEntity.ok().build();
  }
}
