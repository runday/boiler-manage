package com.itdreamworks.boilermanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdreamworks.boilermanage.entity.User;
import com.itdreamworks.boilermanage.mapper.UserMapper;
import com.itdreamworks.boilermanage.mapper.UserRoleMapper;
import com.itdreamworks.boilermanage.service.UserService;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserService userService;


    @GetMapping("/getloginuserinfo")
    public Result getLoginUserInfo(@RequestParam(name = "loginid") String loginid){
        return ResultGenerator.genSuccessResult(userService.getLoginUserInfo(loginid));
    }

    /**
     * 获得用户列表的数据
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/userlistbyconditionandpage")
    public Result getUserListByConditionAndPage(User user, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<User> list =userMapper.getUserListByCondition(user);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获得用户列表数据
     * @param user
     * @return
     */
    @GetMapping("/userlistbycondition")
    public Result getUserListByCondition(User user){
        List<User> list =userMapper.getUserListByCondition(user);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 通过组织类型和组织Id获得用户数据列表
     * @param orgType
     * @param orgId
     * @return
     */
    @GetMapping("/getuserlistbyorganizationtypeandid")
    public Result getUserListByOrganizationTypeAndId(@RequestParam(name = "orgType") Integer orgType,@RequestParam(name = "orgId") Integer orgId){
        return ResultGenerator.genSuccessResult(userMapper.getUserListByOrganizationTypeAndId(orgType,orgId));
    }

    /**
     * 编辑用户角色
     * @param user
     * @return
     */
    @PostMapping("/edituserrole")
    public Result editUserRole(@RequestBody User user){
        userRoleMapper.deleteUserRoleByUserId(user.getId());
        userRoleMapper.insertManyUserRole(user.getUserRoleList());
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 编辑用户
     * @param user
     * @return
     */
    @PostMapping("/edituser")
    public Result editUser(@RequestBody User user){
        if(user.getId()!=null){
            userMapper.updateUser(user);
        }else{
            if(null!=user&&user.getMobile()!=null){
                String userMobile=userMapper.getUserMobileByMobile(user.getMobile());
                if(userMobile.equals(user.getMobile())){
                    return ResultGenerator.genFailResult("账户已经存在,请进行查询确认");
                }
            }
            userMapper.insertUser(user);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteuserbyid")
    public Result deleteUserById(@RequestParam int id){
        userMapper.deleteUserById(id);
        userRoleMapper.deleteUserRoleByUserId(id);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 编辑用户密码
     * @param user
     * @return
     */
    @PostMapping("/editUserPass")
    public Result editUserPass(@RequestBody User user){
        userMapper.updateUserPass(user);
        return ResultGenerator.genSuccessResult();
    }
}
