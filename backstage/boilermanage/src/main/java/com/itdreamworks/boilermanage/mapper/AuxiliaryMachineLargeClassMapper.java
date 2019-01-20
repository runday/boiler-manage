package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.AuxiliaryMachineLargeClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuxiliaryMachineLargeClassMapper {

    @Select("<script>" +
            "select * from Auxiliary_Machine_Large_Class "+
            "<where>"+
            " 1=1 "+
            "<if test='name != null and name.length>0'> "+
            " AND Name LIKE CONCAT(CONCAT('%',#{name}),'%')"+
            "</if>"+
            "</where>"+
            " order by Sort asc"+
            "</script>")
    List<AuxiliaryMachineLargeClass> getAuxiliaryMachineLargeClassListByCondition(AuxiliaryMachineLargeClass auxiliaryMachineLargeClass);

    @Update("update Auxiliary_Machine_Large_Class set Name=#{name},Sort=#{sort} where Id = #{id}")
    void updateAuxiliaryMachineLargeClass(AuxiliaryMachineLargeClass auxiliaryMachineLargeClass);

    @Insert("insert into Auxiliary_Machine_Large_Class (Name,Sort) values (#{name},#{sort})")
    void insertAuxiliaryMachineLargeClass(AuxiliaryMachineLargeClass auxiliaryMachineLargeClass);

    @Delete("delete from Auxiliary_Machine_Large_Class where Id=#{id}")
    void deleteAuxiliaryMachineLargeClassById(Integer id);

}
