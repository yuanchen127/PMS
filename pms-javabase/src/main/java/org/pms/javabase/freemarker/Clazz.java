package org.pms.javabase.freemarker;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    public static String packageName;
    public static String className;
    List<org.ike.pms.api.freemarker.Base> baseList = new ArrayList<>(1);

    public void addField(String type, String name) {
        baseList.add(new org.ike.pms.api.freemarker.Base(type, name));
    }
}
