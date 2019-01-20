package com.itdreamworks.boilermanage.web;

import com.itdreamworks.boilermanage.mapper.ProductAuxiliaryMachineInfoMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/productauxiliarymachineinfo")
public class ProductAuxiliaryMachineInfoController {

    @Autowired
    ProductAuxiliaryMachineInfoMapper productAuxiliaryMachineInfoMapper;

    @GetMapping(value = "/productauxiliarymachineinfolistbyproductid")
    public Result getProductAuxiliaryMachineInfoListByProductId(Integer productId){
        return  ResultGenerator.genSuccessResult(productAuxiliaryMachineInfoMapper.getProductAuxiliaryMachineInfoListByProductId(productId));
    }
}
