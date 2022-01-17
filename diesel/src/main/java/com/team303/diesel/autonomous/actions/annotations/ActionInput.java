package com.team303.diesel.autonomous.actions.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * Denotes a parameter that can be set from the node editor
 * 
 * Allowed types:
 * <pre>
 *- double
 *- int
 *- boolean
 *- String
 *- Action
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface ActionInput {
    /**
     * Display name in node editor 
     */
    String value();
}
