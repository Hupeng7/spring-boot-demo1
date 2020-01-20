package com.demo.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.demo.codegen.common.R;
import com.demo.codegen.entity.GenConfig;
import com.demo.codegen.entity.TableRequest;
import com.demo.codegen.service.CodeGenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CodeGenController
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 17:09
 * @Version 1.0
 */

@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class CodeGenController {

    private final CodeGenService codeGenService;

    @GetMapping("/table")
    public R listTables(TableRequest request) {
        return R.success(codeGenService.listTables(request));
    }

    /**
     * 生成代码
     *
     * @param genConfig
     * @param response
     */
    @SneakyThrows
    @PostMapping("")
    public void generatorCode(@RequestBody GenConfig genConfig, HttpServletResponse response) {
        byte[] data = codeGenService.generatorCode(genConfig);

        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=%s.zip", genConfig.getTableName()));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream;charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }


}
