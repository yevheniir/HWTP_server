package com.yevheniir.hwtp.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="user")
//    private Set<Stuff> stuffs = new HashSet<>();

    public User() {
    }

//    public Set<Stuff> getStuffs() {
//        return stuffs;
//    }
//
//    public void setStuffs(Set<Stuff> stuffs) {
//        this.stuffs = stuffs;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userpic='" + userpic + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", locale='" + locale + '\'' +
                ", lastVisit=" + lastVisit +
                '}';
    }

    public User(String id, String name, String userpic, String email, String gender, String locale, LocalDateTime lastVisit) {
        this.id = id;
        this.name = name;
        this.userpic = userpic;
        this.email = email;
        this.gender = gender;
        this.locale = locale;
        this.lastVisit = lastVisit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }
}
