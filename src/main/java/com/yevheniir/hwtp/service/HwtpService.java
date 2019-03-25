package com.yevheniir.hwtp.service;

import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.model.User;
import com.yevheniir.hwtp.repository.HwtpRepository;
import com.yevheniir.hwtp.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HwtpService {

    @Autowired
    private HwtpRepository hwtpRepository;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Stuff> getStuff() {
        return hwtpRepository.findAll();
    }

    public Stuff addStuff(Stuff stuff) {
//        stuff.setUser(userDetailsRepo.findById(stuff.getUserId()).get());
        stuff.setUser(currentUser);
        return hwtpRepository.save(stuff);
    }
}
