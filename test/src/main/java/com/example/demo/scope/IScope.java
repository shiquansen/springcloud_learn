package com.example.demo.scope;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * @author V00000033
 */
@Scope("myScope")
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IScope {
}
