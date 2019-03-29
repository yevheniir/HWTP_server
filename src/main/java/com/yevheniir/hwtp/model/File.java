package com.yevheniir.hwtp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class File {

    @NotNull
    @Id
    private String id;

    @NotNull
    private byte[] file;

    public File() {
    }

    public File(@NotNull String id, @NotNull byte[] file) {
        this.id = id;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
