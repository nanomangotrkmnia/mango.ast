package io.github.kawase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface VMProtect {
    ProtectionLevel value();

    enum ProtectionLevel {
        NONE,
        STANDARD,
        ULTRA,
        MAXIMUM
    }
}
