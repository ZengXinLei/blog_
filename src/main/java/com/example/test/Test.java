package com.example.test;

import com.example.util.GetClasses;

import java.util.ArrayList;

/**
 * @Author: zxl
 * @Time: 2020/06/20 09:59:34
 * @system: ASUS
 **/
public interface Test {

    @GetClasses
    public ArrayList<Class<?>> test();
}
