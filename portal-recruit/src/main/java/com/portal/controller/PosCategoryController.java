package com.portal.controller;

import com.portal.pojo.PosCategoryEntity;
import com.portal.service.PosCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.PatternUtils;

import java.util.List;

@RestController
@RequestMapping("/pos")
public class PosCategoryController {

  @Autowired private PosCategoryService posCategoryService;

  /**
   * 获取职位分类
   *
   * @param id
   * @return
   */
  @ApiOperation("获取职位分类")
  @GetMapping("/search/{id}")
  public ResponseEntity<List<PosCategoryEntity>> getPosCategory(
          @PathVariable @ApiParam(value = "接收用户传递过来的id", required = true) String id) {
    if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
      throw new RuntimeException("id不合法");
    }
    List<PosCategoryEntity> list = posCategoryService.getPosCategory(Long.parseLong(id));
    return ResponseEntity.ok(list);
  }

  /**
   * 添加职位分类
   *
   * @param posCategoryEntity
   * @return
   */
  @ApiOperation("添加职位分类")
  @PostMapping(value = "/add", headers = "content-type=application/json")
  public ResponseEntity<Void> addPosCategory(
          @RequestBody @ApiParam(value = "接收传递过来的职位分类信息", required = true) PosCategoryEntity posCategoryEntity) {
    if (posCategoryEntity.getPosIsDel() == null) {
      throw new RuntimeException("posIsDel不合法");
    }
    if (posCategoryEntity.getPosIsParent() == null) {
      throw new RuntimeException("posIsParent不合法");
    }
    if (posCategoryEntity.getPosName() == null) {
      throw new RuntimeException("posName不合法");
    }
    if (posCategoryEntity.getPosParentId() == null) {
      throw new RuntimeException("posParentId不合法");
    }
    if (posCategoryEntity.getPosSort() == null) {
      throw new RuntimeException("posSort不合法");
    }
    // 设置PosIsDel默认为0
    posCategoryEntity.setPosIsDel((byte) 0);
    posCategoryService.addPosCategory(posCategoryEntity);
    return ResponseEntity.ok().build();
  }

  /**
   * 更新职位分类
   *
   * @param posCategoryEntity
   * @return
   */
  @ApiOperation("更新职位分类")
  @PostMapping(value = "/update", headers = "content-type=application/json")
  public ResponseEntity<Void> updatePosCategory(
          @RequestBody @ApiParam(value = "接收传递过来的职位分类信息", required = true) PosCategoryEntity posCategoryEntity) {
    if (posCategoryEntity.getPosId() == null) {
      throw new RuntimeException("posId不合法");
    }
    if (posCategoryEntity.getPosIsDel() == null) {
      throw new RuntimeException("posIsDel不合法");
    }
    if (posCategoryEntity.getPosIsParent() == null) {
      throw new RuntimeException("posIsParent不合法");
    }
    if (posCategoryEntity.getPosName() == null) {
      throw new RuntimeException("posName不合法");
    }
    if (posCategoryEntity.getPosParentId() == null) {
      throw new RuntimeException("posParentId不合法");
    }
    if (posCategoryEntity.getPosSort() == null) {
      throw new RuntimeException("posSort不合法");
    }
    posCategoryService.updatePosCategory(posCategoryEntity);
    return ResponseEntity.ok().build();
  }

  /**
   * 删除职位分类
   *
   * @param id
   * @return
   */
  @ApiOperation("删除职位分类")
  @PostMapping(value = "/delete", headers = "content-type=application/json")
  public ResponseEntity<Void> deletePosCategory(@ApiParam(value = "接收用户传递过来的id", required = true) String id) {
    if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
      throw new RuntimeException("id不合法");
    }
    posCategoryService.deletePosCategory(Long.parseLong(id));
    return ResponseEntity.ok().build();
  }

  /**
   * 启用被删除的分类
   *
   * @param id
   * @return
   */
  @ApiOperation("启用被删除的分类")
  @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
  public ResponseEntity<Void> openPosCategory(@ApiParam(value = "接收用户传递过来的id", required = true) String id) {
    if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
      throw new RuntimeException("id不合法");
    }
    posCategoryService.openPosCategory(Long.parseLong(id));
    return ResponseEntity.ok().build();
  }
}
