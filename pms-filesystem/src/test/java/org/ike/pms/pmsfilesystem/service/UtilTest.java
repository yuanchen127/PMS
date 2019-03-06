package org.ike.pms.pmsfilesystem.service;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;

/**
 * @author ike
 * @since 2019-02-26 10:44
 */

@Slf4j
public class UtilTest {
    private static Set<String> fileSet = new LinkedHashSet<>();
    public static void main(String[] args) {

        String dirName = "F:"+File.separator;
        log.info("dirName: {}", dirName);
        File base = new File(dirName);
        long start = new Date().getTime();
        listDir(base, fileSet);
        long end = new Date().getTime();

        for (String name : fileSet) {
            log.info("last dir: {}", name);
        }
        log.info("use time: {}ms", end-start);
        log.info("fileSet size: {}", fileSet.size());

    }

    public static void listDir(File dir,Set<String> fileSet) {
        String[] list = dir.list();
        String lastDir = dir.getAbsolutePath();
        if (list == null || list.length <= 0) {
            fileSet.add(lastDir);
            return;
        }
        for (String name : list) {
            String currentDir = lastDir + File.separator + name;
            File currentFile = new File(currentDir);
            if (currentFile.isFile()) {
                fileSet.add(lastDir);
                continue;
            }
            listDir(currentFile, fileSet);
        }
    }
}
