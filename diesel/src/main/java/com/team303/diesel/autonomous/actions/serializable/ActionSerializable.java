package com.team303.diesel.autonomous.actions.serializable;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ActionSerializable implements Jsonable {
    private String name;
    private String classPath;
    private Map<ExecutableElement, ActionConstructorSerializable> constructors = new HashMap<>();

    public ActionSerializable(String classPath, String name) {
        this.classPath = classPath;
        this.name = name;
    }

    public Map<ExecutableElement, ActionConstructorSerializable> getConstructors() {
        return constructors;
    }

    public String getName() {
        return name;
    }

    public String getClassPath() {
        return classPath;
    }

    @Override
    public JsonElement serialize() {
        JsonObject action = new JsonObject();
        action.addProperty("name", this.name);
        action.addProperty("classPath", this.classPath);

        JsonArray cons = new JsonArray();

        for (var con : constructors.values()) 
            cons.add(con.serialize());

        action.add("constructors", cons);

        return action;
    }
}
