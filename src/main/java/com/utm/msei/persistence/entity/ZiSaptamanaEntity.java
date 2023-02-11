package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.zi_saptamana", schema = "public", catalog = "postgres")
public class ZiSaptamanaEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private int id;
    @Basic
    @Column(name = "zi")
    private String zi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZiSaptamanaEntity that = (ZiSaptamanaEntity) o;
        return id == that.id && Objects.equals(zi, that.zi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zi);
    }
}
