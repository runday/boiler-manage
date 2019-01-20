package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRoleMapper {

    @Insert("<script>"+
            "insert into User_Role(UserId,RoleId)"
            + "values "
            + "<foreach collection =\"userRoleList\" item=\"userRole\" index=\"index\" separator =\",\"> "
            + "(#{userRole.userId},#{userRole.roleId}) "
            + "</foreach > " +
            "</script>")
    void insertManyUserRole(@Param("userRoleList") List<UserRole> userRoleList);

    @Delete("delete from User_Role where UserId=#{userId}")
    void deleteUserRoleByUserId(@Param("userId") Integer userId);
}
