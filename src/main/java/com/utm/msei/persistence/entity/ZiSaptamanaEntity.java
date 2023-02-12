package com.utm.msei.persistence.entity;

import javax.persistence.*;

@Entity
//@Table(name = "public.zi_saptamana", catalog = "msei_db")
@Table(name = "public.zi_saptamana", schema = "public", catalog = "msei_db")
public class ZiSaptamanaEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
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

        if (id != that.id) return false;
        if (zi != null ? !zi.equals(that.zi) : that.zi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (zi != null ? zi.hashCode() : 0);
        return result;
    }
}
