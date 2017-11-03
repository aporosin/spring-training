package com.aptitude.springtraining.annotations;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Qualifier // rozsadzanie konfliktow dla typow w Springu (gdy jest wiecej niz jedne implementujacy interfejsy)
public @interface FakeComponent {

}
