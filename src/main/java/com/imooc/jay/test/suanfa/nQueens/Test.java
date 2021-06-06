package com.imooc.jay.test.suanfa.nQueens;

/**
 * n皇后，n个皇后放在n*n的棋盘上，任意两个皇后不能共行，不能共列，不能在同一条斜线上（45度）
 */
public class Test {
    public static void main(String[] args) {
        int n = 14;
        long start = System.currentTimeMillis();
        System.out.println(nQueens(n));
        System.out.println(System.currentTimeMillis() - start);


        long start2 = System.currentTimeMillis();
        System.out.println(nQueens2(n));
        System.out.println(System.currentTimeMillis() - start2);
    }

    public static int nQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        // record[i] i行的皇后，放在了第几列
        int[] record = new int[n];
        return process(0, record, n);
    }

    /**
     *
     * @param i：目前来到了第i行
     * @param record：第i行之前，已经放好的皇后的位置
     * @param n：总共n行
     * @return
     */
    private static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i ,j)) {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }

        return res;
    }

    /**
     * 验证i行j列是否可以放皇后
     * @param record
     * @param i
     * @param j
     * @return
     */
    private static boolean isValid(int[] record, int i, int j) {
        // 遍历record
        for (int k = 0; k < i; k++) {
            // 不共列且不在一条斜线上
            if (j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 位计算优化，n不超过32
     * @param n
     * @return
     */
    public static int nQueens2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }

        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * 递归调用
     * @param limit：补码后n位均为1
     * @param column：列的限制，1的位置不能放皇后，0可以
     * @param leftDia：左斜线的限制
     * @param rightDia：右斜线的限制
     * @return
     */
    private static int process2(int limit, int column, int leftDia, int rightDia) {
        if (column == limit) {
            return 1;
        }

        int res = 0;
        // 可以放皇后的位置
        int pos = limit & (~(column | leftDia | rightDia));
        while (pos != 0) {
            // 取pos最右边的1
            int mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit,
                    column | mostRightOne,
                    (leftDia | mostRightOne) << 1,
                    (rightDia | mostRightOne) >>> 1);
        }
        return res;
    }


}
