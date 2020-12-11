package com.example.springbootdemomytool.utils.googledemo;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import lombok.Data;

/**
 * @ClassName SoccerPlayer
 * @Description
 * @Author H
 * @Date 2020/12/3 13:47
 * @Version 1.0
 */
@Data
public class SoccerPlayer {
    private String name;

    @Since(1.2)
    private int shirtNumber;

    @Until(0.9)
    private String country;

    private String teamName;
}
