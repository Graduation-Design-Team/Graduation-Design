package com.portal.controller;

import com.portal.pojo.NavPictureEntity;
import com.portal.service.NavPictureEntityService;
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

@Api("照片类")
@RestController
@RequestMapping("/navPicture")
public class NavPictureEntityController {

    @Autowired
    private NavPictureEntityService navPictureEntityService;

    /**
     * 获取照片列表
     * @return
     */
    @ApiOperation("根据导航栏分类Id查询照片")
    @GetMapping("/search/{navId}")
    public ResponseEntity<List<NavPictureEntity>> search(@PathVariable @ApiParam(value = "接收用户传递过来的分类id", required = true) String navId) {
        if(navId == null || !PatternUtils.isNum(navId) || Long.parseLong(navId) < 0) {
            throw new RuntimeException("navId不合法");
        }
        List<NavPictureEntity> list = navPictureEntityService.search(Long.parseLong(navId));
        return ResponseEntity.ok(list);
    }

    /**
     * 使用的是腾讯云的cos
     * 添加导航栏分类照片
     * content-type:multipart/form-data
     * 同时要访问上传的照片的时候要设置referer=bokehai.xyz
     */

    @ApiOperation("添加照片")
    @PostMapping(value = "/addNav",headers = "content-type=multipart/form-data")
    public ResponseEntity<Void> addPictureEntity(@RequestParam("pic") @ApiParam(value = "接收客户端传递过来的照片", required = true) MultipartFile pic,
                                                 @RequestParam("navId") @ApiParam(value = "接收导航栏分类的id", required = true) String navId) {
        if(navId == null || !PatternUtils.isNum(navId) || Long.parseLong(navId) < 0) {
            throw new RuntimeException("navId不合法");
        }

        //这个接口只上传照片
        if (!pic.getContentType().contains("image")) {
            throw new RuntimeException("请上传照片");
        }

        //如果照片的大于1M则不上传
        if(pic.getSize() > 1024 * 1024) {
            throw new RuntimeException("照片大于1M");
        }
        //先判断navId是否存在，存在再上传
        if (!navPictureEntityService.existsByNavId(Long.parseLong(navId))) {
            throw new RuntimeException("navId不存在");
        }
        // 每个navId可以关联照片和视频，且视频只能是一个，要么关联照片，照片最多三张
        if (!(navPictureEntityService.navCatePicCount(Long.parseLong(navId)) < 3)) {
            throw new RuntimeException("照片超过三张");
        }
        //上传照片
        String url = UploadFileUtils.uploadPicture(pic, "nav/",
                "https://portal-pictures-1258887597.cos.ap-nanjing.myqcloud.com/",
                "portal-pictures-1258887597", "cos.ap-nanjing.myqcloud.com");
        if(com.qcloud.cos.utils.StringUtils.isNullOrEmpty(url)) {
            throw new RuntimeException("上传照片失败");
        }
        //保存照片到数据库中 size的大小为字节
        navPictureEntityService.addPictureEntity(url, Long.parseLong(navId), pic.getSize());

        return ResponseEntity.ok().build();
    }

    /**
     * 更新照片
     * 使用的是腾讯云的cos
     * content-type:multipart/form-data
     */
    @ApiOperation("更新照片")
    @PostMapping(value = "/updateNav",headers = "content-type=multipart/form-data")
    public ResponseEntity<Void> updatePictureEntity(@RequestParam("pic") @ApiParam(value = "接收客户端传递过来的照片", required = true) MultipartFile pic,
                                                    @RequestParam("picId") @ApiParam(value = "接收要更新照片的id", required = true) String picId) {
        if(picId == null || !PatternUtils.isNum(picId) || Long.parseLong(picId) < 0) {
            throw new RuntimeException("picId不合法");
        }

        //这个接口只上传照片
        if (!pic.getContentType().contains("image")) {
            throw new RuntimeException("请上传照片");
        }

        //如果照片的大于1M则不更新
        if(pic.getSize() > 1024 * 1024) {
            throw new RuntimeException("照片大于1M");
        }
        //先判断picId是否存在，存在再更新
        if (!navPictureEntityService.existsByPicId(Long.parseLong(picId))) {
            throw new RuntimeException("picId不存在");
        }
        //上传照片
        String url = UploadFileUtils.uploadPicture(pic, "nav/",
                "https://portal-pictures-1258887597.cos.ap-nanjing.myqcloud.com/",
                "portal-pictures-1258887597", "cos.ap-nanjing.myqcloud.com");
        if(com.qcloud.cos.utils.StringUtils.isNullOrEmpty(url)) {
            throw new RuntimeException("上传照片失败");
        }
        //更新照片到数据库中 size的大小为字节
        navPictureEntityService.updatePictureEntity(url, Long.parseLong(picId), pic.getSize());

        return ResponseEntity.ok().build();
    }

    /**
     * 删除照片
     * @param id
     */
    @ApiOperation("删除照片")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deletePictureEntity(@ApiParam(value = "获取要删除照片的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        navPictureEntityService.deletePictureEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 启用照片
     * @param id
     */
    @ApiOperation("启用照片")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openPictureEntity(@ApiParam(value = "获取要启用照片的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        navPictureEntityService.openPictureEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 审核不通过
     * @param id
     */
    @ApiOperation("审核不通过")
    @PostMapping(value = "/notPass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> notPassPictureEntity(@ApiParam(value = "获取要审核照片的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        navPictureEntityService.notPassPictureEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    /**
     * 审核通过
     * @param id
     */
    @ApiOperation("审核通过")
    @PostMapping(value = "/pass", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> passPictureEntity(@ApiParam(value = "获取要审核照片的id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        navPictureEntityService.passPictureEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }


}
