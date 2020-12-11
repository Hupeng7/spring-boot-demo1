package com.example.springbootdemomytool.utils.googledemo;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @ClassName BookDeserializer
 * @Description
 * @Author H
 * @Date 2020/12/3 14:49
 * @Version 1.0
 */
public class BookDeserializer implements JsonDeserializer<Book> {
    @Override
    public Book deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final JsonElement jsonTitle = jsonObject.get("title");
        final String title = jsonTitle.getAsString();

        final String isbn10 = jsonObject.get("isbn-10").getAsString();
        final String isbn13 = jsonObject.get("isbn-13").getAsString();

        final JsonArray jsonAuthorsArray = jsonObject.get("authors").getAsJsonArray();
        final String[] authors = new String[jsonAuthorsArray.size()];
        for (int i = 0; i < authors.length; i++) {
            final JsonElement jsonAuthor = jsonAuthorsArray.get(i);
            authors[i] = jsonAuthor.getAsString();
        }

        final Book book = new Book();
        book.setTitle(title);
        book.setIsbn10(isbn10);
        book.setIsbn13(isbn13);
        book.setAuthors(authors);
        return book;
    }
}
