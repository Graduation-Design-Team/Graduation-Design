package com.portal.controller;


import com.portal.pojo.NavVedioEntity;
import com.portal.service.NavVedioEntityService;
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

@Api("导航视频类")
@RestController
@RequestMapping("/navVedio")
public class NavVedioEntityController {

    @Autowired
    private NavVedioEntityService navVedioEntityService;

    /**
     * 获取视频列表
     * @return
     */
    @ApiOperation("根据导航栏分类Id查询视频")
    @GetMapping("/search/{navId}")
    public ResponseEntity<List<NavVedioEntity>> search(@PathVariable @ApiParam(value = "接收用户传递过来的分类id", required = true) String navId) {
        if(navId == null || !PatternUtils.isNum(navId) || Long.parseLong(navId) < 0) {
            throw new RuntimeException("navId不合法");
        }
        List<NavVedioEntity> list = navVedioEntityService.search(Long.parseLong(navId));
        return ResponseEntity.ok(list);
    }

    /**
     * 使用的是腾讯云的cos
     * 添加导航栏分类视频
     * content-type:multipart/form-data
     */

    @ApiOperation("添加视频")
    @PostMapping(value = "/addNav",headers = "content-type=multipart/form-data")
    public ResponseEntity<Void> addVedioEntity(@RequestParam("file") @ApiParam(value = "接收客户端传递过来的视频", required = true) MultipartFile file,
                                               @RequestParam("navId") @ApiParam(value = "接收导航栏分类的id", required = true) String navId) {
        if(navId == null || !PatternUtils.isNum(navId) || Long.parseLong(navId) < 0) {
            throw new RuntimeException("navId不合法");
        }

        //这个接口只上传视频
        if(!file.getContentType().contains("video")) {
            throw new RuntimeException("请上传视频");
        }

        //如果视频的大于10M则不上传
        if(file.getSize() > 1024 * 1024 * 10) {
            throw new RuntimeException("视频超过10M");
        }
        //先判断navId是否存在，存在再上传
        if (!navVedioEntityService.existsByNavId(Long.parseLong(navId))) {
            throw new RuntimeException("navId不存在");
        }
        // 每个navId关联视频，且视频只能是一个，关联照片，照片最多三张
        if (!(navVedioEntityService.navCateVedioCount(Long.parseLong(navId)) < 1)) {
            throw new RuntimeException("视频超过一个");
        }
        //上传视频
        String url = UploadFileUtils.uploadPicture(file, "navVedio/", "https://portal-vedio-1258887597.cos.ap-shenzhen-fsi.myqcloud.com/"
        ,"portal-vedio-1258887597","cos.ap-shenzhen-fsi.myqcloud.com");
        if(com.qcloud.cos.utils.StringUtils.isNullOrEmpty(url)) {
            throw new RuntimeException("上传视频失败");
        }
        //保存视频到数据库中 size的大小为字节
        navVedioEntityService.addVedioEntity(url, Long.parseLong(navId), file.getSize());

        return ResponseEntity.ok().build();
    }

    /**
     * 更新视频
     * 使用的是腾讯云的cos
     * content-type:multipart/form-data
     */
    @ApiOperation("更新视频")
    @PostMapping(value = "/updateNav",headers = "content-type=multipart/form-data")
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
            throw new RuntimeException("视频超过10M");
        }
        //先判断vedioId是否存在，存在再更新
        if (!navVedioEntityService.existsByVedioId(Long.parseLong(vedioId))) {
            throw new RuntimeException("vedioId不存在");
        }
        //上传视频
        String url = UploadFileUtils.uploadPicture(file, "navVedio/", "https://portal-vedio-1258887597.cos.ap-shenzhen-fsi.myqcloud.com/"
                ,"portal-vedio-1258887597","cos.ap-shenzhen-fsi.myqcloud.com");
        if(com.qcloud.cos.utils.StringUtils.isNullOrEmpty(url)) {
            throw new RuntimeException("视频上传失败");
        }
        //更新视频到数据库中 size的大小为字节
        navVedioEntityService.updateVedioEntity(url, Long.parseLong(vedioId), file.getSize());

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
        navVedioEntityService.deleteVedioEntity(Long.parseLong(id));
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
        navVedioEntityService.openVedioEntity(Long.parseLong(id));
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
        navVedioEntityService.notPassVedioEntity(Long.parseLong(id));
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
        navVedioEntityService.passVedioEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
