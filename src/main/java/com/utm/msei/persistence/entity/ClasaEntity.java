package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.clasa", schema = "public", catalog = "postgres")
public class ClasaEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Column(name = "nume")
    private String nume;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_profesor", nullable = false)
    private ProfesorEntity idProfesor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public ProfesorEntity getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(ProfesorEntity idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClasaEntity that = (ClasaEntity) o;
        return id == that.id && idProfesor == that.idProfesor && Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, idProfesor);
    }
}
