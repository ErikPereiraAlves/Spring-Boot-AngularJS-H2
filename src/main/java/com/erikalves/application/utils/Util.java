package com.erikalves.application.utils;

import com.erikalves.application.exceptions.ApplicationException;
import com.erikalves.application.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

public class Util {


    private static final Type PRODUCT_COLLECTION_TYPE_TOKEN = new TypeToken<Collection<Product>>() {
    }.getType();

    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    private static final Gson GSON = new Gson();


    public static boolean isJSONValid(String jsonInString) {

        try {
            GSON.fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    public static String readJsonFromRequest(HttpServletRequest request) throws ApplicationException {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            LOGGER.error("Unable to read Json from Request!", e);
            throw new ApplicationException("Error while parsing json from request, please check format. Error Message:" + e.getMessage());

        }
    }


    public static Collection<Product> deserializeProductCollection(String jsonAsString) throws ApplicationException {

        return deserialize(PRODUCT_COLLECTION_TYPE_TOKEN, jsonAsString);
    }


    public static Collection deserialize(Type type, String jsonAsString) throws ApplicationException {

        if (StringUtils.isNotBlank(jsonAsString)) {
            String trimmedJsonLogs = jsonAsString.trim();
            try {
               if (type == PRODUCT_COLLECTION_TYPE_TOKEN) {
                    return GSON.fromJson(trimmedJsonLogs, PRODUCT_COLLECTION_TYPE_TOKEN);
                }
            } catch (Exception e) {
                LOGGER.error("Error while parsing [{}]", trimmedJsonLogs, e);
                throw new ApplicationException("Error while parsing json: " + trimmedJsonLogs + " Error Message:" + e.getMessage());
            }
        }
        return Collections.emptyList();
    }


    public static java.sql.Date getCurrentDate()  {

        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        java.sql.Date date = java.sql.Date.valueOf(dateTime);
        return date;
    }

    public static Timestamp getCurrentTs() {

        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        return timestamp;
    }


    public static String StringfyId(Long id) {

        return Long.toString(id);
    }

    public static Long LongfyId(String str) {

        long number = Long.parseLong(str);
        return number;
    }

    public static String toJson(Product object) {

        String json = new Gson().toJson(object);
        return json;

    }

    public static String toJson(Object object) {

        String json = new Gson().toJson(object);
        return json;

    }

    public static JSONArray toJsonArray(Object object) {

        String json = toJson(object);
        JSONArray array = new JSONArray(json);
        return array;

    }

    public static Gson getGson() {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
        return gson;
    }

    public static void main(String[] args) {

        Product product = new Product();
        product.setProductParentId(1l);
        product.setProductName("Smartphone controller integration tests");
        product.setProductDesc("controller integration tests for product at " + Util.getCurrentDate());
        product.setProductPrice(200.00);
        product.setProductCreatedTs(Util.getCurrentDate());
        product.setProductUpdatedTs(Util.getCurrentDate());
        LOGGER.debug("Products created TS {} ", product.getProductCreatedTs());

        String json = getGson().toJson(product);
        LOGGER.debug("Json representation of a the created Product {} ", json);

    }
}
