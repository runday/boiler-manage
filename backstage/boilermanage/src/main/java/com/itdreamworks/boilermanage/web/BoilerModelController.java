package com.itdreamworks.boilermanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdreamworks.boilermanage.entity.BoilerCustomer;
import com.itdreamworks.boilermanage.entity.BoilerModel;
import com.itdreamworks.boilermanage.entity.ProductAboutModel;
import com.itdreamworks.boilermanage.mapper.BoilerModelMapper;
import com.itdreamworks.boilermanage.service.BoilerModelService;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/boilermodel")
public class BoilerModelController {

    @Autowired
    private BoilerModelMapper boilerModelMapper;

    @Autowired
    BoilerModelService boilerModelService;
    /**
     * 查询数据-分页
     * @param boilerModel
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/boilermodellistbyconditionandpage")
    public Result getBoilerModelListByConditionAndPage(BoilerModel boilerModel, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BoilerModel> list =boilerModelMapper.getBoilerModelListByCondition(boilerModel);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询数据-不带分页
     * @param boilerModel
     * @return
     */
    @GetMapping(value = "/boilermodellistbycondition")
    public Result getBoilerModelListByCondition(BoilerModel boilerModel) {
        return ResultGenerator.genSuccessResult(boilerModelMapper.getBoilerModelListByCondition(boilerModel));
    }

    /**
     * 编辑数据
     * @param boilerModel
     * @return
     */
    @PostMapping("/editboilermodel")
    public Result editBoilerModel(@RequestBody BoilerModel boilerModel){
        if(boilerModel.getId()!=null){
            boilerModelMapper.updateBoilerModel(boilerModel);
        }else{
            boilerModel.setSort((boilerModelMapper.getMaxSort(boilerModel)==null?0:boilerModelMapper.getMaxSort(boilerModel))+1);
            boilerModel.setValue((boilerModelMapper.getMaxValue(boilerModel)==null?0:boilerModelMapper.getMaxValue(boilerModel))+1);
            boilerModelMapper.insertBoilerModel(boilerModel);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/insertmanyboilermodel")
    public Result<BoilerModel> insertManyBoilerModel(@RequestBody ProductAboutModel<BoilerModel> productAboutModel){
        return boilerModelService.insertManyBoilerModel(productAboutModel);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteboilermodelbyid")
    public Result deleteBoilerModelById(@RequestParam int id){
        boilerModelMapper.deleteBoilerModelById(id);
        return ResultGenerator.genSuccessResult();
    }
}
