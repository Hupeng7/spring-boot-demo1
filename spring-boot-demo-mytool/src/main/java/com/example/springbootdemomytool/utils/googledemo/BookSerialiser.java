package com.example.springbootdemomytool.utils.googledemo;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @ClassName BookSerialiser
 * @Description
 * @Author H
 * @Date 2020/12/3 14:04
 * @Version 1.0
 */
public class BookSerialiser implements JsonSerializer<Book> {

//    @Override
//    public JsonElement serialize(final Book book, final Type typeOfSrc, final JsonSerializationContext context) {
//
//        return null;
//    }

    @Override
    public JsonElement serialize(final Book book, final Type type, final JsonSerializationContext jsonSerializationContext) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", book.getTitle());
        jsonObject.addProperty("isbn-10", book.getIsbn10());
        jsonObject.addProperty("isbn-13", book.getIsbn13());

        final JsonArray jsonAuthorsArray = new JsonArray();
        for (final String author : book.getAuthors()) {
            final JsonPrimitive jsonAuthor = new JsonPrimitive(author);
            jsonAuthorsArray.add(jsonAuthor);
        }
        jsonObject.add("authors", jsonAuthorsArray);
        return jsonObject;
    }
}
