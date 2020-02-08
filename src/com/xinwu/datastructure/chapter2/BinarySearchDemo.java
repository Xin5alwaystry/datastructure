package com.xinwu.datastructure.chapter2;

/**
 * 二分法查找，前提是数据是有序的
 */
public class BinarySearchDemo {
    private static int binarySearch(int[] datas, int target) {
        int low = 0;
        int high = datas.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midNum = datas[mid];
            if (target < midNum) {
                high = mid - 1;
            } else if (target > midNum) {
                low = mid + 1;
            } else {
                return 1;
            }
        }
        return -1;
    }
}
