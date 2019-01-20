package com.itdreamworks.boilermanage.mapper;

import com.itdreamworks.boilermanage.entity.Dictionary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryMapper {

    @Select("<script>" +
            "select * from Dictionary "+
            "<where>"+
            "1=1 "+
            "</where>"+
            "</script>")
    List<Dictionary> getDictionaryListByCondition(Dictionary dictionary);

    @Update("update Dictionary set Name=#{name},Type=#{type},Sort=#{sort} where Id = #{id}")
    void updateDictionary(Dictionary dictionary);

    @Insert("insert into Dictionary (Name,Type,Sort) values (#{name},#{type},#{sort})")
    void insertDictionary(Dictionary dictionary);

    @Delete("delete from Dictionary where Id=#{id}")
    void deleteDictionaryById(Integer id);
}
