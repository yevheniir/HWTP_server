package com.yevheniir.hwtp.model;

import org.springframework.core.io.Resource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderDTO {

    private Long id;

    private String email;

    private String comment;

    private Resource screen;

    private List<Stuff> stuffs = new ArrayList<>();

    public OrderDTO() {

    }

    public OrderDTO(Long id, String email, String comment, Resource screen, List<Stuff> stuffs) {
        this.id = id;
        this.email = email;
        this.comment = comment;
        this.screen = screen;
        this.stuffs = stuffs;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", screen='" + screen + '\'' +
                ", stuffs=" + stuffs +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Resource getScreen() {
        return screen;
    }

    public void setScreen(Resource screen) {
        this.screen = screen;
    }

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<Stuff> stuffs) {
        this.stuffs = stuffs;
    }
}
