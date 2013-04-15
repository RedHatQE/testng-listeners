package com.redhat.qe.auto.testng;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is processed by {@link SkipTestNGListener} class.
 * 
 * Use this annotation if you want to skip a test based on java system properties. You can use
 * <b>equals</b>, <b>notEquals</b> or <b>contains</b> operators that are evaluated in context of given <b>property</b  >.
 * If any is evaluated to True, test is marked as skipped.
 * @author lzoubek@redhat.com
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE})
public @interface SkipIf {
    /**
     * name of system property
     * @return
     */
    String property() default "";
    /**
     * property {@link SkipIf#property()} must equal this value for a test to be skipped 
     * @return
     */
    String equals() default "";
    /**
     * property {@link SkipIf#property()} must not equal this value for a test to be skipped 
     * @return
     */
    String notEquals() default "";
    /**
     * property {@link SkipIf#property()} must contain this value for a test to be skipped
     * @return
     */
    String contains() default "";
}
