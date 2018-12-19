package org.ike.pms.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketController {

    @RequestMapping("socket")
    public String viewSocket() {
        return "index";
    }
}
