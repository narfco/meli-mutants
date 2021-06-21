package co.com.narfco.meli.mutants.meli.mutants.adapter.in.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = HumanDnaValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HumanDnaConstraint {
    String message() default "Invalid dna";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}