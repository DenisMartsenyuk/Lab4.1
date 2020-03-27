package parser.deserialization;

import com.google.gson.*;
import collection.Organization;
import collection.OrganizationType;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.lang.reflect.Type;

/**
 * Класс десеарилизующий Organization
 */
public class OrganizationDeserializer implements JsonDeserializer<Organization> {

    /**
     * Метод десеарилизующий Organization
     */
    @Override
    public Organization deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if(jsonObject.get("annualTurnover").getAsLong() != jsonObject.get("annualTurnover").getAsDouble())
            throw new JsonParseException("Некорректные данные в json файле!");
        return new Organization(jsonObject.get("name").getAsString(),
                jsonObject.get("annualTurnover").getAsLong(),
                OrganizationType.valueOf(jsonObject.get("organizationType").getAsString()));
    }
}
