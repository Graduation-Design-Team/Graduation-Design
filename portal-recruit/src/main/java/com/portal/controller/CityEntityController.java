package com.portal.controller;


import com.portal.pojo.CityEntity;
import com.portal.service.CityEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.PatternUtils;

import java.util.List;

@Api("城市信息类接口")
@RestController
@RequestMapping("/city")
public class CityEntityController {

    @Autowired
    private CityEntityService cityEntityService;

    @ApiOperation("查找城市信息")
    @GetMapping("/search")
    public ResponseEntity<List<CityEntity>> getCityEntity() {
        List<CityEntity> list = cityEntityService.getCityEntity();
        return ResponseEntity.ok(list);
    }


    @ApiOperation("添加城市信息")
    @PostMapping(value = "/add", headers = "content-type=application/json")
    public ResponseEntity<Void> addCityEntity(@RequestBody @ApiParam(value = "接收传递过来的城市信息", required = true) CityEntity cityEntity) {
        if (cityEntity.getCityIsDel() == null) {
            throw new RuntimeException("cityIsDel不合法");
        }
        if (cityEntity.getCityName() == null) {
            throw new RuntimeException("cityName不合法");
        }
        cityEntity.setCityIsDel((byte) 0);
        cityEntityService.addCityEntity(cityEntity);
        return ResponseEntity.ok().build();
    }


    @ApiOperation("更新城市信息")
    @PostMapping(value = "/update", headers = "content-type=application/json")
    public ResponseEntity<Void> updateCityEntity(@RequestBody @ApiParam(value = "接收传递过来的城市信息", required = true) CityEntity cityEntity) {
        if (cityEntity.getCityId() == null) {
            throw new RuntimeException("cityId不合法");
        }
        if (cityEntity.getCityIsDel() == null) {
            throw new RuntimeException("cityIsDel不合法");
        }
        if (cityEntity.getCityName() == null) {
            throw new RuntimeException("cityName不合法");
        }
        cityEntityService.updateCityEntity(cityEntity);
        return ResponseEntity.ok().build();
    }


    @ApiOperation("删除城市信息")
    @PostMapping(value = "/delete", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> deleteCityEntity(@ApiParam(value = "接收要删除的城市id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        cityEntityService.deleteCityEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("启用删除的城市信息")
    @PostMapping(value = "/open", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> openCityEntity(@ApiParam(value = "接收要删除的城市id", required = true) String id) {
        if(id == null || !PatternUtils.isNum(id) || Long.parseLong(id) < 0) {
            throw new RuntimeException("id不合法");
        }
        cityEntityService.openCityEntity(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
