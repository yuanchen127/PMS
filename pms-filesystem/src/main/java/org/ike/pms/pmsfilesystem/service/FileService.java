package org.ike.pms.pmsfilesystem.service;

import org.ike.pms.pmsfilesystem.entity.CopyDir;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ike
 * @since 2019-02-26 09:47
 */

public interface FileService {

    Map<String,String> getBasicInfo();

    boolean initBase(String dirName);

    Set<String> listDir(String dirName, boolean deep);

    Set<String> listFile(String dirName, boolean deep);

    boolean copyFileInDirs(List<CopyDir> copyDirs);
}
