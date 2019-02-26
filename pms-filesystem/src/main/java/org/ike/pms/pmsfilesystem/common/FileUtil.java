package org.ike.pms.pmsfilesystem.common;

import java.io.File;
import java.util.Set;

/**
 * @Author: ike
 * @Date: 2019-02-26 13:03
 */
public class FileUtil {

    public static void deepListDir(File dir, Set<String> dirSet) {
        String[] list = dir.list();
        String lastDir = dir.getAbsolutePath();
        if (list == null || list.length <= 0) {
            dirSet.add(lastDir);
            return;
        }
        for (String name : list) {
            String currentDir = lastDir + File.separator + name;
            File currentFile = new File(currentDir);
            if (currentFile.isFile()) {
                dirSet.add(lastDir);
                continue;
            }
            deepListDir(currentFile, dirSet);
        }
    }

    public static void deepListFile(File dir, Set<String> fileSet) {
        String[] list = dir.list();
        String lastDir = dir.getAbsolutePath();
        if (list != null && list.length > 0) {
            for (String name : list) {
                String currentDir = lastDir + File.separator + name;
                File currentFile = new File(currentDir);
                if (currentFile.isFile()) {
                    fileSet.add(currentDir);
                    continue;
                }
                deepListFile(currentFile, fileSet);
            }
        }

    }
}
