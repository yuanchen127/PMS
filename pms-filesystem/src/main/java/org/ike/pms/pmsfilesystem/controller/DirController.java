package org.ike.pms.pmsfilesystem.controller;

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
 * @Date: 2019-02-26 17:23
 */

@RestController
@RequestMapping(value = "/dir")
public class DirController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<String> listDir() {
        String baseDir = fileService.getBasicInfo().get("base");
        Set<String> dirSet = fileService.listDir(baseDir, false);
        return new ArrayList<>(dirSet);
    }

    @RequestMapping(value="/list/deep", method = RequestMethod.GET)
    public List<String> deepListDir() {
        String baseDir = fileService.getBasicInfo().get("base");
        Set<String> dirSet = fileService.listDir(baseDir, true);
        return new ArrayList<>(dirSet);
    }

}
