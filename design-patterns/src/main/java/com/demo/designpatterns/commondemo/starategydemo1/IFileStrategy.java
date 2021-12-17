package com.demo.designpatterns.commondemo.starategydemo1;

public interface IFileStrategy {
    // 属于哪种文件解析类型
    FileTypeResolveEnum gainFileType();

    // 封装的公用算法
    void resolve(Object objectParam);

}
