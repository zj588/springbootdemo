package com.imooc.jay.test.suanfa.baolidigui;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定字符串的全排列
 */
public class TestCharsQuanPai {
    public static void main(String[] args) {
        fun1("aab");
        System.out.println("------------------");
        fun2("aab");
    }

    public static void fun2(String str) {
        if (str == null) {
            return;
        }

        List<String> res = new ArrayList<>();
        char[] chars = str.toCharArray();
        process2(chars, 0, res);

        res.forEach(item -> {
            System.out.println(item);
        });
    }

    private static void process2(char[] chars, int i, List<String> res) {
        if (i == chars.length) {
            res.add(String.valueOf(chars));
        }

        // 用于记录chars[i]位置的字符是否被使用
        boolean[] visit = new boolean[26];
        for (int j = i; j < chars.length; j++) {
            // 判断chars[j]的字符与chars[i]的字符是否一样，不相等，则可以对i和j进行交换
            if (!visit[chars[j] - 'a']) {
                visit[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process2(chars, i + 1, res);
                swap(chars, i, j);
            }
        }
    }

    public static void fun1(String str) {
        if (str == null) {
            return;
        }

        List<String> res = new ArrayList<>();
        char[] chars = str.toCharArray();
        process1(chars, 0, res);

        res.forEach(item -> {
            System.out.println(item);
        });
    }

    private static void process1(char[] chars, int i, List<String> res) {
        if (i == chars.length) {
            String s = String.valueOf(chars);
            if (!res.contains(s)) {
                res.add(s);
            }
        }

        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            process1(chars, i + 1, res);
            swap(chars, i, j);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
