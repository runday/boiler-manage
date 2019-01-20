package com.itdreamworks.boilermanage.web;

import com.itdreamworks.boilermanage.entity.CustomerResource;
import com.itdreamworks.boilermanage.mapper.CustomerResourceMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customerresource")
public class CustomerResourceController {

    @Autowired
    private CustomerResourceMapper customerResourceMapper;

    /**
     * 通过客户Id获得资源Id列表
     * @param customerId
     * @return
     */
    @GetMapping("/getresourceidlistbycustomerid")
    public Result getResourceIdListByCustomerId(@RequestParam(name = "customerId") Integer customerId){
        return ResultGenerator.genSuccessResult(customerResourceMapper.getResourceIdListByCustomerId(customerId));
    }

}
