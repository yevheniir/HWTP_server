package com.yevheniir.hwtp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Image {

    @NotNull
    @Id
    private String id;

    @NotNull
    private byte[] image;

    public Image() {
    }

    public Image(@NotNull String id, @NotNull byte[] image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
