package com.yevheniir.hwtp.controller;

import com.yevheniir.hwtp.exception.ForbiddenException;
import com.yevheniir.hwtp.model.Order;
import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.repository.OrderRepository;
import com.yevheniir.hwtp.repository.UserDetailsRepo;
import com.yevheniir.hwtp.service.AuthService;
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
import java.util.ArrayList;
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

    @Autowired
    private AuthService authService;

    @GetMapping("stuff")
    List<Stuff> getStuff() {
        return hwtpService.getStuff();
    }


    @PostMapping("stuff")
    Stuff addStuff(@RequestBody Stuff stuff, @RequestHeader(value="Auth") String auth ) {
        if (authService.validToken(auth)) {
            return hwtpService.addStuff(stuff);
        }
        throw new ForbiddenException();
    }

    @DeleteMapping("stuff/{id}")
    public void deleteStuff(@PathVariable Long id, @RequestHeader(value="Auth") String auth ) {
        if (authService.validToken(auth)) {
            hwtpService.deleteStuff(id);
        } else{
            throw new ForbiddenException();
        }
    }

    @PostMapping("stuff/file")
    void addFile(@RequestParam("file") MultipartFile file){
        storageService.storeFile(file);
    }

    @GetMapping("orders")
    List<Order> getOrders() {
        return hwtpService.getOrders();
    }

    @PostMapping("orders/screen")
    void addScreen(@RequestParam("photo") MultipartFile file){
        storageService.storeScreen(file);
    }

    @DeleteMapping("orders/{id}")
    void deleteOrder(@PathVariable Long id, @RequestHeader(value="Auth") String auth ) throws MessagingException {
        if (authService.validToken(auth)) {
            hwtpService.deleteOrder(id);
        } else{
            throw new ForbiddenException();
        }
    }

    @PostMapping("orders/{id}")
    void commentOrder(@PathVariable Long id, @RequestBody String comment, @RequestHeader(value="Auth") String auth ) throws MessagingException {
        if (authService.validToken(auth)) {
            hwtpService.commentOrder(id, comment);
        } else{
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("orders/cancel/{id}")
    void cancelOrder(@PathVariable Long id, @RequestHeader(value="Auth") String auth ) throws MessagingException {
        if (authService.validToken(auth)) {
            hwtpService.canceOlrder(id);
        } else{
            throw new ForbiddenException();
        }
    }

    @PostMapping("orders")
    Order addOrder(@RequestBody Order order) {
        return hwtpService.addOrder(order);
    }


    @GetMapping(value = "/image-response-entity/{path}")
    public void getImageAsResponseEntity(HttpServletResponse response, @PathVariable String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();

        InputStream in = new FileInputStream(new File("src/main/resources/images/" + path));
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(in, response.getOutputStream());
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

//    @GetMapping("users")
//    List<User> getusers() {
//        return userDetailsRepo.findAll();
//    }
//
//    @RequestMapping(value = "/controller", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity sendViaResponseEntity() {
//        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
//    }
//
//    @GetMapping("logout")
//    void userLogout() {
//        return;
//    }

    @PostMapping("password")
    public List<String> validatePassword(@RequestBody String password) {
        if (password.equals("password")) {
            List<String> l = new ArrayList<>();
            l.add(this.authService.createToken());
            return l ;
        } else {
            return null;
        }
    }
}
