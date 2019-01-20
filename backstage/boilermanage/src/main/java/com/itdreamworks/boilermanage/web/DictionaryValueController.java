package com.itdreamworks.boilermanage.web;
import com.itdreamworks.boilermanage.entity.DictionaryValue;
import com.itdreamworks.boilermanage.mapper.DictionaryValueMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dictionaryvalue")
public class DictionaryValueController {

    @Autowired
    private DictionaryValueMapper dictionaryValueMapper;


    @GetMapping(value = "/dictionaryvaluelistbytype")
    public Result getDictionaryValueListByType(String type){
        return  ResultGenerator.genSuccessResult(dictionaryValueMapper.getDictionaryValueListByType(type));
    }

    /**
     * 编辑字典值
     * @param dictionaryValue
     * @return
     */
    @PostMapping("/editdictionaryvalue")
    public Result editDictionaryValue(@RequestBody DictionaryValue dictionaryValue){
        if(dictionaryValue.getId()!=null){
            dictionaryValueMapper.updateDictionaryValue(dictionaryValue);
        }else{
            dictionaryValueMapper.insertDictionaryValue(dictionaryValue);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除字典值
     * @param id
     * @return
     */
    @PostMapping(value = "/deletedictionaryvaluebyid")
    public Result deleteDictionaryValueById(@RequestParam int id){
        dictionaryValueMapper.deleteDictionaryValueById(id);
        return ResultGenerator.genSuccessResult();
    }

}
