package com.yevheniir.hwtp.controller;

import com.yevheniir.hwtp.model.Order;
import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.repository.OrderRepository;
import com.yevheniir.hwtp.repository.UserDetailsRepo;
import com.yevheniir.hwtp.service.HwtpService;
import com.yevheniir.hwtp.service.StorageService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
        storageService.storeFile(file);
    }

    @GetMapping("orders")
    List<Order> getOrders() {
        return hwtpService.getOrders();
    }

    @DeleteMapping("orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        hwtpService.deleteOrder(id);
    }

    @RequestMapping(value = "/image-response-entity", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
        HttpHeaders headers = new HttpHeaders();

        InputStream in = new FileInputStream(new File("src/main/resources/static/images/download1.jpeg"));
//        InputStream in = HwtpController.class.getResourceAsStream("../../../resources/static/images/download1.jpeg");
        byte[] media = new byte[in.available()]; //IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(media, headers, HttpStatus.OK);
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
