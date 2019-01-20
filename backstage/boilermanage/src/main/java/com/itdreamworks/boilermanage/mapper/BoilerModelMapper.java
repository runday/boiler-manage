package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.BoilerModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoilerModelMapper {

    @Select("<script>" +
            "select * from Boiler_Model "+
            "<where>"+
            "<if test='orgType!= null'> "+
            " AND OrgType=#{orgType} "+
            "</if>"+
            "<if test='orgId!= null and orgId.length>0'> "+
            " and OrgId=#{orgId} "+
            "</if>"+
            "<if test='label!= null and label.length>0 '> "+
            " AND Label LIKE CONCAT(CONCAT('%',#{label}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<BoilerModel> getBoilerModelListByCondition(BoilerModel boilerModel);

    @Select("select * from Boiler_Model where Label=#{label} and OrgType=#{orgType} and OrgId=#{orgId} ")
    BoilerModel getBoilerModelValueByLabel(@Param("label") String label, @Param("orgType") Integer orgType, @Param("orgId") String orgId);

    @Select("select count(*) from Boiler_Model where OrgType=#{orgType} and OrgId=#{orgId} and Label=#{label} ")
    int isExistOfBoilerModel(@Param("orgId") String orgId, @Param("orgType") Integer orgType, @Param("label") String label);

    @Update("update Boiler_Model set Label=#{label} where Id = #{id}")
    void updateBoilerModel(BoilerModel BoilerModel);

    @Insert("insert into Boiler_Model (Label,Value,OrgType,OrgId,Sort) values (#{label},#{value},#{orgType},#{orgId},#{sort})")
    void insertBoilerModel(BoilerModel BoilerModel);

    @Insert("<script>"+
            "insert ignore into Boiler_Model(Label,Value,OrgType,OrgId,Sort)"
            + "values "
            + "<foreach collection =\"boilerModelList\" item=\"boilerModel\" index=\"index\" separator =\",\"> "
            + "(#{boilerModel.label},#{boilerModel.value},#{orgType},#{orgId},#{boilerModel.sort}) "
            + "</foreach > " +
            "</script>")
    int insertManyBoilerModel(@Param("boilerModelList") List<BoilerModel> boilerModelList, @Param("orgId") String orgId, @Param("orgType") String orgType);


    @Delete("delete from Boiler_Model where Id=#{id}")
    void deleteBoilerModelById(Integer id);

    @Select("select max(Value) from Boiler_Model where OrgType=#{orgType} and OrgId=#{orgId}")
    @ResultType(Integer.class)
    Integer getMaxValue(BoilerModel BoilerModel);

    @Select("select max(Sort) from Boiler_Model where OrgType=#{orgType} and OrgId=#{orgId}")
    @ResultType(Integer.class)
    Integer getMaxSort(BoilerModel BoilerModel);
}
