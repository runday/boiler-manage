package com.itdreamworks.boilermanage.web;

import com.itdreamworks.boilermanage.entity.Resource;
import com.itdreamworks.boilermanage.mapper.ResourceMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 查询资源列表
     * @param resource
     * @return
     */
    @GetMapping(value = "/resourcelistbycondition")
    public Result getResourceListByCondition(Resource resource,Integer userId) {
        return ResultGenerator.genSuccessResult(resourceMapper.getResourceListByCondition(resource,userId));
    }

    /**
     * 通过角色Id获得角色资源列表
     * @param roleId
     * @return
     */
    @GetMapping(value = "/resourceresidlistbyroleid")
    public Result getResourceResIdListByRoleId(Integer roleId) {
        return ResultGenerator.genSuccessResult(resourceMapper.getResourceResIdListByRoleId(roleId));
    }

    /**
     * 编辑资源
     * @param resource
     * @return
     */
    @PostMapping("/editresource")
    public Result editResource(@RequestBody Resource resource){
        if(resource.getResId()!=null){
            resourceMapper.updateResource(resource);
        }else{
            resourceMapper.insertResource(resource);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除资源
     * @param resId
     * @return
     */
    @PostMapping(value = "/deleteresourcebyid")
    public Result deleteResourceById(@RequestParam int resId){
        resourceMapper.deleteResourceById(resId);
        return ResultGenerator.genSuccessResult();
    }
}
