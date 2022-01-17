package com.team303.diesel.autonomous.actions.serializable;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ActionConstructorSerializable implements Jsonable {
    private Set<ActionParameterSerializable> parameters = new HashSet<>();

    public ActionConstructorSerializable() {

    }

    public Set<ActionParameterSerializable> getParameters() {
        return parameters;
    }

    @Override
    public JsonElement serialize() {
        JsonObject obj = new JsonObject();

        JsonArray params = new JsonArray();

        for (var param : parameters) params.add(param.serialize());

        obj.add("parameters", params);

        return obj;
    }
}
