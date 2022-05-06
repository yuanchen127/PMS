package org.ike.pms.pmsfilesystem.controller;

import org.ike.pms.pmsfilesystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ike
 * @since 2019-02-26 14:27
 */
@RestController
@RequestMapping(value="/system")
public class SystemController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value="/init",method = RequestMethod.POST)
    public boolean init(String baseDir) {
        return fileService.initBase(baseDir);
    }

    @RequestMapping(value = "/{property}",method = RequestMethod.GET)
    public String getProperty(@PathVariable String property) {
        return fileService.getBasicInfo().get(property);
    }

}
