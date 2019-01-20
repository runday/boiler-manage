package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    @Select("select em.*," +
            "case em.OrgType " +
            "when 1 then com.CompanyName " +
            "when 2 then ent.EnterpriseName " +
            "when 3 then cut.CustomerName " +
            "end as organizationName " +
            "from bksrDB.Employee em " +
            "left join bksrDB.Enterprise ent on ent.Id=em.OrgId " +
            "left join bksrDB.Customer cut on cut.Id=em.OrgId " +
            "left join bksrDB.Company com on com.Id=em.OrgId " +
            "where em.mobile=#{loginId} or em.email=#{loginId}")
    User getLoginUserInfo(@Param("loginId") String loginId);

    @Select("select * from bksrDB.Employee em where em.mobile=#{loginId} or em.email=#{loginId}")
    User findOneByLoginId(@Param("loginId") String loginId);

    @Select("<script>" +
            "select em.* from bksrDB.Employee em "+
            "<where>"+
            " 1=1 "+
            "<if test='status != null'> "+
            " AND Status = #{status}"+
            "</if>"+
            "<if test='realName != null and realName.length>0 '> "+
            " AND RealName LIKE CONCAT(CONCAT('%',#{realName}),'%')"+
            "</if>"+
            "<if test='mobile != null and mobile.length>0 '> "+
            " AND Mobile LIKE CONCAT(CONCAT('%',#{mobile}),'%')"+
            "</if>"+
            "<if test='orgType ==3'> "+
            "AND OrgType=3  AND  OrgId=#{orgId}"+
            "</if>"+
            "<if test='orgType==1 or orgType==0'> "+
            "AND OrgType=3 "+
            "</if>"+
            "</where>"+
            "</script>")
    List<User> getUserListByCondition(User user);

    @Select("select em.* from bksrDB.Employee em "+
            " left join test_sdcsoft_db.User_Role ur on ur.UserId=em.Id where ur.RoleId=#{roleId} and OrgType=#{orgType} and OrgId=#{orgId}")
    List<User> getUserListByOrgAndRole(User user);

    @Select("select em.Id,em.RealName from bksrDB.Employee em where em.OrgType=#{orgType} and em.OrgId=#{orgId}")
    List<User> getUserListByOrganizationTypeAndId(@Param("orgType")Integer orgType,@Param("orgId")Integer orgId);

    @Select("select em.Mobile from bksrDB.Employee em where Mobile=#{mobile}")
    String getUserMobileByMobile(@Param("mobile")String mobile);

    @Insert("insert into bksrDB.Employee (OrgType,OrgId,Password,Mobile,Email,WeiXin,QQ,RealName,Status,LastLoginDatetime,Mark) values (#{orgType},#{orgId},#{password},#{mobile},#{email},#{weiXin},#{qQ},#{realName},#{status},#{lastLoginDatetime},#{mark})")
    void insertUser(User user);

    @Update("update bksrDB.Employee set OrgType=#{orgType},OrgId=#{orgId},Password=#{password},Mobile=#{mobile},Email=#{email},WeiXin=#{weiXin},QQ=#{qQ},RealName=#{realName},Status=#{status},LastLoginDatetime=#{lastLoginDatetime},Mark=#{mark} where Id = #{id}")
    int updateUser(User user);

    @Delete("delete from bksrDB.Employee where Id=#{id}")
    void deleteUserById(Integer id);

    @Update("update bksrDB.Employee set Password=#{password} where Id = #{id}")
    int updateUserPass(User user);
}