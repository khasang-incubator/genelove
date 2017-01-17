package io.khasang.genelove.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Multimedia albums (photo, video, audio)
 * @author Denis Guzikov
 * @version 1.0
 */

@Entity
public class Album {
    public Album() {
    }

    @Id
    private long id;
    private String name;
    private String descr;
    @ManyToOne
    private Relative relative;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }
}