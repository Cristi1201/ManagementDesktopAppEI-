package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.durata_lectie", schema = "public", catalog = "postgres")
public class DurataLectieEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Column(name = "durata")
    private String durata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DurataLectieEntity that = (DurataLectieEntity) o;
        return id == that.id && Objects.equals(durata, that.durata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, durata);
    }
}
