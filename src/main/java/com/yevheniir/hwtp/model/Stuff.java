package com.yevheniir.hwtp.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stuff")
public class Stuff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    private String teacher;

    @NotNull
    private int course;

    @NotNull
    private int semester;

    @NotNull
    private int lab;

    @NotNull
    private int exercise;

    @NotNull
    private int price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Override
    public String toString() {
        return "Stuff{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", course=" + course +
                ", semester=" + semester +
                ", lab=" + lab +
                ", exercise=" + exercise +
                ", price=" + price +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int cource) {
        this.course = cource;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getLab() {
        return lab;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    public int getExercise() {
        return exercise;
    }

    public void setExercise(int exercise) {
        this.exercise = exercise;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
