package com.itdreamworks.boilermanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdreamworks.boilermanage.entity.Dictionary;
import com.itdreamworks.boilermanage.mapper.DictionaryMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 查询字典列表
     * @param dictionary
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/dictionarylistbycondition")
    public Result getDictionaryListByCondition(Dictionary dictionary, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dictionary> list =dictionaryMapper.getDictionaryListByCondition(dictionary);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 编辑字典
     * @param dictionary
     * @return
     */
    @PostMapping("/editdictionary")
    public Result editDictionary(@RequestBody Dictionary dictionary){
        if(dictionary.getId()!=null){
            dictionaryMapper.updateDictionary(dictionary);
        }else{
            dictionaryMapper.insertDictionary(dictionary);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @PostMapping(value = "/deletedictionarybyid")
    public Result deleteDictionaryById(@RequestParam int id){
        dictionaryMapper.deleteDictionaryById(id);
        return ResultGenerator.genSuccessResult();
    }
}
