package com.portal.controller;

import com.portal.pojo.User;
import com.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;

    @RequestMapping(value = "/user/adduser",method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user){
        return userservice.addUser(user);
    }

    @RequestMapping(value = "/user/queryuser/{user_id}",method = RequestMethod.GET)
    public User queryUser(@PathVariable("user_id")Long id){
        return userservice.queryUser(id);
    }

    @RequestMapping(value = "/user/queryall",method = RequestMethod.GET)
    public List<User> queryUserAll(){
        return userservice.queryUserAll();
    }

    @RequestMapping(value = "/user/addUserSelective",method = RequestMethod.POST)
    public Boolean addUserSelective(@RequestBody User user){
        return userservice.addUserSelective(user);
    }

    @RequestMapping(value = "/user/deleteUser/{user_id}",method = RequestMethod.DELETE)
    public Boolean deleteUser(@PathVariable("user_id") Long id){
        return userservice.deleteUser(id);
    }

    @RequestMapping(value = "/user/updateUserSelective",method = RequestMethod.POST)
    public Boolean updateUserSelective(@RequestBody User user){
        return userservice.updateUserSelective(user);
    }

    @RequestMapping(value = "/user/updateUserAll",method = RequestMethod.PUT)
    public Boolean updateUserAll(@RequestBody User user){
        return userservice.updateUserAll(user);
    }



    @Value("${file.path}")
    private String filePath;
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Boolean upload(
            @RequestParam("user_photo_url")MultipartFile file, HttpServletRequest request
    ){

        System.out.println("文件的地址是---  ---" + filePath);
        String username = request.getParameter("name");
        System.out.println("使用的用户名字是---  ---" + username);

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (file.isEmpty() && file.getSize()>30720){
            return false;
        }
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
            return userservice.uploadUserImg(filePath+fileName);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return userservice.uploadUserImg(filePath+fileName);
    }







}
