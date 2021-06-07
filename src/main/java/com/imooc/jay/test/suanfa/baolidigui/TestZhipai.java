package com.imooc.jay.test.suanfa.baolidigui;

/**
 * 给定一个整型数组，A和B依次只能拿最左或最右的数，最终谁会赢
 * 例：给定[1,100,4,2]，A先拿2，B先拿4，A再拿100，B最后拿到1，则A赢
 */
public class TestZhipai {
    public static void main(String[] args) {
        int[] arr = {1,100,4};
        System.out.println(win1(arr));
    }

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    /**
     * 先手拿
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static int first(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        // 先手拿最有力的情况
        return Math.max(arr[i] + second(arr, i + 1, j), arr[j] + second(arr, i, j - 1));
    }

    /**
     * 后手拿
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static int second(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        // 后手只能拿最不利的情况
        return Math.min(first(arr, i + 1, j), first(arr, i, j - 1));
    }
}
