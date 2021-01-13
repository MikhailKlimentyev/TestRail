package utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation contains ids of test cases in TestRail system as testCaseID field.
 * Allows linking test case in TestRail with autotest
 * by assigning this annotation with initialized field by test case id to test method
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestRail {

    int[] testCaseID() default 0;
}
