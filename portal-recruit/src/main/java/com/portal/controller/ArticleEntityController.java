package com.portal.controller;


import com.portal.pojo.ArticleEntity;
import com.portal.service.ArticleEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.PatternUtils;

import java.util.List;


@Api("文章类")
@RestController
@RequestMapping("/article")
public class ArticleEntityController {

    @Autowired
    private ArticleEntityService articleEntityService;

    /**
     * 获取文章列表
     *
     * @return
     */
    @ApiOperation("查询文章")
    @GetMapping("/search")
    public ResponseEntity<List<ArticleEntity>> getArticleEntity() {
        List<ArticleEntity> list = articleEntityService.getArticleEntity();
        return ResponseEntity.ok(list);
    }

    /**
     * 添加文章
     *
     * @param articleEntity
     */
    @ApiOperation("添加文章")
    @PostMapping(value = "/add", headers = "content-type=application/json")
    public ResponseEntity<Void> addArticleEntity(
            @RequestBody @ApiParam(value = "接收添加文章的参数", required = true) ArticleEntity articleEntity) {
        if (articleEntity.getUserId() == null) {
            throw new RuntimeException("userId不合法");
        }
        if (articleEntity.getContent() == null) {
            throw new RuntimeException("content不合法");
        }
        if (articleEntity.getIsDel() == null) {
            throw new RuntimeException("isDel不合法");
        }
        if (articleEntity.getIsPass() == null) {
            throw new RuntimeException("isPass不合法");
        }
        articleEntity.setIsDel((byte) 0);
        articleEntity.setIsPass((byte) 1);
        articleEntityService.addArticleEntity(articleEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新文章
     *
     * @param articleEntity
     */
    @ApiOperation("更新文章")
    @PostMapping(value = "/update", headers = "content-type=application/json")
    public ResponseEntity<Void> updateArticleEntity(
            @RequestBody @ApiParam(value = "接收添加文章的参数", required = true) ArticleEntity articleEntity) {
        if (articleEntity.getArticleId() == null) {
            throw new RuntimeException("articleId不合法");
        }
        if (articleEntity.getUserId() == null) {
            throw new RuntimeException("userId不合法");
        }
        if (articleEntity.getContent() == null) {
            throw new RuntimeException("content不合法");
        }
        if (articleEntity.getIsDel() == null) {
            throw new RuntimeException("isDel不合法");
        }
        if (articleEntity.getIsPass() == null) {
            throw new RuntimeException("isPass不合法");
        }
        articleEntityService.updateArticleEntity(articleEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除文章
     *
     * @param id
     */
    @ApiOperation("删除文章")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deleteArticleEntity(@ApiParam(value = "接收要删除文章的id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleEntityService.deleteArticleEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 启用文章
     *
     * @param id
     */
    @ApiOperation("启用文章")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openArticleEntity(@ApiParam(value = "接收要启用文章的id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleEntityService.openArticleEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }


    /**
     * 审核不通过
     * @param id
     */
    @ApiOperation("审核不通过")
    @PostMapping(value = "/notPass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> notPassArticleEntityById(@ApiParam(value = "接收文章的id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleEntityService.notPassArticleEntityById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 审核通过
     * @param id
     */
    @ApiOperation("审核通过")
    @PostMapping(value = "/pass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> passArticleEntityById(@ApiParam(value = "接收文章的id", required = true) String id) {
    System.out.println(id);
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleEntityService.passArticleEntityById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
