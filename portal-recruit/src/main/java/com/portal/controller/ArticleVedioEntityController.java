package com.portal.controller;

import com.portal.pojo.ArticleVedioEntity;
import com.portal.service.ArticleVedioEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.PatternUtils;
import utils.UploadFileUtils;

import java.util.List;

@Api("文章视频类")
@RestController
@RequestMapping("/articleVedio")
public class ArticleVedioEntityController {

    @Autowired
    private ArticleVedioEntityService articleVedioEntityService;

    /**
     * 获取视频列表
     * @return
     */
    @ApiOperation("根据文章Id查询视频")
    @GetMapping(value = "/search/{articleId}")
    public ResponseEntity<List<ArticleVedioEntity>> search(@PathVariable @ApiParam(value = "接收用户传递过来的文章id", required = true) String articleId) {
        if(articleId == null || !PatternUtils.isNum(articleId) || Long.parseLong(articleId) < 0) {
            throw new RuntimeException("articleId不合法");
        }
        List<ArticleVedioEntity> list = articleVedioEntityService.search(Long.parseLong(articleId));
        return ResponseEntity.ok(list);
    }

    /**
     * 使用的是腾讯云的cos
     * 添加文章视频
     * content-type:multipart/form-data
     */

    @ApiOperation("添加视频")
    @PostMapping(value = "/add",headers = "content-type=multipart/form-data")
    public ResponseEntity<Void> addVedioEntity(@RequestParam("file") @ApiParam(value = "接收客户端传递过来的视频", required = true) MultipartFile file,
                                               @RequestParam("articleId") @ApiParam(value = "接收导航栏分类的id", required = true) String articleId) {
        if(articleId == null || !PatternUtils.isNum(articleId) || Long.parseLong(articleId) < 0) {
            throw new RuntimeException("articleId不合法");
        }

        //这个接口只上传视频
        if(!file.getContentType().contains("video")) {
            throw new RuntimeException("请上传视频");
        }

        //如果视频的大于10M则不上传
        if(file.getSize() > 1024 * 1024 * 10) {
            throw new RuntimeException("视频大于10M");
        }
        //先判断articleId是否存在，存在再上传
        if (!articleVedioEntityService.existsByArticleId(Long.parseLong(articleId))) {
            throw new RuntimeException("articleId不存在");
        }
        // 每个articleId关联视频，且视频只能是一个，关联照片，照片最多三张
        if (!(articleVedioEntityService.articleCateVedioCount(Long.parseLong(articleId)) < 1)) {
            throw new RuntimeException("视频不能超过1个");
        }
        //上传视频
        String url = UploadFileUtils.uploadPicture(file, "articleVedio/", "https://portal-vedio-1258887597.cos.ap-shenzhen-fsi.myqcloud.com/"
                ,"portal-vedio-1258887597","cos.ap-shenzhen-fsi.myqcloud.com");
        if(com.qcloud.cos.utils.StringUtils.isNullOrEmpty(url)) {
            throw new RuntimeException("上传视频失败");
        }
        //保存视频到数据库中 size的大小为字节
        articleVedioEntityService.addVedioEntity(url, Long.parseLong(articleId), file.getSize());

        return ResponseEntity.ok().build();
    }

    /**
     * 更新视频
     * 使用的是腾讯云的cos
     * content-type:multipart/form-data
     */
    @ApiOperation("更新视频")
    @PostMapping(value = "/update",headers = "content-type=multipart/form-data")
    public ResponseEntity<Void> updatePictureEntity(@RequestParam("file") @ApiParam(value = "接收客户端传递过来的视频", required = true) MultipartFile file,
                                                    @RequestParam("vedioId") @ApiParam(value = "接收要更新视频的id", required = true) String vedioId) {
        if(vedioId == null || !PatternUtils.isNum(vedioId) || Long.parseLong(vedioId) < 0) {
            throw new RuntimeException("vedioId不合法");
        }

        //这个接口只上传视频
        if(!file.getContentType().contains("video")) {
            throw new RuntimeException("请上传视频");
        }

        //如果视频的大于10M则不更新
        if(file.getSize() > 1024 * 1024 * 10) {
            throw new RuntimeException("视频大于10M");
        }
        //先判断vedioId是否存在，存在再更新
        if (!articleVedioEntityService.existsByVedioId(Long.parseLong(vedioId))) {
            throw new RuntimeException("vedioId不存在");
        }
        //上传视频
        String url = UploadFileUtils.uploadPicture(file, "articleVedio/", "https://portal-vedio-1258887597.cos.ap-shenzhen-fsi.myqcloud.com/"
                ,"portal-vedio-1258887597","cos.ap-shenzhen-fsi.myqcloud.com");
        if(com.qcloud.cos.utils.StringUtils.isNullOrEmpty(url)) {
            throw new RuntimeException("上传视频失败");
        }
        //更新视频到数据库中 size的大小为字节
        articleVedioEntityService.updateVedioEntity(url, Long.parseLong(vedioId), file.getSize());

        return ResponseEntity.ok().build();
    }

    /**
     * 删除视频
     * @param id
     */
    @ApiOperation("删除视频")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deletePictureEntity(@ApiParam(value = "获取要删除视频的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleVedioEntityService.deleteVedioEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 启用视频
     * @param id
     */
    @ApiOperation("启用视频")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openPictureEntity(@ApiParam(value = "获取要启用视频的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleVedioEntityService.openVedioEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 审核不通过
     * @param id
     */
    @ApiOperation("审核不通过")
    @PostMapping(value = "/notPass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> notPassPictureEntity(@ApiParam(value = "获取要审核视频的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleVedioEntityService.notPassVedioEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 审核通过
     * @param id
     */
    @ApiOperation("审核通过")
    @PostMapping(value = "/pass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> passPictureEntity(@ApiParam(value = "获取要审核视频的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        articleVedioEntityService.passVedioEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
