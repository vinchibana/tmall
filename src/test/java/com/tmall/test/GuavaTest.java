package com.tmall.test;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.junit.Test;

public class GuavaTest {

    @Test
    public void test1() {
        String input = "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("Input " + (isNullOrEmpty ?"is":"is not") + " null or empty.");
    }

    @Test
    public void test2() {
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a,b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c,d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }

    @Test
    public void test3() {
        int minLength = 4;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);
        String padStartResult = Strings.padStart("1", 2, '0');
        System.out.println("padStartResult is " + padStartResult);
    }

    @Test
    public void test4() {
        Gson gson = new Gson();
        String[] strings = {"abc", "def", "ghi"};
        System.out.println(gson.toJson(strings));
    }
}
