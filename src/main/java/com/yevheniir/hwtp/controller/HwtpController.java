package com.yevheniir.hwtp.controller;

import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.model.User;
import com.yevheniir.hwtp.repository.UserDetailsRepo;
import com.yevheniir.hwtp.service.HwtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin
public class HwtpController {

    @Autowired
    private HwtpService hwtpService;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @ResponseBody
    @GetMapping("stuff")
    List<Stuff> getStuff() {
        System.out.println(hwtpService.getCurrentUser());
        return hwtpService.getStuff();
    }

    @ResponseBody
    @PostMapping("admin/stuff")
    Stuff addStuff(@RequestBody Stuff stuff) {

        return hwtpService.addStuff(stuff);
    }

    @GetMapping("login/getUser")
    public String getUser(Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", hwtpService.getCurrentUser());

        model.addAttribute("frontendData", data);

        return "index";
    }

    @GetMapping("users")
    @ResponseBody
    List<User> getusers() {
        return userDetailsRepo.findAll();
    }

    @ResponseBody
    @GetMapping("logout")
    void userLogout() {
        return;
    }
}
