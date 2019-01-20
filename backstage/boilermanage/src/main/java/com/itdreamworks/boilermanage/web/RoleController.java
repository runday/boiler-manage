package com.itdreamworks.boilermanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdreamworks.boilermanage.entity.Role;
import com.itdreamworks.boilermanage.mapper.RoleMapper;
import com.itdreamworks.boilermanage.mapper.RoleResourceMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 查询角色列表
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/rolelistbycondition")
    public Result getRoleListByCondition(Role role,Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list =roleMapper.getRoleListByCondition(role,userId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

        /**
         * 通过用户id查询角色信息
         * @param userId
         * @return
         */
        @GetMapping(value = "/rolelistbyuserid")
        public Result getRoleListByUserId(Integer userId) {
            return ResultGenerator.genSuccessResult(roleMapper.getRoleListByCondition(new Role(),userId));
        }

    /**
     * 编辑角色
     * @param role
     * @return
    */
    @PostMapping("/editrole")
    public Result editRole(@RequestBody Role role){
        if(role.getRoleId()!=null){
            roleMapper.updateRole(role);
        }else{
            roleMapper.insertRole(role);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 编辑角色资源
     * @param role
     * @return
     */
    @PostMapping("/editroleresource")
    public Result editRoleResource(@RequestBody Role role){
        roleResourceMapper.deleteRoleResourceByRoleId(role.getRoleId());
        if(null!=role.getRoleResourceList()&&role.getRoleResourceList().size()>0)
            roleResourceMapper.insertManyRoleResource(role.getRoleResourceList());
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @PostMapping(value = "/deleterolebyid")
    public Result deleteRoleById(@RequestParam int roleId){
        roleMapper.deleteRoleById(roleId);
        return ResultGenerator.genSuccessResult();
    }
}
