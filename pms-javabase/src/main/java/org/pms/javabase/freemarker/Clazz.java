package org.ike.pms.api.freemarker;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    public static String packageName;
    public static String className;
    List<Base> baseList = new ArrayList<>(1);

    public void addField(String type, String name) {
        baseList.add(new Base(type, name));
    }
}
