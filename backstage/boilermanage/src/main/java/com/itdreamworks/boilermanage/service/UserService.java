package com.itdreamworks.boilermanage.service;

import com.itdreamworks.boilermanage.entity.Resource;
import com.itdreamworks.boilermanage.entity.User;
import com.itdreamworks.boilermanage.mapper.ResourceMapper;
import com.itdreamworks.boilermanage.mapper.RoleMapper;
import com.itdreamworks.boilermanage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

@CacheConfig(cacheNames = {"resourceList"})
@Component
public class UserService {

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 集合当中的数据去重并且排序
     * @param resourceList
     * @return
     */
    private List<Resource> removeDuplicateAndSortById(List<Resource> resourceList) {
        Set<Resource> resourceSet = new TreeSet<>((o1, o2) -> o1.getResId().compareTo(o2.getResId()));
        resourceSet.addAll(resourceList);
        List<Resource> tempResourceList=new ArrayList<>(resourceSet);
        Collections.sort(tempResourceList,new Comparator () {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Resource && o2 instanceof Resource) {
                    Resource e1 = (Resource) o1;
                    Resource e2 = (Resource) o2;
                    return e1.getSort() - e2.getSort();
                }
                throw new ClassCastException("不能转换为Emp类型");
            }
        });
        return tempResourceList;
    }

    @Cacheable(key = "'getloginuserinfo_'+#loginid")
    public User getLoginUserInfo(String loginid){
        User user=userMapper.getLoginUserInfo(loginid);
        List<Resource> resourceList =resourceMapper.getResourceListByUserId(user.getId());
        resourceList.addAll(resourceMapper.getResourceListByOrganizationUserId(user.getId()));
        user.setListResource(removeDuplicateAndSortById(resourceList));
        user.setRoleList(roleMapper.getRoleListByUserId(user.getId()));
        return user;
    }

    public List<User> getUserListByOrgAndRole(User user){
        return userMapper.getUserListByOrgAndRole(user);
    }
}
