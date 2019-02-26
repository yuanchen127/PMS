package org.ike.pms.pmsfilesystem.service;

import java.util.Map;
import java.util.Set;

/**
 * @Author: ike
 * @Date: 2019-02-26 09:47
 */

public interface FileService {

    Map<String,String> getBasicInfo();

    boolean initBase(String dirName);

    Set<String> listDir(String dirName, boolean deep);

    Set<String> listFile(String dirName, boolean deep);
}
