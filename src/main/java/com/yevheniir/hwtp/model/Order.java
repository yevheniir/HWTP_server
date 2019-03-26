package com.yevheniir.hwtp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ord")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    private String comment;

    @NotNull
    private String screen;

//    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy="ord")

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "order_stuff",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "stuff_id")
    )
    private List<Stuff> stuffs = new ArrayList<>();

    public Order() {
    }

    public Order(String email, String comment, String screen, List<Stuff> stuffs) {
        this.email = email;
        this.comment = comment;
        this.screen = screen;
        this.stuffs = stuffs;
    }

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<Stuff> stuffs) {
        this.stuffs = stuffs;
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

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", screen='" + screen + '\'' +
                ", stuffs=" + stuffs +
                '}';
    }
}
