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

测试：
一般接口：
Key：Content-Type
Value：application/json

文件上传接口：
二、填写Headers
Key：Content-Type
Value：multipart/form-data
[{"key":"Content-Type","value":"multipart/form-data","description":"","enabled":true}]