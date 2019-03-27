package com.yevheniir.hwtp.service;

import com.yevheniir.hwtp.model.Order;
import com.yevheniir.hwtp.model.OrderDTO;
import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.model.User;
import com.yevheniir.hwtp.repository.HwtpRepository;
import com.yevheniir.hwtp.repository.OrderRepository;
import com.yevheniir.hwtp.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HwtpService {

    @Autowired
    private HwtpRepository hwtpRepository;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private EmailService emailService;

//    User currentUser;
//
//    public User getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(User currentUser) {
//        this.currentUser = currentUser;
//    }

    public List<Stuff> getStuff() {
        return hwtpRepository.findAllUnarchived();
    }

    public Stuff addStuff(Stuff stuff) {
//        stuff.setUser(userDetailsRepo.findById(stuff.getUserId()).get());
//        stuff.setUser(currentUser);
        return hwtpRepository.save(stuff);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) throws MessagingException {
        emailService.sendSimpleMessage("zheka.rachkovan@gmail.com", "Наначи говорит наааааааааа... 10 часов", orderRepository.getOne(id).getStuffs());

        orderRepository.deleteById(id);
    }

    public Order addOrder(Order order) {
        System.out.println(order.getStuffs());
        return orderRepository.save(order);
    }

    public void deleteStuff(Long id) {
        this.hwtpRepository.deleteById(id);
    }
}
