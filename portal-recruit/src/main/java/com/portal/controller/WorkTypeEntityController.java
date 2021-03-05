package com.portal.controller;


import com.portal.pojo.WorkTypeEntity;
import com.portal.service.WorkTypeEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.PatternUtils;

import java.util.List;

@Api("职位类型")
@RestController
@RequestMapping("/WorkType")
public class WorkTypeEntityController {

    @Autowired
    private WorkTypeEntityService workTypeEntityService;

    /**
     * 获取职位类型
     * @return
     */
    @ApiOperation("查询职位类型")
    @GetMapping("/search")
    public ResponseEntity<List<WorkTypeEntity>> getWorkType() {
        List<WorkTypeEntity> list = workTypeEntityService.getWorkType();
        return ResponseEntity.ok(list);
    }

    /**
     * 添加职位类型
     * @param workTypeEntity
     */
    @ApiOperation("添加职位类型")
    @PostMapping(value = "/add", headers = "content-type=application/json")
    public ResponseEntity<Void> addWorkType(@RequestBody @ApiParam(value = "获取添加职位类型的参数", required = true) WorkTypeEntity workTypeEntity) {
        if (workTypeEntity.getIsDel() == null) {
            throw new RuntimeException("isDel不合法");
        }
        if (workTypeEntity.getTypeName() == null) {
            throw new RuntimeException("typeName不合法");
        }
        workTypeEntity.setIsDel((byte)0);
        workTypeEntityService.addWorkType(workTypeEntity);
        return ResponseEntity.ok().build();
    }


    /**
     * 更新职位类型
     * @param workTypeEntity
     */
    @ApiOperation("更新职位类型")
    @PostMapping(value = "/update", headers = "content-type=application/json")
    public ResponseEntity<Void> updateWorkType(@RequestBody @ApiParam(value = "获取添加职位类型的参数", required = true) WorkTypeEntity workTypeEntity) {
        if (workTypeEntity.getTypeId() == null) {
            throw new RuntimeException("typeId不合法");
        }
        if (workTypeEntity.getIsDel() == null) {
            throw new RuntimeException("isDel不合法");
        }
        if (workTypeEntity.getTypeName() == null) {
            throw new RuntimeException("typeName不合法");
        }
        workTypeEntityService.updateWorkType(workTypeEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除职位类型
     * @param id
     */
    @ApiOperation("删除职位类型")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deleteWorkType(@ApiParam(value = "获取删除职位类型的id", required = true) String id) {
    System.out.println(id);
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        workTypeEntityService.deleteWorkType(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 启用职位类型
     * @param id
     */
    @ApiOperation("启用被删除的职位")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openWorkType(@ApiParam(value = "获取要启用职位类型的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        workTypeEntityService.openWorkType(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
