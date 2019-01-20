package com.itdreamworks.boilermanage.web;

import com.itdreamworks.boilermanage.entity.CustomerUser;
import com.itdreamworks.boilermanage.mapper.CustomerUserMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customeruser")
public class CustomerUserController {

    @Autowired
    private CustomerUserMapper customerUserMapper;

    /**
     * 通过客户Id获得用户Id列表
     * @param customerId
     * @return
     */
    @GetMapping("/getuseridlistbycustomerid")
    public Result getUserIdListByCustomerId(@RequestParam(name = "customerId") Integer customerId){
        return ResultGenerator.genSuccessResult(customerUserMapper.getUserIdListByCustomerId(customerId));
    }

}
