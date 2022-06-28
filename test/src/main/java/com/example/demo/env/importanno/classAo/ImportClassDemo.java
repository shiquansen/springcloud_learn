package com.example.demo.env.importanno.classAo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(Imported.class)
public class ImportClassDemo {
}
