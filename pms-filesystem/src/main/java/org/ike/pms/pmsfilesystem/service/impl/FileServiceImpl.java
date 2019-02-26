package org.ike.pms.pmsfilesystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ike.pms.pmsfilesystem.common.FileUtil;
import org.ike.pms.pmsfilesystem.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: ike
 * @Date: 2019-02-26 09:47
 */

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    private static final HashMap<String, String> BASIC_FILE_INFO = new HashMap<>();

    @Override
    public Map getBasicInfo() {
        return BASIC_FILE_INFO;
    }

    @Override
    public boolean initBase(String dirName) {
        if (dirName != null && !dirName.isEmpty()) {
            BASIC_FILE_INFO.put("base", dirName);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> listDir(String dirName, boolean deep) {
        if (dirName == null || dirName.isEmpty()) {
            throw new IllegalArgumentException("基础目录为空");
        }
        LinkedHashSet<String> dirSet = new LinkedHashSet<>();
        File currentFile = new File(dirName);
        if (deep) {
            FileUtil.deepListDir(currentFile, dirSet);
        } else {
            String[] dirs = currentFile.list();
            if (dirs != null && dirs.length > 0) {
                for (String dirTemp : dirs) {
                    File dir = new File(dirName + File.separator + dirTemp);
                    if (dir.isDirectory()) {
                        dirSet.add(dirName.endsWith(File.separator) ? dirName + dirTemp : dirName + File.separator + dirTemp);
                    }
                }
            }
        }
        return dirSet;
    }

    @Override
    public Set<String> listFile(String dirName, boolean deep) {
        if (dirName == null || dirName.isEmpty()) {
            throw new IllegalArgumentException("基础目录为空");
        }
        LinkedHashSet<String> fileSet = new LinkedHashSet<>();
        File currentFile = new File(dirName);
        if (deep) {
            FileUtil.deepListFile(currentFile, fileSet);
        } else {
            String[] files = currentFile.list();
            if (files != null && files.length > 0) {
                for (String fileTemp : files) {
                    File file = new File(fileTemp);
                    if (file.isFile()) {
                        fileSet.add(dirName.endsWith(File.separator) ? dirName + fileTemp : dirName + File.separator + fileTemp);
                    }
                }
            }
        }
        return fileSet;
    }
}
