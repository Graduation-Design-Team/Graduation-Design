package com.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.Cate;
import com.portal.service.CateService;
import com.portal.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cate")
public class CateController {
    @Autowired
    private CateService cateService;

    /**
     * 管理员发布一条美食餐饮信息
     */
    @PostMapping("/publish")
    public String PublishCate(String cateTitle, String cateContent, Integer typeId, MultipartFile img, Integer userId) throws IOException {
        Cate cate = new Cate();
        cate.setCateTitle(cateTitle);
        cate.setCateContent(cateContent);
        cate.setTypeId(typeId);
        String path = ImageUtils.upload(img);
        cate.setImg(path);
        Integer i = cateService.addCate(cate, userId);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 根据分页查询美食餐饮列表
     */
    @GetMapping("/list")
    public PageInfo<Cate> cateList(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 7);
        List<Cate> cates = cateService.cateList();
        PageInfo<Cate> catePageInfo = new PageInfo<>(cates);
        return catePageInfo;
    }

    /**
     * 根据id查询该美食餐饮详情
     */
    @GetMapping("/detail")
    public Cate getCateById(Integer cateId) {
        return cateService.getCateById(cateId);
    }

    /**
     * 根据cateId逻辑删除该条美食餐饮信息
     */
    @PutMapping("/delete")
    public String deleteNewsById(Integer cateId) {
        Integer i = cateService.deleteCateById(cateId);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 管理员修改美食餐饮信息
     */
    @PutMapping("/update")
    public String editCate(Cate cate, Integer userId) {
        Integer i = cateService.updateCate(cate, userId);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 管理员修改美食餐饮标题图片
     */
    @PutMapping("/update-picture")
    public String editCatePicture(MultipartFile img, Integer cateId, Integer userId) throws IOException {
        String path = ImageUtils.upload(img);
        Integer i = cateService.updateCatePicture(path, cateId, userId);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

}