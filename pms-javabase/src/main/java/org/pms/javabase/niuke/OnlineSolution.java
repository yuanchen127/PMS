package org.pms.javabase.niuke;

import java.util.ArrayList;
import java.util.Random;

public class OnlineSolution {
    static ArrayList<Integer> list = new ArrayList<Integer>();
    public static void main(String args[]) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println(printArray(array));
        System.out.println(printArray(reOrderArray(array)));
        ;
    }



    public static String printArray(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                sb.append(array[i]);
            } else {
                sb.append("," + array[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
     * @param target
     * @param array
     * @return
     */
    public static boolean find(int target, int[][] array) {
        for (int i = 0; i < array.length && i < target; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        int index;
        while ((index = str.indexOf(" "))>=0) {
            str = str.replace(index, index + 1, "%20");
        }
        return str.toString();
    }

    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * @param array
     * @return
     */
    public static int minNumberInRotateArray(int[] array) {
        if (array.length > 0) {
            int left = 0;
            int right = array.length - 1;
            int low = 0;
            while (left <= right) {
                low = array[low] <= array[left] && array[low]<=array[right] ? low : (array[left] <= array[right] ? left : right);
                left++;
                right--;
            }
            return array[low];
        }
        return 0;
    }

    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     * @param n
     * @return
     */
    public static int Fibonacci(int n) {
        //递归
//        if (n <= 1) {
//            return n;
//        }
//        return Fibonacci(n - 2) + Fibonacci(n - 1);
        //循环
        int i=2,x=0, y=1, z=0;
        if (n <= 1) {
            return n;
        }
        while (i <= n) {
            z = x + y;
            x = y;
            y = z;
            i++;
        }
        return z;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * @param target
     * @return
     */
    public static int JumpFloor(int target) {
        int i = 2, x = 1, y = 2, z = 0;
        if (target <= 2) {
            return target;
        }
        while (i < target) {
            z = x + y;
            x = y;
            y = z;
            i++;
        }
        return z;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * @param target
     * @return
     */
    public static int JumpFloorII(int target) {
        return 1<<(--target);
    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * @param target
     * @return
     */
    public static int RectCover(int target) {
        int i = 3, x = 1, y = 2, z = 0;
        if (target <= 2) {
            return target;
        }
        while (i<=target) {
            z = x + y;
            x = y;
            y = z;
            i++;
        }
        return z;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * @param base
     * @param exponent
     * @return
     */
    public static double power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * @param array
     */
    public static int[] reOrderArray(int [] array) {
        int[] another = array.clone();
        int index = 0;
        for (int i = 0; i < another.length; i++) {
            if (another[i] % 2 != 0) {
                array[index] = another[i];
                index++;
            }
        }
        for (int i = 0; i < another.length; i++) {
            if (another[i] % 2 == 0) {
                array[index] = another[i];
                index++;
            }
        }
        return array;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix(int [][] matrix) {

        return null;
    }


    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

}
