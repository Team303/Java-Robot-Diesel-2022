package com.team303.diesel.autonomous.actions.serializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ActionParameterSerializable implements Jsonable {
    private String name;

    public ActionParameterSerializable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public JsonElement serialize() {
        JsonObject parameter = new JsonObject();
        parameter.addProperty("type", "double");
        parameter.addProperty("name", this.name);

        return parameter;
    }
}
