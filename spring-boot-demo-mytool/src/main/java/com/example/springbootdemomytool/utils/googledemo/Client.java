package com.example.springbootdemomytool.utils.googledemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @ClassName Client
 * @Description
 * @Author H
 * @Date 2020/12/3 13:49
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        gsonVersion();

        System.out.println("=============================");
        gsonBuilder();
    }

    private static void gsonVersion() {
        final GsonBuilder builder = new GsonBuilder();
        builder.setVersion(1.0);

        final Gson gson = builder.create();
        final SoccerPlayer account = new SoccerPlayer();
        account.setName("Albert Attard");
        account.setShirtNumber(10);   // Since version 1.2
        account.setTeamName("Zejtun Corinthians");
        account.setCountry("Malta"); // Unit version 0.9

        final String json = gson.toJson(account);
        System.out.printf("Serialised (version 1.0)%n %s%n", json);
    }

    /**
     *
     */
    private static void gsonBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Book.class, new BookSerialiser());
        gsonBuilder.setPrettyPrinting();
        final Gson gson = gsonBuilder.create();

        final Book javaPuzzlers = new Book();
        javaPuzzlers.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
        javaPuzzlers.setIsbn10("124654134");
        javaPuzzlers.setIsbn13("79879856565231");
        javaPuzzlers.setAuthors(new String[]{"Joshua Bloch", "Neal Gafter"});

        // Format to JSON
        final String json = gson.toJson(javaPuzzlers);
        System.out.println(json);
    }

}
