package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.Resource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResourceMapper {

    @Select("select DISTINCT re.* from Resource re " +
            "LEFT JOIN Role_Resource rr on rr.ResId = re.ResId " +
            "LEFT JOIN User_Role ur on rr.RoleId = ur.RoleId " +
            "LEFT JOIN bksrDB.Employee ee on ee.Id = ur.UserId " +
            "where ee.Id=#{userId} order by Sort asc")
    List<Resource> getResourceListByUserId(Integer userId);

    @Select("<script>" +
            "select  DISTINCT re.* from Resource re "+
            "LEFT JOIN Role_Resource rr on rr.ResId = re.ResId " +
            "LEFT JOIN User_Role ur on rr.RoleId = ur.RoleId " +
            "LEFT JOIN bksrDB.Employee ee on ee.Id = ur.UserId " +
            "<where>"+
            " 1=1 "+
            "<if test='userId != null'> "+
            " AND ur.UserId =#{userId} "+
            "</if>"+
            "</where>"+
            " order by Sort asc"+
            "</script>")
    List<Resource> getResourceListByCondition(Resource Resource, @Param("userId") Integer userId);

    @Select("select DISTINCT r.* from Resource r " +
            " LEFT JOIN Customer_Resource cr on cr.ResourceId=r.ResId " +
            " LEFT JOIN Customer_User cu on cu.CustomerId=cr.CustomerId " +
            "where cu.UserId=#{userId} order by Sort asc")
    List<Resource> getResourceListByOrganizationUserId(Integer userId);

    @Select("<script>select re.ResId from Resource re LEFT JOIN Role_Resource rr on rr.ResId = re.ResId where rr.RoleId=#{roleId} and re.PId<![CDATA[ <> ]]>0</script>")
    List<Resource> getResourceResIdListByRoleId(@Param("roleId") Integer roleId);

    @Select("select * from Resource where ResId=#{resId}")
    Resource findOneById(@Param("resId") int resId);

    @Update("update Resource set ResName=#{resName},PId=#{pId} ,URL=#{url},PageUrl=#{pageUrl},Hidden=#{hidden},Permission=#{permission},Sort=#{sort} where ResId=#{resId}")
    void updateResource(Resource Resource);

    @Insert("insert into Resource (ResName,PId,URL,PageUrl,Hidden,Permission,Sort) values (#{resName},#{pId},#{url},#{pageUrl},#{hidden},#{permission},#{sort})")
    void insertResource(Resource Resource);

    @Delete("delete from Resource where ResId=#{resId}")
    void deleteResourceById(Integer resId);

}
