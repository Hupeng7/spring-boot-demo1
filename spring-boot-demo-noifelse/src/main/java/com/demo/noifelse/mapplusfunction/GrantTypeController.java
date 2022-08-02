package com.demo.noifelse.mapplusfunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GrantTypeController
 * @Description
 * @Author H
 * @Date 2022/7/6 11:51
 * @Version 1.0
 */
@RestController
public class GrantTypeController {

    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @PostMapping("/grantType")
    public String test(String resourceType) {
        return queryGrantTypeService.getResult(resourceType);
    }
}
