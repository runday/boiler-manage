package com.itdreamworks.boilermanage.entity;

import com.itdreamworks.boilermanage.util.Result;
import com.itdreamworks.boilermanage.util.ResultGenerator;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

public class ProductAboutModel<T> implements Serializable {

    private String orgType,orgId,userId;
    private List<T> list;

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public <T> Result<T> validateModelGetResult(){
        try {
            if(StringUtils.isEmpty(this.getOrgId())){
                return ResultGenerator.genFailResult("组织Id不能为空");
            }
            if(StringUtils.isEmpty(this.getOrgType())){
                return ResultGenerator.genFailResult("组织类型不能为空");
            }
            if(StringUtils.isEmpty(this.getUserId())){
                return ResultGenerator.genFailResult("用户Id不能为空");
            }
        }catch (Exception e){
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
}
