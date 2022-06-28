package com.example.demo.env.importanno.anno;

import lombok.var;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * @author V00000033
 */
public class AnnoBena implements ImportSelector {
    @Override
    public Predicate<String> getExclusionFilter() {
        return ImportSelector.super.getExclusionFilter();
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] strings = new String[1];
        strings[0] = "com.example.demo.env.importanno.test.TestA";
        return strings;
    }
}
