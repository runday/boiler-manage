package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.DictionaryValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryValueMapper {

    @Select("select Id,Label,Value,Sort,Type from Dictionary_Value where type = #{type} order by Sort asc")
    List<DictionaryValue> getDictionaryValueListByType(String type);

    @Update("update Dictionary_Value set Type=#{type},Label=#{label},Value=#{value},Sort=#{sort} where Id = #{id}")
    void updateDictionaryValue(DictionaryValue dictionaryValue);

    @Insert("insert into Dictionary_Value (Type,Label,Value,Sort) values (#{type},#{label},#{value},#{sort})")
    void insertDictionaryValue(DictionaryValue dictionaryValue);

    @Delete("delete from Dictionary_Value where Id=#{id}")
    void deleteDictionaryValueById(Integer id);

}
