package com.itdreamworks.boilermanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdreamworks.boilermanage.entity.BoilerCustomer;
import com.itdreamworks.boilermanage.entity.ProductAboutModel;
import com.itdreamworks.boilermanage.mapper.BoilerCustomerMapper;
import com.itdreamworks.boilermanage.service.BoilerCustomerService;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.util.List;
@RestController
@RequestMapping(value = "/boilercustomer")
public class BoilerCustomerController {

    @Autowired
    private BoilerCustomerMapper boilerCustomerMapper;

    @Autowired
    private BoilerCustomerService boilerCustomerService;

    /**
     * 查询客户
     * @param boilerCustomer
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/boilercustomerlistbyconditionandpage")
    public Result getBoilerCustomerListByConditionAndPage(BoilerCustomer boilerCustomer, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BoilerCustomer> list =boilerCustomerMapper.getBoilerCustomerListByCondition(boilerCustomer);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询客户
     * @param boilerCustomer
     * @return
     */
    @GetMapping(value = "/boilercustomerlistbycondition")
    public Result getBoilerCustomerListByCondition(BoilerCustomer boilerCustomer) {
        return ResultGenerator.genSuccessResult(boilerCustomerMapper.getBoilerCustomerListByCondition(boilerCustomer));
    }

    /**
     * 编辑客户
     * @param boilerCustomer
     * @return
     */
    @PostMapping("/editboilercustomer")
    public Result editBoilerCustomer(@RequestBody BoilerCustomer boilerCustomer){
        if(boilerCustomer.getId()!=null){
            boilerCustomerMapper.updateBoilerCustomer(boilerCustomer);
        }else{
            boilerCustomerMapper.insertBoilerCustomer(boilerCustomer);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/insertmanyboilercustomer")
    public Result insertManyBoilerCustomer(@RequestBody ProductAboutModel<BoilerCustomer> productAboutModel){
        return boilerCustomerService.insertManyBoilerCustomer(productAboutModel);
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteboilercustomerbyid")
    public Result deleteBoilerCustomerById(@RequestParam int id){
        boilerCustomerMapper.deleteBoilerCustomerById(id);
        return ResultGenerator.genSuccessResult();
    }
}
