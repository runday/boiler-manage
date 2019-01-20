package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.CustomerResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerResourceMapper {
    
    @Insert("<script>"+
            "insert into Customer_Resource(CustomerId,resourceId)"
            + "values "
            + "<foreach collection =\"customerResourceList\" item=\"customerResource\" index=\"index\" separator =\",\"> "
            + "(#{customerResource.customerId},#{customerResource.resourceId}) "
            + "</foreach > " +
            "</script>")
    void insertManyCustomerResource(@Param("customerResourceList") List<CustomerResource> customerResourceList);

    @Select("<script>select cr.ResourceId from Customer_Resource cr left join Resource r on r.ResId=cr.ResourceId where cr.CustomerId=#{customerId} and r.PId<![CDATA[ <> ]]>0</script>")
    List<String> getResourceIdListByCustomerId(@Param("customerId") Integer customerId);

    @Delete("delete from Customer_Resource where CustomerId=#{customerId}")
    void deleteCustomerResourceByCustomerId(@Param("customerId") Integer customerId);
}
