package com.ez2db.ez2db.controller;

import com.ez2db.ez2db.entity.Member;
import com.ez2db.ez2db.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController
{
  private final LoginService loginService;

  @RequestMapping(method = RequestMethod.GET, value = "/login")
  public String loginGet()
  {
    return "login";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public String loginPost(@RequestBody Member member)
  {
    return "";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/signUp")
  public String signUpGet(Model model)
  {
    model.addAttribute("member", new Member());

    return "signUp";
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/signUp")
  public String signUpPut(@ModelAttribute Member member)
  {
    log.info("member.getUserId() = " + member.getUserId());

    loginService.sinUp(member);

    return "redirect:/login";
  }


  @RequestMapping(method = RequestMethod.GET, value = "/idDuplicatedCheck")
  public String idDuplicatedCheckGet(@RequestBody Member member)
  {

  }
}
