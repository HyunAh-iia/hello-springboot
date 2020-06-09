package my.study.hello.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // PARAMETER : this annotation can be used by objects as a parameter of a method.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // @interface : make this file to an annotation class
}
