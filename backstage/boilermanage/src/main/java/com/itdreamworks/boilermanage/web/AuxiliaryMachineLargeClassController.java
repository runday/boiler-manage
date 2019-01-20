package com.itdreamworks.boilermanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdreamworks.boilermanage.entity.AuxiliaryMachineLargeClass;
import com.itdreamworks.boilermanage.mapper.AuxiliaryMachineLargeClassMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auxiliarymachinelargeclass")
public class AuxiliaryMachineLargeClassController {

    @Autowired
    AuxiliaryMachineLargeClassMapper auxiliaryMachineLargeClassMapper;
    
    /**
     * 查询数据列表-带分页
     * @param auxiliaryMachineLargeClass
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/auxiliarymachinelargeclasslistbyconditionandpage")
    public Result getAuxiliaryMachineLargeClassListByConditionAndPage(AuxiliaryMachineLargeClass auxiliaryMachineLargeClass, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuxiliaryMachineLargeClass> list =auxiliaryMachineLargeClassMapper.getAuxiliaryMachineLargeClassListByCondition(auxiliaryMachineLargeClass);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询数据列表-不带分页
     * @param auxiliaryMachineLargeClass
     * @return
     */
    @GetMapping(value = "/auxiliarymachinelargeclasslistbycondition")
    public Result getAuxiliaryMachineLargeClassListByCondition(AuxiliaryMachineLargeClass auxiliaryMachineLargeClass) {
        return ResultGenerator.genSuccessResult(auxiliaryMachineLargeClassMapper.getAuxiliaryMachineLargeClassListByCondition(auxiliaryMachineLargeClass));
    }

    /**
     * 编辑数据
     * @param auxiliaryMachineLargeClass
     * @return
     */
    @PostMapping("/editauxiliarymachinelargeclass")
    public Result editAuxiliaryMachineLargeClass(@RequestBody AuxiliaryMachineLargeClass auxiliaryMachineLargeClass){
        if(auxiliaryMachineLargeClass.getId()!=null){
            auxiliaryMachineLargeClassMapper.updateAuxiliaryMachineLargeClass(auxiliaryMachineLargeClass);
        }else{
            auxiliaryMachineLargeClassMapper.insertAuxiliaryMachineLargeClass(auxiliaryMachineLargeClass);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 通过Id删除数据
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteauxiliarymachinelargeclassbyid")
    public Result deleteAuxiliaryMachineLargeClassById(@RequestParam int id){
        auxiliaryMachineLargeClassMapper.deleteAuxiliaryMachineLargeClassById(id);
        return ResultGenerator.genSuccessResult();
    }
}
