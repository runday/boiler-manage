package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {

    @Select("<script>" +
            "select DISTINCT r.* from Role r left join User_Role ur on ur.RoleId=r.RoleId"+
            "<where>"+
            "1=1 "+
            "<if test='userId != null'> "+
            " AND ur.UserId =#{userId}"+
            "</if>"+
            "<if test='role.roleName != null and role.roleName.length>0 '> "+
            " AND RoleName LIKE CONCAT(CONCAT('%',#{role.roleName}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Role> getRoleListByCondition(@Param("role") Role role, @Param("userId") Integer userId);

    @Select("select * from Role where RoleId=#{roleId}")
    Role findOneById(@Param("id") int id);

    @Update("update Role set roleName=#{roleName},RoleDesc=#{roleDesc} where RoleId = #{roleId}")
    void updateRole(Role role);

    @Insert("insert into Role (RoleName,RoleDesc) values (#{roleName},#{roleDesc})")
    void insertRole(Role role);

    @Delete("delete from Role where RoleId=#{roleId}")
    void deleteRoleById(Integer roleId);

    @Select("select * from Role r LEFT JOIN User_Role u on u.RoleId = r.RoleId where u.UserId =#{useId}")
    List<Role> getRoleListByUserId(Integer userId);
}
