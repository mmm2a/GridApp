package com.morgan.grid.server.common.hostpage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Binding annotatino for identifying the host page map binder.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HostPageMap {
}
