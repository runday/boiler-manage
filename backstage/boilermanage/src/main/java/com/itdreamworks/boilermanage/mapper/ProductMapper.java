package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductMapper {

    @Select("<script>" +
            "select DISTINCT pt.* from Product pt"+
            " left join Product_User pu on pu.ProductId=pt.Id "+
            "<where>"+
            " 1=1 "+
            "<if test='userId != null'> "+
            " AND pu.UserId=#{userId} "+
            "</if>"+
            "<if test='saleDate != null'> "+
            " AND SaleDate=DATE_FORMAT(DATE_ADD(#{saleDate},INTERVAL 1 DAY), '%Y-%m-%d') "+
            "</if>"+
            "<if test='boilerNo != null and boilerNo.length>0'> "+
            " AND BoilerNo LIKE CONCAT(CONCAT('%',#{boilerNo}),'%')"+
            "</if>"+
            "<if test='boilerModelNumber != null'> "+
            " AND BoilerModelNumber=#{boilerModelNumber}"+
            "</if>"+
            "<if test='controllerNo != null and controllerNo.length>0'> "+
            " AND ControllerNo LIKE CONCAT(CONCAT('%',#{controllerNo}),'%')"+
            "</if>"+
            "<if test='tonnageNum != null'> "+
            " AND TonnageNum=#{tonnageNum}"+
            "</if>"+
            "<if test='medium != null'> "+
            " AND Medium=#{medium}"+
            "</if>"+
            "<if test='fuel != null'> "+
            " AND Fuel=#{fuel}"+
            "</if>"+
            "</where>"+
            " order by EditDateTime desc"+
            "</script>")
    List<Product> getProductListByCondition(Product Product);

    @Select("select * from Product where BoilerNo=#{boilerNo} and ControllerNo=#{controllerNo}")
    Product getProductByBoilerNo(@Param("boilerNo") String boilerNo, @Param("controllerNo") String controllerNo);

    @Insert("INSERT into Product(BoilerNo,BoilerModelNumber,ControllerNo,TonnageNum,Medium,Fuel,IsSell,SaleDate,Longitude,Latitude,Province,City,District,Street,CreateDateTime,EditDateTime,BoilerCustomerId,BoilerCustomerName) " +
            " VALUES(#{boilerNo},#{boilerModelNumber},#{controllerNo},#{tonnageNum},#{medium},#{fuel},#{isSell},DATE_FORMAT(DATE_ADD(#{saleDate},INTERVAL 1 DAY), '%Y-%m-%d'),#{longitude},#{latitude},#{province},#{city}," +
            "#{district},#{street},#{createDateTime},#{editDateTime},#{boilerCustomerId},#{boilerCustomerName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertProduct(Product product);

    @Update("update Product set BoilerNo=#{boilerNo},BoilerModelNumber=#{boilerModelNumber},ControllerNo=#{controllerNo}, TonnageNum=#{tonnageNum},Medium=#{medium}," +
            "Fuel=#{fuel},IsSell=#{isSell},SaleDate=DATE_FORMAT(DATE_ADD(#{saleDate},INTERVAL 1 DAY), '%Y-%m-%d'),Longitude=#{longitude},Latitude=#{latitude},Province=#{province},City=#{city}," +
            "District=#{district},Street=#{street},EditDateTime=#{editDateTime},BoilerCustomerId=#{boilerCustomerId},BoilerCustomerName=#{boilerCustomerName}" +
            " where Id=#{id}")
    void updateProduct(Product Product);

    @Update("update Product set IsSell=#{isSell},SaleDate=DATE_FORMAT(DATE_ADD(#{saleDate},INTERVAL 1 DAY), '%Y-%m-%d'),Longitude=#{longitude},Latitude=#{latitude},Province=#{province},City=#{city}," +
            "District=#{district},Street=#{street},EditDateTime=#{editDateTime},BoilerCustomerId=#{boilerCustomerId},BoilerCustomerName=#{boilerCustomerName}" +
            " where Id=#{id}")
    void updateProductSellAbout(Product product);

    @Delete("delete from Product where Id =#{id}")
    void deleteProductById(int id);
}
