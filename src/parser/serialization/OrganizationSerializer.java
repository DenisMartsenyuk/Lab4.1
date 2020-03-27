package parser.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import collection.Organization;

import java.lang.reflect.Type;

/**
 * Класс десеарилизующий Organization
 */
public class OrganizationSerializer implements JsonSerializer<Organization> {

    /**
     * Метод сеарилизующий Organization
     */
    @Override
    public JsonElement serialize(Organization organization, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", organization.getName());
        jsonObject.addProperty("annualTurnover", organization.getAnnualTurnover());
        jsonObject.addProperty("organizationType", organization.getOrganizationType().toString());

        return jsonObject;
    }
}
