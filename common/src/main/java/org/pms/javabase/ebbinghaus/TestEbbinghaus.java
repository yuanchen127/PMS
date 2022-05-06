package org.pms.javabase.ebbinghaus;

import java.util.Calendar;

public class TestEbbinghaus {
    public static void main(String[] args) {
        Calendar first = Calendar.getInstance();
        first.add(Integer.valueOf(Calendar.YEAR), -1);

        boolean before = Calendar.getInstance().after(first);
        System.out.println(before);
    }

}
