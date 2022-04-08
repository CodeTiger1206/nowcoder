package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解可以写在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //注解只在运行时有效
public @interface LoginRequired {



}
