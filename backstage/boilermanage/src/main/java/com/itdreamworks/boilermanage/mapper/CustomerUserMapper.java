package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.CustomerUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerUserMapper {

    @Insert("<script>"+
            "insert into Customer_User(CustomerId,UserId)"
            + "values "
            + "<foreach collection =\"customerUserList\" item=\"customerUser\" index=\"index\" separator =\",\"> "
            + "(#{customerUser.customerId},#{customerUser.userId}) "
            + "</foreach > " +
            "</script>")
    void insertManyCustomerUser(@Param("customerUserList") List<CustomerUser> customerUserList);

    @Select("select cu.UserId from Customer_User cu where cu.CustomerId=#{customerId}")
    List<String> getUserIdListByCustomerId(@Param("customerId") Integer customerId);

    @Delete("delete from Customer_User where CustomerId=#{customerId}")
    void deleteCustomerUserByCustomerId(@Param("customerId") Integer customerId);
}
