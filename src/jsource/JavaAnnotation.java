package jsource;


import demo.chapter5.KotlinAnnotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by system on 2017/9/3.
 */
@KotlinAnnotation.ApplicationScope
public class JavaAnnotation {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("jsource.JavaAnnotation");
            Annotation annotation = clazz.getAnnotation(KotlinAnnotation.ApplicationScope.class);
            System.out.println(annotation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @KotlinAnnotation.Targets({String.class, Integer.class})
    class TargetClass {
    }

    @KotlinAnnotation.TargetArrays({String.class, Integer.class})
    class TargetArrays {
    }

    public @interface Describe {
        String value();
    }

    public @interface SinceJava {
        String name();

        int version();
    }

    public @interface Targets {
        Class[] value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface IntId {
        int value() default -1;
    }

}
