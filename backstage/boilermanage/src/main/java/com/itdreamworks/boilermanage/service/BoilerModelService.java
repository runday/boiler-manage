package com.itdreamworks.boilermanage.service;

import com.itdreamworks.boilermanage.entity.BoilerModel;
import com.itdreamworks.boilermanage.entity.ProductAboutModel;
import com.itdreamworks.boilermanage.mapper.BoilerModelMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultCode;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class BoilerModelService {

    @Autowired
    private BoilerModelMapper boilerModelMapper;

    public void setBoilerModelOtherValue(ProductAboutModel<BoilerModel> productAboutModel){
        List<BoilerModel> boilerModelList=productAboutModel.getList();
        int maxSort=(boilerModelMapper.getMaxSort(boilerModelList.get(0))==null?0:boilerModelMapper.getMaxSort(boilerModelList.get(0)))+1;
        int maxValue=(boilerModelMapper.getMaxValue(boilerModelList.get(0))==null?0:boilerModelMapper.getMaxValue(boilerModelList.get(0)))+1;
        for (int i=0;i<boilerModelList.size();i++){
            boilerModelList.get(i).setSort((maxSort+i));
            boilerModelList.get(i).setValue((maxValue+i));
        }
    }

    /**
     * 批量插入数据
     * @param productAboutModel
     * @return
     */
    public Result<BoilerModel> insertManyBoilerModel(ProductAboutModel<BoilerModel> productAboutModel){
        int insertNum=0;
        try{
            /*数据验证*/
            if(productAboutModel.validateModelGetResult().getCode()== ResultCode.FAIL.getCode()) return productAboutModel.validateModelGetResult();
            setBoilerModelOtherValue(productAboutModel);
            //每次100条数据
            int perCount = 100, index = 0;
            //执行的次数
            List<BoilerModel> boilerCustomerList=productAboutModel.getList();
            int times = boilerCustomerList.size() / perCount;
            do{
                Thread.sleep(50);
                List<BoilerModel> tempBoilerModelList=null;
                if(boilerCustomerList.size()>=perCount){
                    tempBoilerModelList=boilerCustomerList.subList(0,perCount);
                }else{
                    tempBoilerModelList=boilerCustomerList.subList(0,boilerCustomerList.size());
                }
                insertNum+=boilerModelMapper.insertManyBoilerModel(tempBoilerModelList,productAboutModel.getOrgId(),productAboutModel.getOrgType());
                boilerCustomerList.removeAll(tempBoilerModelList);
                index++;
            }while (index<=times);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResultGenerator.genSuccessResult("共"+insertNum+"条");
    }

}
