package com.example.demo.env.importanno.anno;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import(AnnoBena.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Anno {
}
