package com.sun.base.annotations;

import java.lang.annotation.Annotation;

/**
 * Created by louis on 2015/1/19.
 */
public class GetAnnotationInfo {
    public static void main(String[] args) {
        Class clazz=MyClass.class;
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation=(MyAnnotation)annotation;
                System.out.println(myAnnotation.age());
                System.out.println(myAnnotation.name());
            }
        }
    }
}
