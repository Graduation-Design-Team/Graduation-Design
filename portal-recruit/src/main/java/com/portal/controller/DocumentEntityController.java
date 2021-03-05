package com.portal.controller;

import com.portal.pojo.DocumentEntity;
import com.portal.service.DocumentEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.PatternUtils;

import java.util.List;

@Api("文档类")
@RestController
@RequestMapping("/document")
public class DocumentEntityController {

  @Autowired private DocumentEntityService documentEntityService;

  /**
   * 获取文档列表
   *
   * @return
   */
  @ApiOperation("查询文档")
  @GetMapping("/search")
  public ResponseEntity<List<DocumentEntity>> getDocumentEntity() {
    List<DocumentEntity> list = documentEntityService.getDocumentEntity();
    return ResponseEntity.ok(list);
  }

  /**
   * 添加文档
   *
   * @param documentEntity
   */
  @ApiOperation("添加文档")
  @PostMapping(value = "/add", headers = "content-type=application/json")
  public ResponseEntity<Void> addDocumentEntity(
          @RequestBody @ApiParam(value = "接收添加文档的参数", required = true) DocumentEntity documentEntity) {
    if (documentEntity.getDocTitle() == null) {
      throw new RuntimeException("docTitle不合法");
    }
    if (documentEntity.getDocContent() == null) {
      throw new RuntimeException("docContent不合法");
    }
    if (documentEntity.getDocCount() == null) {
      throw new RuntimeException("docCount不合法");
    }
    if (documentEntity.getIsDel() == null) {
      throw new RuntimeException("isDel不合法");
    }
    if (documentEntity.getIsPass() == null) {
      throw new RuntimeException("isPass不合法");
    }
    documentEntity.setIsDel((byte) 0);
    documentEntity.setIsPass((byte) 1);
    documentEntityService.addDocumentEntity(documentEntity);
    return ResponseEntity.ok().build();
  }

  /**
   * 更新文档
   *
   * @param documentEntity
   */
  @ApiOperation("更新文档")
  @PostMapping(value = "/update", headers = "content-type=application/json")
  public ResponseEntity<Void> updateDocumentEntity(
          @RequestBody @ApiParam(value = "接收添加文档的参数", required = true) DocumentEntity documentEntity) {
    if (documentEntity.getDocId() == null) {
      throw new RuntimeException("docId不合法");
    }
    if (documentEntity.getDocTitle() == null) {
      throw new RuntimeException("docTitle不合法");
    }
    if (documentEntity.getDocContent() == null) {
      throw new RuntimeException("docContent不合法");
    }
    if (documentEntity.getDocCount() == null) {
      throw new RuntimeException("docCount不合法");
    }
    if (documentEntity.getIsDel() == null) {
      throw new RuntimeException("isDel不合法");
    }
    if (documentEntity.getIsPass() == null) {
      throw new RuntimeException("isPass不合法");
    }
    documentEntityService.updateDocumentEntity(documentEntity);
    return ResponseEntity.ok().build();
  }

  /**
   * 删除文档
   *
   * @param id
   */
  @ApiOperation("删除文档")
  @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
  public ResponseEntity<Void> deleteDocumentEntity(@ApiParam(value = "接收要删除文档的id", required = true) String id) {
    if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
      throw new RuntimeException("id不合法");
    }
    documentEntityService.deleteDocumentEntity(Long.parseLong(id));
    return ResponseEntity.ok().build();
  }

  /**
   * 启用文档
   *
   * @param id
   */
  @ApiOperation("启用文档")
  @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
  public ResponseEntity<Void> openDocumentEntity(@ApiParam(value = "接收要启用文档的id", required = true) String id) {
    if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
      throw new RuntimeException("id不合法");
    }
    documentEntityService.openDocumentEntity(Long.parseLong(id));
    return ResponseEntity.ok().build();
  }


    /**
     * 审核不通过
     * @param id
     */
    @ApiOperation("审核不通过")
    @PostMapping(value = "/notPass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> notPassDocumentEntityById(@ApiParam(value = "接收文档的id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
          throw new RuntimeException("id不合法");
        }
        documentEntityService.notPassDocumentEntityById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 审核通过
     * @param id
     */
    @ApiOperation("审核通过")
    @PostMapping(value = "/pass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> passDocumentEntityById(@ApiParam(value = "接收文档的id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
          throw new RuntimeException("id不合法");
        }
        documentEntityService.passDocumentEntityById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
