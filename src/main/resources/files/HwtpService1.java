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

    public void deleteOrder(Long id) {
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
