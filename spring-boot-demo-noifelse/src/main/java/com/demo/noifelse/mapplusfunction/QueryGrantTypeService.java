package com.demo.noifelse.mapplusfunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName QueryGrantTypeService
 * @Description
 * @Author H
 * @Date 2022/7/6 11:31
 * @Version 1.0
 */
@Service
public class QueryGrantTypeService {

    @Autowired
    private GrantTypeService grantTypeService;
    private Map<String, Function<String, String>> grantTypeMap = new HashMap<>();

    @PostConstruct
    public void dispatcherInit() {
        grantTypeMap.put("红包", resourceId -> grantTypeService.redPaper(resourceId));
        grantTypeMap.put("购物券", resourceId -> grantTypeService.shopping(resourceId));
        grantTypeMap.put("qq会员", resourceId -> grantTypeService.QQVip(resourceId));
    }

    public String getResult(String resourceType){
        Function<String, String> result = grantTypeMap.get(resourceType);
        if (result != null) {
            return result.apply(resourceType);
        }

        return "查询不到该优惠券的发放方式";
    }

}
