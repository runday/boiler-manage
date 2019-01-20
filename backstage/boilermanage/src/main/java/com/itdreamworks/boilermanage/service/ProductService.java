package com.itdreamworks.boilermanage.service;

import com.itdreamworks.boilermanage.entity.*;
import com.itdreamworks.boilermanage.mapper.*;
import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultCode;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductUserMapper productUserMapper;

    @Autowired
    ProductAuxiliaryMachineInfoMapper productAuxiliaryMachineInfoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private BoilerModelMapper boilerModelMapper;

    @Autowired
    private BoilerCustomerMapper boilerCustomerMapper;

    @Autowired
    RoleMapper roleMapper;


    public void insertProductAndProductUser(Product product){
        if(product.getId()!=null){
            productMapper.updateProduct(product);
            productAuxiliaryMachineInfoMapper.deleteProductAuxiliaryMachineInfoByProductId(product.getId());
        }else{
            productMapper.insertProduct(product);
            List<Integer> roleIdArray=product.getRoleIdArray();
            if(roleIdArray.size()>0&&(!roleIdArray.contains(Product.ROLE_ADMIN))){
                User user=new User();
                user.setRoleId(Product.ROLE_ADMIN);
                user.setOrgType(product.getOrgType());
                user.setOrgId(product.getOrgId());
                List<User> userAdminList=userService.getUserListByOrgAndRole(user);
                List<ProductUser> productUserList=new ArrayList<>();
                for (int i=0;i<userAdminList.size();i++){
                    ProductUser TempProductUser=new ProductUser();
                    TempProductUser.setProductId(product.getId());
                    TempProductUser.setUserId(userAdminList.get(i).getId());
                    productUserList.add(TempProductUser);
                }
                productUserMapper.insertManyProductUser(productUserList);
            }
            ProductUser productUser=new ProductUser();
            productUser.setUserId(product.getUserId());
            productUser.setProductId(product.getId());
            productUserMapper.inserProductUser(productUser);
        }
        if(product.getProductAuxiliaryMachineInfoList()!=null&&product.getProductAuxiliaryMachineInfoList().size()>0){
            productAuxiliaryMachineInfoMapper.insertManyProductAuxiliaryMachineInfo(product.getProductAuxiliaryMachineInfoList(),product.getId());
        }
    }

    /**
     * 添加多个产品
     * @param productAboutModel
     * @return
     */
    public Result insertManyProduct(ProductAboutModel<Product> productAboutModel)  {
        int insertTotalNum=0;
        try {
            /*数据验证*/
            if(productAboutModel.validateModelGetResult().getCode()== ResultCode.FAIL.getCode()) return productAboutModel.validateModelGetResult();
            for (int i=0;i<productAboutModel.getList().size();i++){
                Product product=productAboutModel.getList().get(i);
                product.setOrgId(productAboutModel.getOrgId());
                product.setOrgType(Integer.parseInt(productAboutModel.getOrgType()));
                product.setUserId(Integer.parseInt(productAboutModel.getUserId()));
                //获得锅炉型号值
                BoilerModel boilerModel=boilerModelMapper.getBoilerModelValueByLabel(product.getBoilerModelNumberName(),product.getOrgType(),product.getOrgId());
                if(null!=boilerModel&&!StringUtils.isEmpty(boilerModel.getValue())){
                    product.setBoilerModelNumber(boilerModel.getValue());
                }
                if(!StringUtils.isEmpty(product.getBoilerCustomerNo())){
                    BoilerCustomer boilerCustomer=boilerCustomerMapper.getBoilerCustomerByCustomerNo(product.getBoilerCustomerNo(),product.getOrgType(),product.getOrgId());
                    if(null!=boilerCustomer){
                        product.setBoilerCustomerId(boilerCustomer.getId());
                        product.setBoilerCustomerName(boilerCustomer.getName());
                    }
                }
                Product tempProduct=productMapper.getProductByBoilerNo(product.getBoilerNo(),product.getControllerNo());
                if(null!=tempProduct){
                    continue;
                }
                List<Role> roleList=roleMapper.getRoleListByUserId(product.getUserId());
                Boolean isAdmin=false;
                for (int j=0;j<roleList.size();j++){
                    Role role=roleList.get(j);
                    if(role.getRoleId()==Product.ROLE_ADMIN){
                        isAdmin=true;
                        break;
                    }
                }
                int insertNum=productMapper.insertProduct(product);
                insertTotalNum+=insertNum;
                if(!isAdmin){
                    User user=new User();
                    user.setRoleId(Product.ROLE_ADMIN);
                    user.setOrgType(product.getOrgType());
                    user.setOrgId(product.getOrgId());
                    List<User> userAdminList=userService.getUserListByOrgAndRole(user);
                    List<ProductUser> productUserList=new ArrayList<>();
                    for (int j=0;j<userAdminList.size();j++){
                        ProductUser TempProductUser=new ProductUser();
                        TempProductUser.setProductId(product.getId());
                        TempProductUser.setUserId(userAdminList.get(j).getId());
                        productUserList.add(TempProductUser);
                    }
                    productUserMapper.insertManyProductUser(productUserList);
                }
                ProductUser productUser=new ProductUser();
                productUser.setUserId(product.getUserId());
                productUser.setProductId(product.getId());
                productUserMapper.inserProductUser(productUser);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResultGenerator.genSuccessResult("共"+insertTotalNum+"条");
    }
}
