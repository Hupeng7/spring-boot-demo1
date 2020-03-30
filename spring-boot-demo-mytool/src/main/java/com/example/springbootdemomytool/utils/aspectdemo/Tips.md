今天主要说说如何通过自定义注解的方式，在 Spring Boot 中来实现 AOP 切面统一打印出入参日志。小伙伴们可以收藏一波。

废话不多说，进入正题 ！

目录
一、先看看切面日志输出效果

二、添加 AOP Maven 依赖

三、自定义日志注解

四、配置 AOP 切面

五、怎么使用呢？

六、对于文件上传好使不？

七、只想在开发环境和测试环境中使用？

八、多切面如何指定优先级？

一、先看看切面日志输出效果
在看看实现方法之前，我们先看下切面日志输出效果咋样：

Spring boot 自定义注解，aop切面统一打印请求日志效果图
从上图中可以看到，每个对于每个请求，开始与结束一目了然，并且打印了以下参数：

URL: 请求接口地址；
Description: 接口的中文说明信息；
HTTP Method: 请求的方法，是 POST, GET, 还是 DELETE 等；
Class Method: 被请求的方法路径 : 包名 + 方法名;
IP: 请求方的 IP 地址；
Request Args: 请求入参，以 JSON 格式输出；
Response Args: 响应出参，以 JSON 格式输出；
Time-Consuming: 请求耗时，以此估算每个接口的性能指数；
怎么样？看上去效果还不错呢？接下来看看，我们要如何一步一步实现它呢？

二、添加 AOP Maven 依赖
在项目 pom.xml 文件中添加依赖：

<!-- aop 依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

<!-- 用于日志切面中，以 json 格式打印出入参 -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.5</version>
</dependency>
三、自定义日志注解
让我们来自定义一个日志注解，如下所示：

自定义注解
①：什么时候使用该注解，我们定义为运行时；
②：注解用于什么地方，我们定义为作用于方法上；
③：注解是否将包含在 JavaDoc 中；
④：注解名为 WebLog;
⑤：定义一个属性，默认为空字符串；
源代码如下：

package site.exception.springbootaopwebrequest.aspect;

import java.lang.annotation.*;

/**
 * @author 犬小哈 （微信号：小哈学Java）
 * @site www.exception.site
 * @date 2019/2/12
 * @time 下午9:19
 * @discription
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";

}
到这里，一个完整的自定义注解就定义完成了。

四、配置 AOP 切面
在配置 AOP 切面之前，我们需要了解下 aspectj 相关注解的作用：

@Aspect：声明该类为一个注解类；
@Pointcut：定义一个切点，后面跟随一个表达式，表达式可以定义为切某个注解，也可以切某个 package 下的方法；
切点定义好后，就是围绕这个切点做文章了：

@Before: 在切点之前，织入相关代码；
@After: 在切点之后，织入相关代码;
@AfterReturning: 在切点返回内容后，织入相关代码，一般用于对返回值做些加工处理的场景；
@AfterThrowing: 用来处理当织入的代码抛出异常后的逻辑处理;
@Around: 环绕，可以在切入点前后织入代码，并且可以自由的控制何时执行切点；
注解执行顺序
接下来，定义一个 WebLogAspect.java 切面类，声明一个切点：

定义一个切点
然后，定义 @Around 环绕，用于何时执行切点：

环绕
①：记录一下调用接口的开始时间；
②：执行切点，执行切点后，会去依次调用 @Before -> 接口逻辑代码 -> @After -> @AfterReturning；
③：打印出参；
④：打印接口处理耗时；
⑤：返回接口返参结果；
再来看看 @Before 方法：

@Before
看注释功能说明，因为注释说得还是比较清楚的！

最后，用 @After 来做个收尾：

换行
@After
我们在每个接口的最后，打印日志结束标志。最后再看下项目包结构：

项目包结构
到这里，切面相关的代码就完成了！

上完整代码：

package site.exception.springbootaopwebrequest.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author 犬小哈 （微信号：小哈学Java）
 * @site www.exception.site
 * @date 2019/2/12
 * @time 下午9:19
 * @discription
 **/
@Aspect
@Component
@Profile({"dev", "test"})
public class WebLogAspect {

    private final static Logger logger         = LoggerFactory.getLogger(WebLogAspect.class);
    /** 换行符 */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** 以自定义 @WebLog 注解为切点 */
    @Pointcut("@annotation(site.exception.springbootaopwebrequest.aspect.WebLog)")
    public void webLog() {}

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");
        // 打印请求 url
        logger.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        logger.info("Description    : {}", methodDescription);
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        logger.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        logger.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Response Args  : {}", new Gson().toJson(result));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

}

五、怎么使用呢？
因为我们的切点是自定义注解 @WebLog, 所以我们仅仅需要在 Controller 控制器的每个接口方法添加 @WebLog 注解即可，如果我们不想某个接口打印出入参日志，不加注解就可以了：

用户登录接口
六、对于文件上传好使不？
是好使的！不论是单文件上传，抑或是多文件上传，切面日志均运行良好，这里测试的东西，小哈就不贴出来了。有兴趣的小伙伴可以试试！

七、只想在开发环境和测试环境中使用？
对于那些性能要求较高的应用，不想在生产环境中打印日志，只想在开发环境或者测试环境中使用，要怎么做呢？我们只需为切面添加 @Profile 就可以了，如下图所示：

指定profile
这样就指定了只能作用于 dev 开发环境和 test 测试环境，生产环境 prod 是不生效的！

八、多切面如何指定优先级？
假设说我们的服务中不止定义了一个切面，比如说我们针对 Web 层的接口，不止要打印日志，还要校验 token 等。要如何指定切面的优先级呢？也就是如何指定切面的执行顺序？

我们可以通过 @Order(i)注解来指定优先级，注意：i 值越小，优先级则越高。

假设说我们定义上面这个日志切面的优先级为 @Order(10), 然后我们还有个校验 token 的切面 CheckTokenAspect.java，我们定义为了 @Order(11), 那么它们之间的执行顺序如下：

多切点优先级
我们可以总结一下：

在切点之前，@Order 从小到大被执行，也就是说越小的优先级越高；
在切点之后，@Order 从大到小被执行，也就是说越大的优先级越高；

#### 测试：
一般接口：
Key：Content-Type
Value：application/json

文件上传接口：
二、填写Headers
Key：Content-Type
Value：multipart/form-data
[{"key":"Content-Type","value":"multipart/form-data","description":"","enabled":true}]

### 知识点：
面向切面的本质：定义切面类并将切面类的功能织入到目标类中；

实现方式：将切面应用到目标对象从而创建一个新的代理对象的过程。替换；

 

使用注解@Aspect来定义一个切面，在切面中定义切入点(@Pointcut),通知类型(@Before, @AfterReturning,@After,@AfterThrowing,@Around). 

https://www.cnblogs.com/oumyye/p/4480196.html

 

现将图6-6中涉及到的一些概念解释如下。

切面（Aspect）：
其实就是共有功能的实现。如日志切面、权限切面、事务切面等。在实际应用中通常是一个存放共有功能实现的普通Java类，之所以能被AOP容器识别成切面，是在配置中指定的。

切面：织入类

@Aspect

public class MyAspect {}

 

通知（Advice）：
是切面的具体实现。以目标方法为参照点，根据放置的地方不同，可分为前置通知（Before）、后置通知（AfterReturning）、异常通知（AfterThrowing）、最终通知（After）与环绕通知（Around）5种。在实际应用中通常是切面类中的一个方法，具体属于哪类通知，同样是在配置中指定的。

通知：织入类的事件；

@Aspect

@Component

public class LogInterceptor {

    @Pointcut("execution(public * com.oumyye.service..*.add(..))")

    public void myMethod(){};

    

    /*@Before("execution(public void com.oumyye.dao.impl.UserDAOImpl.save(com.oumyye.model.User))")*/

    @Before("myMethod()")

    public void before() {

        System.out.println("method staet");

    } 

    @After("myMethod()")

    public void after() {

        System.out.println("method after");

    } 

    @AfterReturning("execution(public * com.oumyye.dao..*.*(..))")

    public void AfterReturning() {

        System.out.println("method AfterReturning");

    } 

    @AfterThrowing("execution(public * com.oumyye.dao..*.*(..))")

    public void AfterThrowing() {

        System.out.println("method AfterThrowing");

    } 

}

 

通知传递参数

在Spring AOP中，除了execution和bean指示符不能传递参数给通知方法，其他指示符都可以将匹配的方法相应参数或对象自动传递给通知方法。获取到匹配的方法参数后通过”argNames”属性指定参数名。如下，需要注意的是args(指示符)、argNames的参数名与before()方法中参数名 必须保持一致即param。

@Before(value="args(param)", argNames="param") //明确指定了    

public void before(int param) {    

    System.out.println("param:" + param);    

}  

 

连接点（Joinpoint）：
就是程序在运行过程中能够插入切面的地点。例如，方法调用、异常抛出或字段修改等，但Spring只支持方法级的连接点。

连接点：目标类+目标函数；用于切面类在运行时获取目标对象+函数+参量上下文信息；

@Around("execution(* com.zejian.spring.springAop.dao.UserDao.addUser(..))")

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕通知前....");

        Object obj= (Object) joinPoint.proceed();

        System.out.println("环绕通知后....");

        return obj;

    }

 

切入点（Pointcut）：
用于定义通知应该切入到哪些连接点上。不同的通知通常需要切入到不同的连接点上，这种精准的匹配是由切入点的正则表达式来定义的。

切入点：在哪里（什么样函数）织入；用于在切面中注解织入到哪些范围的哪些函数上；

定义过滤切入点函数时，直接把execution以定义匹配表达式作为值传递给通知类型的如下：

@After(value="execution(* com.zejian.spring.springAop.dao.UserDao.addUser(..))")

  public void after(){

      System.out.println("最终通知....");

  } 

采用与ApectJ中使用pointcut关键字类似的方式定义切入点表达式如下，使用@Pointcut注解：

@Pointcut("execution(* com.zejian.spring.springAop.dao.UserDao.addUser(..))")

private void myPointcut(){}

@After(value="myPointcut()")

public void afterDemo(){

    System.out.println("最终通知....");

} 

切入点指示符

为了方法通知应用到相应过滤的目标方法上，SpringAOP提供了匹配表达式，这些表达式也叫切入点指示符，在前面的案例中，它们已多次出现。

通配符

在定义匹配表达式时，通配符几乎随处可见，如*、.. 、+ ，它们的含义如下：

.. ：匹配方法定义中的任意数量的参数，此外还匹配类定义中的任意数量包
//任意返回值，任意名称，任意参数的公共方法
execution(public * *(..))
//匹配com.zejian.dao包及其子包中所有类中的所有方法
within(com.zejian.dao..*) 
＋ ：匹配给定类的任意子类
//DaoUserwithin(com.zejian.dao.DaoUser+) 
＊ ：匹配任意数量的字符
匹配包及其子包中所有类的所有方法
within(com.zejian.service..*)
//匹配以set开头，参数为int类型，任意返回值的方法
execution(* set*(int)) 
 

execution 用于匹配方法执行的连接点;

within 用于匹配指定类型内的方法执行;

 

目标对象（Target）：
就是那些即将切入切面的对象，也就是那些被通知的对象。这些对象中已经只剩下干干净净的核心业务逻辑代码了，所有的共有功能代码等待AOP容器的切入。

目标对象：目标类

<!-- 定义目标对象 -->

    <bean id="userDaos" class="com.zejian.spring.springAop.dao.daoimp.UserDaoImp" />

 

代理对象（Proxy）：
将通知应用到目标对象之后被动态创建的对象。可以简单地理解为，代理对象的功能等于目标对象的核心业务逻辑功能加上共有功能。代理对象对于使用者而言是透明的，是程序运行过程中的产物。

代理对象：目标类织入切面功能后的中间层（类）

 

织入（Weaving）：
将切面应用到目标对象从而创建一个新的代理对象的过程。这个过程可以发生在编译期、类装载期及运行期，当然不同的发生点有着不同的前提条件。譬如发生在编译期的话，就要求有一个支持这种AOP实现的特殊编译器；发生在类装载期，就要求有一个支持AOP实现的特殊类装载器；只有发生在运行期，则可直接通过Java语言的反射机制与动态代理机制来动态实现。

织入：织入的实现方式；

 

https://blog.csdn.net/liujiahan629629/article/details/18864211

 

 

基于XML的开发

前面分析完基于注解支持的开发是日常应用中最常见的，即使如此我们还是有必要了解一下基于xml形式的Spring AOP开发，这里会以一个案例的形式对xml的开发形式进行简要分析，定义一个切面类

copycode.gif

/**

 * Created by zejian on 2017/2/20.*/

public class MyAspectXML {

 

    public void before(){

        System.out.println("MyAspectXML====前置通知");

    }

 

    public void afterReturn(Object returnVal){

        System.out.println("后置通知-->返回值:"+returnVal);

    }

 

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("MyAspectXML=====环绕通知前");

        Object object= joinPoint.proceed();

        System.out.println("MyAspectXML=====环绕通知后");

        return object;

    }

 

    public void afterThrowing(Throwable throwable){

        System.out.println("MyAspectXML======异常通知:"+ throwable.getMessage());

    }

 

    public void after(){

        System.out.println("MyAspectXML=====最终通知..来了");

    }

} 

copycode.gif

通过配置文件的方式声明如下（spring-aspectj-xml.xml）：

copycode.gif

<beans xmlns="http://www.springframework.org/schema/beans"

       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

       xmlns:aop="http://www.springframework.org/schema/aop"

       xmlns:context="http://www.springframework.org/schema/context"

       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd

        http://www.springframework.org/schema/aop

        http://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

 

    <!--<context:component-scan base-package=""-->

 

    <!-- 定义目标对象 -->

    <bean name="productDao" class="com.zejian.spring.springAop.dao.daoimp.ProductDaoImpl" />

 

    <!-- 定义切面 -->

    <bean name="myAspectXML" class="com.zejian.spring.springAop.AspectJ.MyAspectXML" />

    <!-- 配置AOP 切面 -->

    <aop:config>

        <!-- 定义切点函数 -->

        <aop:pointcut id="pointcut" expression="execution(* com.zejian.spring.springAop.dao.ProductDao.add(..))" />

 

        <!-- 定义其他切点函数 -->

        <aop:pointcut id="delPointcut" expression="execution(* com.zejian.spring.springAop.dao.ProductDao.delete(..))" />

 

        <!-- 定义通知 order 定义优先级,值越小优先级越大-->

        <aop:aspect ref="myAspectXML" order="0">

            <!-- 定义通知

            method 指定通知方法名,必须与MyAspectXML中的相同

            pointcut 指定切点函数

            -->

            <aop:before method="before" pointcut-ref="pointcut" />

 

            <!-- 后置通知  returning="returnVal" 定义返回值 必须与类中声明的名称一样-->

            <aop:after-returning method="afterReturn" pointcut-ref="pointcut"  returning="returnVal" />

 

            <!-- 环绕通知 -->

            <aop:around method="around" pointcut-ref="pointcut"  />

 

            <!--异常通知 throwing="throwable" 指定异常通知错误信息变量,必须与类中声明的名称一样-->

            <aop:after-throwing method="afterThrowing" pointcut-ref="pointcut" throwing="throwable"/>

 

            <!--

                 method : 通知的方法(最终通知)

                 pointcut-ref : 通知应用到的切点方法

                -->

            <aop:after method="after" pointcut-ref="pointcut"/>

        </aop:aspect>

    </aop:config>

beans 

copycode.gif

声明方式和定义方式在代码中已很清晰了，了解一下即可，在实际开发中，会更倾向与使用注解的方式开发