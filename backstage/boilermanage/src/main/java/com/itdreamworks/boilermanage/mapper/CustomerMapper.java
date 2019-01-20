package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerMapper {

    @Select("<script>" +
            "select * from bksrDB.Customer "+
            "<where>"+
            " 1=1 "+
            "<if test='customerName != null and customerName.length>0 '> "+
            " AND CustomerName LIKE CONCAT(CONCAT('%',#{customerName}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Customer> getCustomerListByCondition(Customer customer);

    @Update("update bksrDB.Customer set EnterpriseId=#{enterpriseId},CustomerName=#{customerName},Status=#{status},CustomerNo=#{customerNo} where Id = #{id}")
    void updateCustomer(Customer customer);

    @Insert("insert into bksrDB.Customer (EnterpriseId,CustomerName,Status,CustomerNo) values (#{enterpriseId},#{customerName},#{status},#{customerNo})")
    void insertCustomer(Customer customer);

    @Delete("delete from bksrDB.Customer where Id=#{id}")
    void deleteCustomerById(Integer id);
}
