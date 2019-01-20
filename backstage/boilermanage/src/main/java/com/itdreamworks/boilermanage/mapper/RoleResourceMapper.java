package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.RoleResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleResourceMapper {

    @Insert("<script>"+
            "insert into Role_Resource(RoleId,ResId)"
            + "values "
            + "<foreach collection =\"roleResourcesList\" item=\"roleResources\" index=\"index\" separator =\",\"> "
            + "(#{roleResources.roleId},#{roleResources.resId}) "
            + "</foreach > " +
            "</script>")
    void insertManyRoleResource(@Param("roleResourcesList") List<RoleResource> roleResourcesList);

    @Delete("delete from Role_Resource where RoleId=#{roleId}")
    void deleteRoleResourceByRoleId(@Param("roleId") Integer roleId);
}
