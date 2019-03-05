package org.ike.pms.pmsfilesystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ike.pms.pmsfilesystem.common.FileUtil;
import org.ike.pms.pmsfilesystem.entity.CopyDir;
import org.ike.pms.pmsfilesystem.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

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
        Set<String> dirSet = new LinkedHashSet<>();
        File currentDir = new File(dirName);
        if (deep) {
            FileUtil.deepListDir(currentDir, dirSet);
        } else {
            dirSet = FileUtil.listDir(dirName);
        }
        return dirSet;
    }

    @Override
    public Set<String> listFile(String dirName, boolean deep) {
        if (dirName == null || dirName.isEmpty()) {
            throw new IllegalArgumentException("基础目录为空");
        }
        Set<String> fileSet = new LinkedHashSet<>();
        File currentFile = new File(dirName);
        if (deep) {
            FileUtil.deepListFile(currentFile, fileSet);
        } else {
            fileSet = FileUtil.listFile(dirName);
        }
        return fileSet;
    }

    @Override
    public boolean copyFileInDirs(List<CopyDir> copyDirs) {
        List<File> list = new ArrayList<>();
        if (copyDirs != null && copyDirs.size() > 0) {
            Random random = new Random();
            for (CopyDir copyDir : copyDirs) {
                String dirPath = copyDir.getDir();
                int num = copyDir.getNum();
                File dir = new File(dirPath);
                if (dir.isDirectory()) {
                    List<String> fileList = new ArrayList<>(FileUtil.listFile(dirPath));
                    if (fileList.size() > 0 && num<=fileList.size()) {
                        while (num > 0) {
                            int index = random.nextInt(fileList.size());
                            String selectFile = fileList.get(index);
                            list.add(new File(selectFile));
                            fileList.remove(index);
                            num--;
                        }
                    }
                }

            }
        }
        return FileUtil.copyFileToClipboard(list);
//        return FileUtil.copyFileToClipboard("test");
    }
}
