package com.example.exp.Controller;

import com.example.exp.Pojo.UpdateReq;
import com.example.exp.ResponseResult.ResponseResult;
import com.example.exp.Service.UserService;
import com.example.exp.Entity.User;
import com.example.exp.Pojo.SearchReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: usercontroller
 * @Author: zsp
 * @Date: 2023/6/23 0:48
 */

@Api(tags = "操作user表")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户信息
     * @param user
     */
    @ApiOperation("添加user")
    @PostMapping(value = "add")
    public void save(@Validated @RequestBody User user) {
        //保存数据
        userService.save(user);
    }

    /**
     * 查找用户
     * @param req
     */
    @ApiOperation("查找user")
    @PostMapping(value = "search")
    public ResponseResult search(@Validated @RequestBody SearchReq req) {
        return userService.search(req.getId());
    }


    /**
     * 查找用户
     * @param req
     */
    @ApiOperation("更新user")
    @PostMapping(value = "update")
    public ResponseResult update(@Validated @RequestBody UpdateReq req) {
        return userService.updateUser(req);
    }


}
