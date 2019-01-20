package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.Enterprise;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnterpriseMapper {
    @Select("<script>" +
            "select * from bksrDB.Enterprise "+
            "<where>"+
            " 1=1 "+
            "<if test='enterpriseName != null and enterpriseName.length>0 '> "+
            " AND EnterpriseName LIKE CONCAT(CONCAT('%',#{enterpriseName}),'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Enterprise> getEnterpriseListByCondition(Enterprise enterprise);

    @Update("update bksrDB.Enterprise set EnterpriseName=#{enterpriseName},Status=#{status} where Id = #{id}")
    void updateEnterprise(Enterprise enterprise);

    @Insert("insert into bksrDB.Enterprise (EnterpriseName,Status) values (#{enterpriseName},#{status})")
    void insertEnterprise(Enterprise enterprise);

    @Delete("delete from bksrDB.Enterprise where Id=#{id}")
    void deleteEnterpriseById(Integer id);
}
