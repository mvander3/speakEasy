package org.mvander3.speakEasy.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Action {

    String value();

    Class<?> inputClass() default NullAction.class;

    Class<?> outputClass() default NullAction.class;

}
