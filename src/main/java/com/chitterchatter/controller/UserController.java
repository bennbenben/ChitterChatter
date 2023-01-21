package com.chitterchatter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping(value = "/helloworld")
  public String helloWorld() {
    return "hello world";
  }
  
  @GetMapping(value = "/hellomyworld")
  public String helloMyWorld() {
    return "hello my world";
  }
}
