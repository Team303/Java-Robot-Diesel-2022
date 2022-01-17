package com.team303.diesel.autonomous.actions.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * Denotes a constructor that can be selected from the node editor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.CONSTRUCTOR })
public @interface ActionConstructor {
    
}
