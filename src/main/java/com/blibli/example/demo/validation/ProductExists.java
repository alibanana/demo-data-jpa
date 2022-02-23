package com.blibli.example.demo.validation;

import com.blibli.example.demo.validation.validator.ProductExistsByIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(
    validatedBy = {ProductExistsByIdValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductExists {

  String message() default "Product does not exists!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
