# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/maven-plugin/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/htmlsingle/#using-boot-devtools)

### IDEA 构建Spring-Boot/Cloud项目

使用Intellij中的Spring Initializr来快速构建Spring Boot/Cloud工程  
```http://blog.didispace.com/spring-initializr-in-intellij/```  
在之前的所有Spring Boot和Spring Cloud相关博文中，都会涉及Spring Boot工程的创建。而创建的方式多种多样，我们可以通过Maven来手工构建或是通过脚手架等方式快速搭建，也可以通过《Spring Boot快速入门》一文中提到的SPRING INITIALIZR页面工具来创建，相信每位读者都有自己最喜欢和最为熟练的创建方式。

本文我们将介绍嵌入的Intellij中的Spring Initializr工具，它同Web提供的创建功能一样，可以帮助我们快速的构建出一个基础的Spring Boot/Cloud工程。

菜单栏中选择File=>New=>Project..，我们可以看到如下图所示的创建功能窗口。其中Initial Service Url指向的地址就是Spring官方提供的Spring Initializr工具地址，所以这里创建的工程实际上也是基于它的Web工具来实现的。



点击Next，等待片刻后，我们可以看到如下图所示的工程信息窗口，在这里我们可以编辑我们想要创建的工程信息。其中，Type可以改变我们要构建的工程类型，比如：Maven、Gradle；Language可以选择：Java、Groovy、Kotlin。



点击Next，进入选择Spring Boot版本和依赖管理的窗口。在这里值的我们关注的是，它不仅包含了Spring Boot Starter POMs中的各个依赖，还包含了Spring Cloud的各种依赖。



点击Next，进入最后关于工程物理存储的一些细节。最后，点击Finish就能完成工程的构建了。



Intellij中的Spring Initializr虽然还是基于官方Web实现，但是通过工具来进行调用并直接将结果构建到我们的本地文件系统中，让整个构建流程变得更加顺畅，还没有体验过此功能的Spring Boot/Cloud爱好者们不妨可以尝试一下这种不同的构建方式。