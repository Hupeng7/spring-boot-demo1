package com.demo.designpatterns.commondemo.starategydemo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName BFileResolve
 * @Description
 * @Author H
 * @Date 2021/12/16 11:52
 * @Version 1.0
 */
@Component
@Slf4j
public class BFileResolve implements IFileStrategy {
    @Override
    public FileTypeResolveEnum gainFileType() {
        return FileTypeResolveEnum.File_B_RESOLVE;
    }

    @Override
    public void resolve(Object objectParam) {
        log.info("B类型解析文件，参数： {}", objectParam);
    }
}
