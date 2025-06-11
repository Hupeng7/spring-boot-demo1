###Springboot 配置文件、隐私数据脱敏的最佳实践

#### 启动命令 并注入 秘钥配置
```$xslt
jasypt.encryptor.password=mypassword

java -jar -Djasypt.encryptor.password=mypassword XXX-jasypt.jar
```


#### 脚本文件
```$xslt
java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} xxx.jar
```       

#### 生成待加密的文本内容
maven包路径 D:\maven_lib\org\jasypt\jasypt\1.9.3\jasypt-1.9.3.jar

input 待加密文本

password 秘钥文本

```$xslt
java -cp  D:\maven_lib\org\jasypt\jasypt\1.9.3\jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="mypassword" password=35579B7F9C8CB15E
```

#### 参考文件 
```$xslt
https://github.com/chengxy-nds/Springboot-Notebook/tree/master/springboot-jasypt
https://mp.weixin.qq.com/s/Xazb-C07oirPJE8P_cwfaQ
https://blog.yamell.com/java/415.html
https://blog.csdn.net/a15670247200/article/details/147887615?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0-147887615-blog-130791406.235^v43^control&spm=1001.2101.3001.4242.1&utm_relevant_index=2
```


### 密码算法
- 对称密码算法
- 非对称密码算法
- 摘要算法 
    - 基础哈希算法实现 (MD5, SHA-256),更安全的是 bcrypt 实现
    





