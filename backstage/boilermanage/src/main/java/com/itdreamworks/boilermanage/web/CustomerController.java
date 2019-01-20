package com.itdreamworks.boilermanage.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdreamworks.boilermanage.entity.Customer;
import com.itdreamworks.boilermanage.entity.CustomerResource;
import com.itdreamworks.boilermanage.entity.CustomerUser;
import com.itdreamworks.boilermanage.mapper.CustomerMapper;
import com.itdreamworks.boilermanage.mapper.CustomerResourceMapper;
import com.itdreamworks.boilermanage.mapper.CustomerUserMapper;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerResourceMapper customerResourceMapper;

    @Autowired
    private CustomerUserMapper customerUserMapper;

    /**
     * 查询客户列表-分页
     * @param customer
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/customerlistbyconditionandpage")
    public Result getCustomerListByConditionAndPage(Customer customer, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Customer> list =customerMapper.getCustomerListByCondition(customer);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询客户列表-不分页
     * @param customer
     * @return
     */
    @GetMapping(value = "/customerlistbycondition")
    public Result getCustomerListByCondition(Customer customer) {
        return ResultGenerator.genSuccessResult(customerMapper.getCustomerListByCondition(customer));
    }

    /**
     * 编辑客户
     * @param customer
     * @return
     */
    @PostMapping("/editcustomer")
    public Result editCustomer(@RequestBody Customer customer){
        if(customer.getId()!=null){
            customerMapper.updateCustomer(customer);
        }else{
            customerMapper.insertCustomer(customer);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 插入多个客户资源数据
     * @param customer
     * @return
     */
    @PostMapping("/insertmanycustomerresource")
    public Result<CustomerResource> insertManyCustomerResource(@RequestBody Customer customer){
        if(null!=customer.getId()){
            customerResourceMapper.deleteCustomerResourceByCustomerId(customer.getId());
        }
        if(null!=customer.getCustomerResourceList()&&customer.getCustomerResourceList().size()>0){
            customerResourceMapper.insertManyCustomerResource(customer.getCustomerResourceList());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 插入多个客户用户数据
     * @param customer
     * @return
     */
    @PostMapping("/insertmanycustomeruser")
    public Result<CustomerUser> insertManyCustomerUser(@RequestBody Customer customer){
        if(null!=customer.getId()){
            customerUserMapper.deleteCustomerUserByCustomerId(customer.getId());
        }
        if(null!=customer.getCustomerUserList()&&customer.getCustomerUserList().size()>0){
            customerUserMapper.insertManyCustomerUser(customer.getCustomerUserList());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @PostMapping(value = "/deletecustomerbyid")
    public Result deleteCustomerById(@RequestParam int id){
        customerMapper.deleteCustomerById(id);
        return ResultGenerator.genSuccessResult();
    }
}
