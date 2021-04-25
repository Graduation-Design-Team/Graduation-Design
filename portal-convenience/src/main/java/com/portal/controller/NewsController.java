package com.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.exception.MyException;
import com.portal.pojo.News;
import com.portal.service.NewsService;
import com.portal.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @PostMapping("publish")
    public String PublishNews(String newsTitle, String newsContent, MultipartFile img, Integer userId) throws IOException {
        News news = new News();
        news.setNewsTitle(newsTitle);
        news.setNewsContent(newsContent);

        String path = ImageUtils.upload(img);
        news.setImg(path);
        Integer i = newsService.addNews(news, userId);

        if (i != 1) {
            return "error";
        }
        return "success";

    }

    @GetMapping("list")
    public PageInfo<News> newsList(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 7);
        List<News> list = newsService.newsList();
        PageInfo<News> newsPageInfo = new PageInfo<>(list);
        return newsPageInfo;
    }

    @GetMapping("detail")
    public News getNewsByid(Integer newsId) {
        return newsService.getNewsById(newsId);
    }

    @PutMapping("/delete")
    public String deleteNewsById(Integer newsId) {
        Integer i = newsService.deleteNewsById(newsId);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    /**
     * 管理员修改新闻
     */
    @PutMapping("/update")
    public String editNews(News news, Integer userId) {
        Integer i = newsService.updateNews(news, userId);
        if (i != 1) {
            return "error";
        }
        return "success";
    }

    @GetMapping("/test")
    public String test(Integer i) throws Exception {

        //String upload = ImageUtils.upload(img);
        if (i != 1) {
            throw new MyException("111", "222");
        }
        return "asd";
    }
}
