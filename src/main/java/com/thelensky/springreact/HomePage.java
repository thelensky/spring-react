package com.thelensky.springreact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {
  @RequestMapping("/")
  String index() {
    return "index";
  }
}
