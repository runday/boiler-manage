package com.itdreamworks.boilermanage.service;

import com.itdreamworks.boilermanage.entity.BoilerCustomer;
import com.itdreamworks.boilermanage.entity.ProductAboutModel;
import com.itdreamworks.boilermanage.mapper.BoilerCustomerMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultCode;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class BoilerCustomerService {

    @Autowired
    private BoilerCustomerMapper boilerCustomerMapper;

    /**
     * 批量插入数据
     * @param productAboutModel
     * @return
     */
    public Result<BoilerCustomer> insertManyBoilerCustomer(ProductAboutModel<BoilerCustomer> productAboutModel){
        int insertNum=0;
        try{
            /*数据验证*/
            if(productAboutModel.validateModelGetResult().getCode()== ResultCode.FAIL.getCode()) return productAboutModel.validateModelGetResult();
            //每次100条数据
            int perCount = 100, index = 0;
            //执行的次数
            List<BoilerCustomer> boilerCustomerList=productAboutModel.getList();
            int times = boilerCustomerList.size() / perCount;
            do{
                Thread.sleep(50);
                List<BoilerCustomer> tempBoilerCustomerList=null;
                if(boilerCustomerList.size()>=perCount){
                    tempBoilerCustomerList=boilerCustomerList.subList(0,perCount);
                }else{
                    tempBoilerCustomerList=boilerCustomerList.subList(0,boilerCustomerList.size());
                }
                insertNum+=boilerCustomerMapper.insertManyBoilerCustomer(tempBoilerCustomerList,productAboutModel.getOrgId(),productAboutModel.getOrgType());
                boilerCustomerList.removeAll(tempBoilerCustomerList);
                index++;
            }while (index<=times);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResultGenerator.genSuccessResult("共"+insertNum+"条");
    }

}
