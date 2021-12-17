package com.demo.designpatterns.commondemo.starategydemo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName AFileResolve
 * @Description
 * @Author H
 * @Date 2021/12/16 11:47
 * @Version 1.0
 */
@Component
@Slf4j
public class AFileResolve implements IFileStrategy {
    @Override
    public FileTypeResolveEnum gainFileType() {
        return FileTypeResolveEnum.File_A_RESOLVE;
    }

    @Override
    public void resolve(Object objectParam) {
        log.info("A类型解析文件,参数： {}", objectParam);
        // do something
    }
}
