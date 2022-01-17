package com.team303.diesel.autonomous.actions.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * Denotes an Action that can be displayed in the node editor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface AutoAction {
    /**
     * Display Name of the Action 
     */
    String value() default "Unknown Action";
}
