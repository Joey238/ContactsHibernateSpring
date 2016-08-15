package org.joey.contacts.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(value = { null })
@Retention(value = null)
public @interface Employee {
    String message() default "manager and employee must work for the same company, management chain must be acyclic";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}