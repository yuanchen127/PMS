package org.ike.pms.pmsfilesystem.service;

/**
 * @author ike
 * @since 2019-03-06 17:20
 */
public class CommonTest {
    public static void main(String[] args) {
        int[] intArr = new int[]{1,2,3,4,5};
        for (int i : intArr) {
            if (i == 3) {
                break;
            }
            System.out.println(i);
        }
        System.out.println("end");
    }
}
