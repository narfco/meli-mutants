package co.com.narfco.meli.mutants.meli.mutants.adapter.in.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class HumanDnaValidator implements ConstraintValidator<HumanDnaConstraint, String[]> {

    private static final String REGEX_DNA = "^[ATGC]+$";

    @Override
    public void initialize(HumanDnaConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        if (!String.join("",value).matches(REGEX_DNA))
            return false;

        return Arrays.stream(Arrays.stream(value)
                .map(String::length)
                .toArray())
                .distinct()
                .count() == 1;
    }
}
