package com.gzu.community_cloud.authentication;


import com.gzu.community_cloud.authentication.feign.ResidenceFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    ResidenceFeign residenceFeign;

    @Value("${admin_username}")
    String adminUsername;

    public AuthenticationController(ResidenceFeign residenceFeign) {
        this.residenceFeign = residenceFeign;
    }

    @GetMapping("/feign/get-username")
    @ResponseBody
    public String getUsername(HttpSession session) {
        return (String) session.getAttribute("username");
    }

    @GetMapping("/feign/is-admin")
    @ResponseBody
    String isAdmin(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null && username.equals(adminUsername)) {
            return "1";
        } else {
            return "0";
        }
    }

    @GetMapping("/")
    public String index(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "forward:/index.html";
        } else if (username.equals(adminUsername)) {
            return "redirect:/admin";
        } else {
            return "redirect:/residence";
        }
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/";
        }
        return "forward:/login.html";
    }

    @GetMapping("/register")
    public String register(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/";
        }
        return "forward:/register.html";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("username", null);
        return "redirect:/";
    }

    @PostMapping("/post-login-data")
    public String loginData(
            @RequestParam("username") String username, @RequestParam("password") String password,
            HttpSession session, RedirectAttributes redirectAttributes
    ) {
        if (username.equals(adminUsername)) {
            session.setAttribute("username", username);
            return "redirect:/admin";
        }
        int loginStatus = Integer.parseInt(residenceFeign.login(username, password));
        switch (loginStatus) {
            case 0:
                session.setAttribute("username", username);
                return "redirect:/residence";
            case 1:
                redirectAttributes.addAttribute("error", "用户不存在");
                return "redirect:/login";
            case 2:
                redirectAttributes.addAttribute("error", "密码错误");
                return "redirect:/login";
            case 3:
                redirectAttributes.addAttribute("error", "发生错误");
                return "redirect:/login";
        }
        throw new RuntimeException("发生错误");
    }

    @PostMapping("/post-register-data")
    public String registerData(
            @RequestParam("username") String username, @RequestParam("password1") String password1,
            @RequestParam("password2") String password2, @RequestParam("name") String name,
            @RequestParam("unitNumber") String unitNumber, @RequestParam("roomNumber") String roomNumber,
            HttpSession session, RedirectAttributes redirectAttributes
    ) {
        if (!password1.equals(password2)) {
            redirectAttributes.addAttribute("error", "密码不一致");
            return "redirect:/register";
        }
        int registerStatus = Integer.parseInt(residenceFeign.register(username, password1, name, unitNumber, roomNumber));
        switch (registerStatus) {
            case 0:
                session.setAttribute("username", username);
                return "redirect:/residence";
            case 1:
                redirectAttributes.addAttribute("error", "用户名已存在");
                return "redirect:/register";
            case 2:
                redirectAttributes.addAttribute("error", "不存在该房间");
                return "redirect:/register";
            case 3:
                redirectAttributes.addAttribute("error", "发生错误");
                return "redirect:/register";
        }
        throw new RuntimeException("发生错误");
    }
}
