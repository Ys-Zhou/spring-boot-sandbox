package me.zhou.springbootsandbox.controller;

import me.zhou.springbootsandbox.service.RetryService;
import me.zhou.springbootsandbox.service.TestExceptionOne;
import me.zhou.springbootsandbox.service.TestExceptionTwo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

  private final RetryService retryService;

  public HomeController(RetryService retryService) {
    this.retryService = retryService;
  }

  @RequestMapping("")
  public String home() {
    return "home";
  }

  @RequestMapping("retry")
  @ResponseBody
  public String retry() throws TestExceptionOne, TestExceptionTwo {
    return retryService.testExceptionOne()
        + "<br>"
        + retryService.testExceptionOneWithParam("retry sample")
        + "<br>"
        + retryService.testExceptionTwo();
  }

}
