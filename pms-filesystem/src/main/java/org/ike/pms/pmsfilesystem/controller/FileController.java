package org.ike.pms.pmsfilesystem.controller;

import org.ike.pms.pmsfilesystem.entity.CopyDir;
import org.ike.pms.pmsfilesystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: ike
 * @Date: 2019-02-26 13:54
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;


    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<String> listFile() {
        String baseDir = fileService.getBasicInfo().get("base");
        Set<String> fileSet = fileService.listFile(baseDir, false);
        return new ArrayList<>(fileSet);
    }

    @RequestMapping(value = "/list/deep", method = RequestMethod.GET)
    public List<String> deepListFile() {
        String baseDir = fileService.getBasicInfo().get("base");
        Set<String> fileSet = fileService.listFile(baseDir, true);
        return new ArrayList<>(fileSet);
    }

    @RequestMapping(value="/copy/dir", method = RequestMethod.POST)
    public boolean copyFileInDirs(List<CopyDir> paths) {

        return false;
    }

}
