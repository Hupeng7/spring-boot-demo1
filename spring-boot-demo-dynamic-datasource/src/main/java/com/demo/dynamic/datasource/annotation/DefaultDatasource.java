package com.demo.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * @author Leo
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultDatasource {
}
