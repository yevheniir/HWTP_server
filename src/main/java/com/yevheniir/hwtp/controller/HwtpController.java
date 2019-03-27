package com.yevheniir.hwtp.controller;

import com.yevheniir.hwtp.model.Order;
import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.repository.OrderRepository;
import com.yevheniir.hwtp.repository.UserDetailsRepo;
import com.yevheniir.hwtp.service.EmailService;
import com.yevheniir.hwtp.service.HwtpService;
import com.yevheniir.hwtp.service.StorageService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin
public class HwtpController {

    @Autowired
    private HwtpService hwtpService;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private EmailService emailService;

    @GetMapping("stuff")
    List<Stuff> getStuff() {
        return hwtpService.getStuff();
    }

    @PostMapping("orders")
    Order addOrder(@RequestBody Order order) {
        return hwtpService.addOrder(order);
    }

    @PostMapping("admin/stuff")
    Stuff addStuff(@RequestBody Stuff stuff) {
        return hwtpService.addStuff(stuff);
    }

    @PostMapping("orders/screen")
    void addScreen(@RequestParam("photo") MultipartFile file){
        storageService.storeScreen(file);
    }

    @GetMapping("orders")
    List<Order> getOrders() {
        return hwtpService.getOrders();
    }

    @DeleteMapping("orders/{id}")
    void deleteOrder(@PathVariable Long id) throws MessagingException {
        hwtpService.deleteOrder(id);
    }


    @GetMapping(value = "/image-response-entity/{path}")
    public void getImageAsResponseEntity(HttpServletResponse response, @PathVariable String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();

        InputStream in = new FileInputStream(new File("src/main/resources/images/" + path));
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(in, response.getOutputStream());
    }


    @DeleteMapping("stuff/{id}")
    public void deleteStuff(@PathVariable Long id) {
        hwtpService.deleteStuff(id);
    }

    @PostMapping("stuff/file")
    void addFile(@RequestParam("file") MultipartFile file){
        storageService.storeFile(file);
    }



//
//    @GetMapping("login/getUser")
//    public String getUser(Model model) {
//        HashMap<Object, Object> data = new HashMap<>();
//
//        data.put("profile", hwtpService.getCurrentUser());
//
//        model.addAttribute("frontendData", data);
//
//        return "index";
//    }
//
//    @GetMapping("users")
//    List<User> getusers() {
//        return userDetailsRepo.findAll();
//    }
//
//    @GetMapping("logout")
//    void userLogout() {
//        return;
//    }
}
