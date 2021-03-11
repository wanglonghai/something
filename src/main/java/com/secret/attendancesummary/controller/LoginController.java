package com.secret.attendancesummary.controller;
import com.secret.attendancesummary.common.EncryptUtil;
import com.secret.attendancesummary.entity.LoginUser;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @GetMapping("/page")
    public String toLoginPage(){
        return "login";
    }
    @GetMapping("/out")
    public String out(HttpSession session){
        session.invalidate();
        return "login";
    }
    @GetMapping("/qdAPI.html")
    public String qdAPI( ){
        return "/sys/qdAPI";
    }
    @GetMapping("/iconfont.html")
    public String iconfont( ){
        return "/sys/iconfont";
    }
    @PostMapping("/doLogin")
    public String doApply(@RequestParam("userName") String userName,
                          @RequestParam("passWord") String passWord,
                          Model model,
                          HttpSession session){
        //验证用户名和密码，输入正确，跳转到dashboard
        if("wlh".equals(userName)&&StringUtils.isNotBlank(passWord)&&"a3672d1a3ee5ffc11a1ead9d4953acc1".equals(EncryptUtil.md5Encode(passWord))){
            LoginUser loginUser=new LoginUser();
            session.setAttribute("LoginUser",loginUser);
            return "redirect:/login/success";
        }
        else  //输入错误，清空session，提示用户名密码错误
        {
            session.invalidate();
            model.addAttribute("msg","用户名密码错误");
            return "login";
        }
    }
    @GetMapping("/success")
    public String success(){
        return "sys/frame";
    }
}
