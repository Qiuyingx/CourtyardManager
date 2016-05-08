package cn.dayuanzi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于配置文件加载顺序的注解
 * 
 * @author : leihc
 * @date : 2015年4月13日 下午3:05:55
 * @version : 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InitConfigOrder {

	int order() default 0;
}
