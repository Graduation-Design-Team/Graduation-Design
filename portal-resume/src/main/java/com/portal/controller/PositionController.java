package com.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.Position;
import com.portal.service.PositionService;
import com.portal.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "/resume/showPositionById/{position_id}",method = RequestMethod.GET)
    public Position showPositionById(@PathVariable("position_id")Integer id){
        return  positionService.showPositionById(id);
    }

    @RequestMapping(value = "/resume/showPositionAll",method = RequestMethod.GET)
    public List<Position> showPositionAll(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Position> mylist = positionService.showPositionAll();
        PageInfo<Position> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }

    @RequestMapping(value = "resume/selectPositionFive",method = RequestMethod.GET)
    public List<Position> selectPositionFive(Position position,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Position> myfivelist = positionService.selectPositionFive(position);
        PageInfo<Position> pageInfo = new PageInfo<>(myfivelist);
        return pageInfo.getList();
    }

    //Redis     实现    热度搜索   ，根据缓存
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    //每次点击给相关词searchkey热度 +1
    public int incrementScore(String searchkey) {
        String key = searchkey;
        Long now = System.currentTimeMillis();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        zSetOperations.incrementScore("technical_position", key, 1);
        valueOperations.getAndSet(key, String.valueOf(now));
        return 1;
    }

    @RequestMapping(value = "/incrementScoreByUserId" ,method = RequestMethod.POST)
    @ResponseBody
    public int incrementScoreByUserId(String searchkey) {
        Long now = System.currentTimeMillis();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        List<String> title = new ArrayList<>();
        title.add(searchkey);
        for (int i = 0, lengh = title.size(); i < lengh; i++) {
            String tle = title.get(i);
            try {
                if (zSetOperations.score("technical_position", tle) <= 0) {
                    zSetOperations.add("technical_position", tle, 0);
                    valueOperations.set(tle, String.valueOf(now));
                }
            } catch (Exception e) {
                zSetOperations.add("technical_position", tle, 0);
                valueOperations.set(tle, String.valueOf(now));
            }
        }
        return 1;
    }

    @RequestMapping(value = "/resume/hotsearch",method = RequestMethod.POST)
    public List<String> hotSearch(@RequestParam String searchkey){

        int i = incrementScore(searchkey);
        System.out.println(i);

        String key = searchkey;
        Long now = System.currentTimeMillis();
        List<String> result = new ArrayList<>();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Set<String> value = zSetOperations.reverseRangeByScore("technical_position", 0, Double.MAX_VALUE);
        //key不为空的时候 推荐相关的最热前十名
        if(StringUtils.isNotEmpty(searchkey)){
            for (String val : value) {
                if (StringUtils.containsIgnoreCase(val, key)) {
                    if (result.size() > 9) {//只返回最热的前十名
                        break;
                    }
                    Long time = Long.valueOf(valueOperations.get(val));
                    if ((now - time) < 2592000000L) {//返回最近一个月的数据
                        result.add(val);
                    } else {//时间超过一个月没搜索就把这个词热度归0
                        zSetOperations.add("technical_position", val, 0);
                    }
                }
            }
        }else {
            for (String val : value) {
                if (result.size() > 9) {//只返回最热的前十名
                    break;
                }
                Long time = Long.valueOf(valueOperations.get(val));
                if ((now - time) < 2592000000L) {//返回最近一个月的数据
                    result.add(val);
                } else {//时间超过一个月没搜索就把这个词热度归0
                    zSetOperations.add("technical_position", val, 0);
                }
            }

        }
        return result;
    }



    }

