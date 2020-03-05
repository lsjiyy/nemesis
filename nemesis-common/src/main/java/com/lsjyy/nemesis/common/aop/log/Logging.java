package com.lsjyy.nemesis.common.aop.log;


import java.lang.annotation.*;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-13 11:13
 * @Description:
 * @Inherited 使用该注解的注解父类的子类可以继承父类的注解。
 * ElementType.PARAMETER  注解不仅被保存到class文件中，jvm加载class文件之后
 * 生命周期长度 SOURCE < CLASS < RUNTIME
 * ElementType.METHOD 用于在方法上
 * @Documented 注解表明这个注解应该被 javadoc工具记录.
 * 默认情况下,javadoc是不包括注解的.
 * 但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理,
 * 所以注解类型信息也会被包括在生成的文档中，是一个标记注解，没有成员。
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Logging {

    /**
     * 模块
     *
     * @return
     */
    String module() default "";

    /**
     * 操作类型
     *
     * @return
     */
    String operateExplain() default "";

    /**
     * 是否保存请求数据
     *
     * @return
     */
    boolean isSaveRequestData() default true;

}
