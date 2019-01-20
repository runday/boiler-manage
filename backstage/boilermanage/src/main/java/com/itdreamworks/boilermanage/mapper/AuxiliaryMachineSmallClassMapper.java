package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.AuxiliaryMachineSmallClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuxiliaryMachineSmallClassMapper {
    @Select("<script>" +
            "select * from Auxiliary_Machine_Small_Class "+
            "<where>"+
            " 1=1 "+
            "<if test='largeClassId != null'> "+
            " AND LargeClassId=#{largeClassId}"+
            "</if>"+
            " order by Sort asc"+
            "</where>"+
            "</script>")
    List<AuxiliaryMachineSmallClass> getAuxiliaryMachineSmallClassListByCondition(AuxiliaryMachineSmallClass auxiliaryMachineSmallClass);

    @Update("update Auxiliary_Machine_Small_Class set LargeClassId=#{largeClassId},Name=#{name},Sort=#{sort} where Id = #{id}")
    void updateAuxiliaryMachineSmallClass(AuxiliaryMachineSmallClass auxiliaryMachineSmallClass);

    @Insert("insert into Auxiliary_Machine_Small_Class (LargeClassId,Name,Sort) values (#{largeClassId},#{name},#{sort})")
    void insertAuxiliaryMachineSmallClass(AuxiliaryMachineSmallClass auxiliaryMachineSmallClass);

    @Delete("delete from Auxiliary_Machine_Small_Class where Id=#{id}")
    void deleteAuxiliaryMachineSmallClassById(Integer id);

}
