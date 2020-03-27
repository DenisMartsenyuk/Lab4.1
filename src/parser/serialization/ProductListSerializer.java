package parser.serialization;

import collection.ProductList;
import com.google.gson.*;
import collection.Product;

import java.lang.reflect.Type;

/**
 * Класс десеарилизующий ProductList
 */
public class ProductListSerializer implements JsonSerializer<ProductList> {

    /**
     * Метод сеарилизующий ProductList
     */
    @Override
    public JsonElement serialize(ProductList productList, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        for (Product product : productList.getProducts()) {
            jsonArray.add(jsonSerializationContext.serialize(product));
        }

        jsonObject.add("products", jsonArray);
        return jsonObject;
    }
}
