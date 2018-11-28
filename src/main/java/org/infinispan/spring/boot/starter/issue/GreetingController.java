package org.infinispan.spring.boot.starter.issue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class GreetingController {

    @GetMapping("/greeting")
    @ResponseBody
    public String greeting(@RequestParam(name = "name", required = false) String name, HttpServletRequest request) {

        if (name == null) {
            String previousName = (String) request.getSession().getAttribute("name");
            if (previousName == null)
                request.getSession().setAttribute("name", name);
            name = previousName;
        }

        return "Hello " + name;
    }

}