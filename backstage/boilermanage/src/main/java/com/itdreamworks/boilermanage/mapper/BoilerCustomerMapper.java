package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.BoilerCustomer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoilerCustomerMapper {

    @Select("<script>" +
            "select * from Boiler_Customer "+
            "<where>"+
            "<if test='orgType!= null'> "+
            " AND OrgType=#{orgType} "+
            "</if>"+
            "<if test='orgId!= null and orgId.length>0'> "+
            " and OrgId=#{orgId} "+
            "</if>"+
            "<if test='name!= null and name.length>0 '> "+
            " AND Name LIKE CONCAT(CONCAT('%',#{name}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<BoilerCustomer> getBoilerCustomerListByCondition(BoilerCustomer boilerCustomer);

    @Select("select * from Boiler_Customer where CustomerNo=#{customerNo} and OrgType=#{orgType} and OrgId=#{orgId}")
    BoilerCustomer  getBoilerCustomerByCustomerNo(@Param("customerNo") String customerNo, @Param("orgType") Integer orgType, @Param("orgId") String orgId);

    @Select("select count(*) from Boiler_Customer where OrgType=#{orgType} and OrgId=#{orgId} and CustomerNo=#{customerNo} ")
    int isExistOfBoilerCustomer(@Param("orgId") String orgId, @Param("orgType") Integer orgType, @Param("customerNo") String customerNo);

    @Update("update Boiler_Customer set CustomerNo=#{customerNo},Name=#{name},Phone=#{phone},WeiXin=#{weiXin},Province=#{province},City=#{city},District=#{district} where Id = #{id}")
    void updateBoilerCustomer(BoilerCustomer boilerCustomer);

    @Insert("insert into Boiler_Customer (CustomerNo,Name,Phone,WeiXin,Province,City,District,OrgType,OrgId) values (#{customerNo},#{name},#{phone},#{weiXin},#{province},#{city},#{district},#{orgType},#{orgId})")
    void insertBoilerCustomer(BoilerCustomer boilerCustomer);

    @Insert("<script>"+
            "insert ignore into Boiler_Customer(CustomerNo,Name,Phone,WeiXin,Province,City,District,OrgType,OrgId)"
            + "values "
            + "<foreach collection =\"boilerCustomerList\" item=\"boilerCustomer\" index=\"index\" separator =\",\"> "
            + "(#{boilerCustomer.customerNo},#{boilerCustomer.name},#{boilerCustomer.phone},#{boilerCustomer.weiXin},#{boilerCustomer.province},#{boilerCustomer.city},#{boilerCustomer.district},#{orgType},#{orgId}) "
            + "</foreach > " +
            "</script>")
    int insertManyBoilerCustomer(@Param("boilerCustomerList") List<BoilerCustomer> boilerCustomerList, @Param("orgId") String orgId, @Param("orgType") String orgType);

    @Delete("delete from Boiler_Customer where Id=#{id}")
    void deleteBoilerCustomerById(Integer id);
}
