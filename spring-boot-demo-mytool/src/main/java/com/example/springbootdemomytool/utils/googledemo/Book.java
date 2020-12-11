package com.example.springbootdemomytool.utils.googledemo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @ClassName Book
 * @Description
 * @Author H
 * @Date 2020/12/3 14:01
 * @Version 1.0
 */
@Data
public class Book {
    private String[] authors;

    @SerializedName("isbn-10")
    private String isbn10;
    @SerializedName("isbn-13")
    private String isbn13;

    private String title;


}
