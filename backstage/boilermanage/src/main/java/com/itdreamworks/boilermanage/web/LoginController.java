package com.itdreamworks.boilermanage.web;

import com.itdreamworks.boilermanage.entity.User;
import com.itdreamworks.boilermanage.mapper.UserMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class LoginController {

    @Autowired
    private UserMapper mapper;

    @PostMapping(value = "/signin")
    public Result signIn(@RequestParam(name = "loginid") String loginId, @RequestParam(name = "password") String password) {
        User user = mapper.findOneByLoginId(loginId);
        if (null == user)
            return ResultGenerator.genFailResult(0,"用户名或者密码输入错误");
        if (user.getPassword().equals(password)) {
            if (User.STATUS_ENABLE == user.getStatus()) {
                return ResultGenerator.genSuccessResult(1,"success",user);
            } else {
                return ResultGenerator.genFailResult(0,"您的用户账号未审核，请联系系统管理人员！");
            }
        } else {
            return ResultGenerator.genFailResult(0,"用户名或者密码输入错误");
        }
    }
}
