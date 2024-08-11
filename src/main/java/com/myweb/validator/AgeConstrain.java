//package com.myweb.validator;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Target({ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = AgeValidator.class)
//public @interface AgeConstrain {
//    String message() default "Age must be 18 or older";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//    int minAge() default 18;
//}
