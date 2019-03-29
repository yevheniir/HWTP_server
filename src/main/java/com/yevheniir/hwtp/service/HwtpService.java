package com.yevheniir.hwtp.service;

import com.yevheniir.hwtp.model.Order;
import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.repository.HwtpRepository;
import com.yevheniir.hwtp.repository.OrderRepository;
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
    private OrderRepository orderRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private EmailService emailService;


    public List<Stuff> getStuff() {
        return hwtpRepository.findAllUnarchived();
    }

    public Stuff addStuff(Stuff stuff) {
        return hwtpRepository.save(stuff);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) throws MessagingException {
        emailService.sendSimpleMessage(orderRepository.getOne(id).getEmail(), "Оплата прошла, заказ доставлен",
                "Наначи говорит наааааааааа... 10 часов", orderRepository.getOne(id).getStuffs());
        orderRepository.deleteById(id);
    }

    public void canceOlrder(Long id) throws MessagingException {
        emailService.sendSimpleMessage(orderRepository.getOne(id).getEmail(), "Оплата не прошла, заказ анулирован",
                "Наначи говорит пошол наааааааааа... 10 часов", new ArrayList<Stuff>());
        orderRepository.deleteById(id);
    }

    public void commentOrder(Long id, String comment) throws MessagingException {
        emailService.sendSimpleMessage(orderRepository.getOne(id).getEmail(), "Пришел комментарий к вашему заказу",
                comment, new ArrayList<Stuff>());
    }

    public Order addOrder(Order order) {
        System.out.println(order.getStuffs());
        return orderRepository.save(order);
    }

    public void deleteStuff(Long id) {
        Stuff stuff = this.hwtpRepository.getOne(id);
        stuff.setArchive(true);
        this.hwtpRepository.save(stuff);
    }
}
