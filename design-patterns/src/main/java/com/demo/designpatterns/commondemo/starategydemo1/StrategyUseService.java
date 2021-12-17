package com.demo.designpatterns.commondemo.starategydemo1;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName StrategyUseService
 * @Description
 * @Author H
 * @Date 2021/12/16 11:58
 * @Version 1.0
 */
@Component
public class StrategyUseService implements ApplicationContextAware {
    private Map<FileTypeResolveEnum, IFileStrategy> iFileStrategyMap = new ConcurrentHashMap<>();

    public void resolveFile(FileTypeResolveEnum fileTypeResolveEnum, Object objectParam) {
        IFileStrategy iFileStrategy = iFileStrategyMap.get(fileTypeResolveEnum);
        if (iFileStrategy != null) {
            iFileStrategy.resolve(objectParam);
        }
    }

    // 把不同策略放到map
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IFileStrategy> tempMap = applicationContext.getBeansOfType(IFileStrategy.class);
        tempMap.values().forEach(strateyService -> iFileStrategyMap.put(strateyService.gainFileType(), strateyService));
    }
}
