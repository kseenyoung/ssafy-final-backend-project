package com.ssafy.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RouteController {

	@RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "index";
    }
}
