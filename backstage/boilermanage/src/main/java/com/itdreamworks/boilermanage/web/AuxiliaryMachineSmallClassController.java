package com.itdreamworks.boilermanage.web;

import com.itdreamworks.boilermanage.entity.AuxiliaryMachineSmallClass;
import com.itdreamworks.boilermanage.mapper.AuxiliaryMachineSmallClassMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auxiliarymachinesmallclass")
public class AuxiliaryMachineSmallClassController {

    @Autowired
    AuxiliaryMachineSmallClassMapper auxiliaryMachineSmalllassMapper;

    /**
     * 查询数据列表-不带分页
     * @param auxiliaryMachineSmalllass
     * @return
     */
    @GetMapping(value = "/auxiliarymachinesmallclasslistbycondition")
    public Result getAuxiliaryMachineSmallClassListByCondition(AuxiliaryMachineSmallClass auxiliaryMachineSmalllass) {
        return ResultGenerator.genSuccessResult(auxiliaryMachineSmalllassMapper.getAuxiliaryMachineSmallClassListByCondition(auxiliaryMachineSmalllass));
    }

    /**
     * 编辑数据
     * @param auxiliaryMachineSmalllass
     * @return
     */
    @PostMapping("/editauxiliarymachinesmallclass")
    public Result editAuxiliaryMachineSmallClass(@RequestBody AuxiliaryMachineSmallClass auxiliaryMachineSmalllass){
        if(auxiliaryMachineSmalllass.getId()!=null){
            auxiliaryMachineSmalllassMapper.updateAuxiliaryMachineSmallClass(auxiliaryMachineSmalllass);
        }else{
            auxiliaryMachineSmalllassMapper.insertAuxiliaryMachineSmallClass(auxiliaryMachineSmalllass);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 通过Id删除数据
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteauxiliarymachinesmallclassbyid")
    public Result deleteAuxiliaryMachineSmallClassById(@RequestParam int id){
        auxiliaryMachineSmalllassMapper.deleteAuxiliaryMachineSmallClassById(id);
        return ResultGenerator.genSuccessResult();
    }
}
