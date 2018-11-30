package com.msql.dynamicdatasource.controller;

import com.msql.dynamicdatasource.common.ResponseResult;
import com.msql.dynamicdatasource.modal.User;
import com.msql.dynamicdatasource.service.UserService;
import com.msql.dynamicdatasource.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUser/{userId}")
    public ResponseResult getUser(@PathVariable("userId") Long userId) throws ServiceException {
        return ResponseResult.build(userService.select(userId));
    }


    @GetMapping
    public ResponseResult getAllUser() {
        return ResponseResult.build(userService.getAllUser());
    }


    @PutMapping("/updateUser/{userId}")
    public ResponseResult updateUser(@PathVariable("userId") Long userId, @RequestBody User user) throws ServiceException {
        return ResponseResult.build(userService.update(userId, user));
    }


    @DeleteMapping("/deleteUser/{userId}")
    public ResponseResult deleteUser(@PathVariable("userId") long userId) throws ServiceException {

        return ResponseResult.build(userService.delete(userId));
    }


    @PostMapping
    public ResponseResult addUser(@RequestBody User user) throws ServiceException {
        return ResponseResult.build(userService.add(user));
    }
}
