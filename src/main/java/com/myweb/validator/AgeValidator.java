//package com.myweb.validator;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.time.LocalDate;
//import java.time.Period;
//
//public class AgeValidator implements ConstraintValidator<AgeConstrain, LocalDate> {
//
//    private int minAge;
//    @Override
//    public void initialize(AgeConstrain ageConstrain) {
//        this.minAge = ageConstrain.minAge();
//    }
//
//    @Override
//    public boolean isValid(LocalDate dob, ConstraintValidatorContext constraintValidatorContext) {
//        if (dob == null) {
//            return true;
//        }
//
//        LocalDate now = LocalDate.now();
//        Period period = Period.between(dob, now);
//        return period.getYears() >= 18;
//    }
//}
