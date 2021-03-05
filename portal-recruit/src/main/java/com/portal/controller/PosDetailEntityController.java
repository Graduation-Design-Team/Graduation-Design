package com.portal.controller;


import com.portal.pojo.PosDetailEntity;
import com.portal.service.PosDetailEntityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.PatternUtils;

import java.util.List;

@RestController
@RequestMapping("/PosDetail")
public class PosDetailEntityController {

    @Autowired
    private PosDetailEntityService posDetailEntityService;


    @ApiOperation("根据职位类别id查询职位")
    @GetMapping(value = "/search", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<List<PosDetailEntity>> getPosDetail(@ApiParam(value = "接收传递过来的职位所属类别id", required = true) String id,
                                                              @ApiParam(value = "接收传递过来的当前页", required = true) String curPage,
                                                              @ApiParam(value = "接收传递过来的每页显示条数", required = true) String size) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        if (curPage == null || !PatternUtils.isNum(curPage) || Long.parseLong(curPage) < 0) {
            throw new RuntimeException("curPage不合法");
        }
        if (size == null || !PatternUtils.isNum(size) || Long.parseLong(size) < 0) {
            throw new RuntimeException("size不合法");
        }
        List<PosDetailEntity> list = posDetailEntityService.getPosDetail(Long.parseLong(id), Integer.parseInt(curPage), Integer.parseInt(size));
        return ResponseEntity.ok(list);
    }


    /**
     * 前端Content-Type需要设置为：application/x-www-form-urlencoded
     *
     * @param posId
     * @param detailName
     * @param detailContent
     * @param id
     * @param count
     * @return
     */
    @ApiOperation("添加职位详细信息")
    @PostMapping(value = "/add", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> addPosDetail(@ApiParam(value = "职位所属类别id", required = true) String posId,
                                             @ApiParam(value = "职位名字", required = true) String detailName,
                                             @ApiParam(value = "职位内容", required = true) String detailContent,
                                             @ApiParam(value = "城市id", required = true) String id,
                                             @ApiParam(value = "职位的数量", required = true) String count,
                                             @ApiParam(value = "职位的类型id", required = true) String typeId) {

        if (posId == null || !PatternUtils.isNum(posId) || Long.parseLong(posId) < 0) {
            throw new RuntimeException("posId不合法");
        }
        if (detailName == null) {
            throw new RuntimeException("detailName不合法");
        }
        if (detailContent == null) {
            throw new RuntimeException("detailContent不合法");
        }
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        if (count == null || !PatternUtils.isNum(count) || Long.parseLong(count) < 0) {
            throw new RuntimeException("count不合法");
        }
        if (typeId == null || !PatternUtils.isNum(typeId) || Long.parseLong(typeId) < 0) {
            throw new RuntimeException("typeId不合法");
        }
        PosDetailEntity posDetailEntity = null;
        //职位类别不存在的话也无需添加数据
        if (!posDetailEntityService.existsPosCategory(Long.parseLong(posId))) {
            return ResponseEntity.badRequest().build();
        }
        //往中间表插入数据 如果根据城市id无法查询的话就不添加数据
        if (!posDetailEntityService.existsCityByid(Long.parseLong(id))) {
            return ResponseEntity.badRequest().build();
        }
        //往中间表插入数据，如果根据工作类别id无法查找到相应的类别的话也无需添加
        if (!posDetailEntityService.existsPosDetailWorkTypeById(Long.parseLong(typeId))) {
            return ResponseEntity.badRequest().build();
        }
        //添加职位详细表的信息
        posDetailEntity = new PosDetailEntity(Long.parseLong(posId), detailName, detailContent, (byte) 0);
        //添加中间表的信息
        posDetailEntityService.addPosDetail(posDetailEntity, Long.parseLong(id), Long.parseLong(count), Long.parseLong(typeId));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("更新职位信息")
    @PostMapping(value = "/update", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> updatePosDetail(
            @ApiParam(value = "职位的id", required = true) String detailId,
            @ApiParam(value = "职位所属类别id", required = true) String posId,
            @ApiParam(value = "职位名字", required = true) String detailName,
            @ApiParam(value = "职位内容", required = true) String detailContent,
            @ApiParam(value = "城市id", required = true) String id,
            @ApiParam(value = "职位的数量", required = true) String count,
            @ApiParam(value = "职位的类型id", required = true) String typeId) {

        if (detailId == null || !PatternUtils.isNum(detailId) || Long.parseLong(detailId) < 0) {
            throw new RuntimeException("detailId不合法");
        }
        if (posId == null || !PatternUtils.isNum(posId) || Long.parseLong(posId) < 0) {
            throw new RuntimeException("posId不合法");
        }
        if (detailName == null) {
            throw new RuntimeException("detailName不合法");
        }
        if (detailContent == null) {
            throw new RuntimeException("detailContent不合法");
        }
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        if (count == null || !PatternUtils.isNum(count) || Long.parseLong(count) < 0) {
            throw new RuntimeException("count不合法");
        }
        if (typeId == null || !PatternUtils.isNum(typeId) || Long.parseLong(typeId) < 0) {
            throw new RuntimeException("typeId不合法");
        }
        PosDetailEntity posDetailEntity = null;
        //职位不存在的话无需更新信息
        if (!posDetailEntityService.existsPosDetailById(Long.parseLong(detailId))) {
            throw new RuntimeException("detailId不存在");
        }
        //职位类别不存在的话也无需更新数据
        if (!posDetailEntityService.existsPosCategory(Long.parseLong(posId))) {
            throw new RuntimeException("posId不存在");
        }
        //往中间表更新数据 如果根据城市id无法查询的话就不更新数据
        if (!posDetailEntityService.existsCityByid(Long.parseLong(id))) {
            throw new RuntimeException("id不存在");
        }

        //往中间表插入数据，如果根据工作类别id无法查找到的话也无需修改
        if (!posDetailEntityService.existsPosDetailWorkTypeById(Long.parseLong(typeId))) {
            throw new RuntimeException("typeId不存在");
        }
        //更新职位详细表的信息
        posDetailEntityService.updatePosDetail(new PosDetailEntity(Long.parseLong(detailId), Long.parseLong(posId), detailName, detailContent, (byte) 0));
        //更新中间表的信息
        posDetailEntityService.updateCityPosDetail(Long.parseLong(detailId), Long.parseLong(id), Long.parseLong(count), Long.parseLong(typeId));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("删除职位信息")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deletePosDetail(@ApiParam(value = "接收要删除职位的id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        posDetailEntityService.deletePosDetail(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("启用被删除的职位")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openPosDetail(@ApiParam(value = "接收要启用的职位id", required = true) String id) {
        if (id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        posDetailEntityService.openPosDetail(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
