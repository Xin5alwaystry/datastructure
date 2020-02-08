package com.xinwu.datastructure.chapter2;

/**
 * p23 求最大的子序列之和
 */
public class MaxSubSum {
    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        int result;
        result = MaxSubSum.solution1(data);
    }

    /**
     * 方法1 使用了遍历的方，时间复杂度为O(n^3)
     */
    private static int solution1(int[] data) {
        int maxSum = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length; j++) {
                int innerSum = 0;
                for (int k = i; k < j; k++) {
                    innerSum += data[k];
                }

                if (innerSum > maxSum) {
                    maxSum = innerSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 方法2 时间复杂度为O(n^2)
     */
    private static int solution2(int[] data) {
        int maxSum = 0;
        for (int i = 0; i < data.length; i++) {
            int innerSum = 0;
            for (int j = i; j < data.length; j++) {
                innerSum += data[j];

                if (innerSum > maxSum) {
                    maxSum = innerSum;
                }
            }
        }
        return maxSum;
    }

}
