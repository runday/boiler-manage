package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.ProductAuxiliaryMachineInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductAuxiliaryMachineInfoMapper {

    @Select("select * from Product_Auxiliary_Machine_Info where ProductId = #{productId}")
    List<ProductAuxiliaryMachineInfo> getProductAuxiliaryMachineInfoListByProductId(Integer productId);

    @Insert("<script>"+
            "insert into Product_Auxiliary_Machine_Info(ProductId,LargeClassId,SmallClassId,BrandName,ModelName,AmountOfUser,Supplier,Remarks)"
            + "values "
            + "<foreach collection =\"ProductAuxiliaryMachineInfoList\" item=\"productAuxiliaryMachineInfo\" index=\"index\" separator =\",\"> "
            + "(#{productId},#{productAuxiliaryMachineInfo.largeClassId},#{productAuxiliaryMachineInfo.smallClassId},#{productAuxiliaryMachineInfo.brandName},#{productAuxiliaryMachineInfo.modelName},#{productAuxiliaryMachineInfo.amountOfUser},#{productAuxiliaryMachineInfo.supplier},#{productAuxiliaryMachineInfo.remarks}) "
            + "</foreach > " +
            "</script>")
    void insertManyProductAuxiliaryMachineInfo(@Param("ProductAuxiliaryMachineInfoList") List<ProductAuxiliaryMachineInfo> ProductAuxiliaryMachineInfoList, @Param("productId") Integer productId);

    @Delete("delete from Product_Auxiliary_Machine_Info where ProductId=#{productId}")
    void deleteProductAuxiliaryMachineInfoByProductId(Integer productId);
}
