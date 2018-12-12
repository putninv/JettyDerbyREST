package com.putnin;

import com.putnin.controller.MoneyController;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Application resource config.
 *
 * @author putnin.v@gmail.com
 */
public class AppResourceConfig extends Application {
    Set<Class<?>> classes = new HashSet<>();

    public AppResourceConfig() {
        classes.add(MoneyController.class);
    }

    public Set<Class<?>> getClasses() {
        return classes;
    }
}
