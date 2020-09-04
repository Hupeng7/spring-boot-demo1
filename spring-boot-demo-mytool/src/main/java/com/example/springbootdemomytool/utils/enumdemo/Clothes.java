package com.example.springbootdemomytool.utils.enumdemo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName Clothes
 * @Description
 * @Author hup
 * @Date 2020/9/4 15:24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Clothes {
    private String type;
    private ColorC color;
}

enum ColorC {
    GREEN, RED, BLUE, YELLOW
}