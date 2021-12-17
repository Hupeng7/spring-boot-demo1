package com.demo.designpatterns.commondemo.starategydemo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName DefaultFileResolve
 * @Description
 * @Author H
 * @Date 2021/12/16 11:54
 * @Version 1.0
 */
@Component
@Slf4j
public class DefaultFileResolve implements IFileStrategy {
    @Override
    public FileTypeResolveEnum gainFileType() {
        return FileTypeResolveEnum.File_DEFAULT_RESOLVE;
    }

    @Override
    public void resolve(Object objectParam) {
        log.info("默认类型解析文件，参数： {}", objectParam);
    }
}
