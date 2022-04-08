package com.kadioglumf.interview.annotations;

import com.kadioglumf.interview.enums.IncomingOperationNameEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IncomingLogger {
    IncomingOperationNameEnum operationName() default IncomingOperationNameEnum.DEFAULT;
}
