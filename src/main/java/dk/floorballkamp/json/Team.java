package dk.floorballkamp.json;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import java.util.function.Supplier;

public class Team implements Supplier<JsonObjectBuilder> {

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JsonObjectBuilder get() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", name);
        return builder;
    }
}
