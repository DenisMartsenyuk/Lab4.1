package parser.deserialization;

import com.google.gson.*;
import collection.Coordinates;
import collection.Organization;
import collection.Product;
import collection.UnitOfMeasure;

import java.lang.reflect.Type;

/**
 * Класс десеарилизующий Product
 */
public class ProductDeserializer implements JsonDeserializer<Product> {

    /**
     * Метод десеарилизующий Product
     */
    @Override
    public Product deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Product product = new Product(jsonObject.get("name").getAsString(),
                (Coordinates) jsonDeserializationContext.deserialize(jsonObject.get("coordinates"), Coordinates.class),
                jsonObject.get("price").getAsDouble(),
                jsonObject.get("partNumber").getAsString(),
                jsonObject.get("manufactureCost").getAsLong(),
                UnitOfMeasure.valueOf(jsonObject.get("unitOfMeasure").getAsString()),
                (Organization) jsonDeserializationContext.deserialize(jsonObject.get("manufacturer"), Organization.class));

        if((jsonObject.get("manufactureCost").getAsLong() != jsonObject.get("manufactureCost").getAsDouble()) ||
                (product.getCoordinates() == null || product.getManufacturer() == null)||
                (product.getPrice() <= 0))
            throw new JsonParseException("Некорректные данные в json файле!");

        return product;
    }
}
