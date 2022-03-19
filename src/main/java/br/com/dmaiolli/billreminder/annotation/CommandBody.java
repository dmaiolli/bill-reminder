package br.com.dmaiolli.billreminder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandBody {

    String value();

    String usage() default "";

    int minArguments() default 0;

    int maxArguments() default 0;

}
