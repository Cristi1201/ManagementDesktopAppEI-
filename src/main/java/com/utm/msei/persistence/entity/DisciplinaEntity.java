package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.disciplina", schema = "public", catalog = "postgres")
public class DisciplinaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "disciplina")
    private String disciplina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaEntity that = (DisciplinaEntity) o;
        return id == that.id && Objects.equals(disciplina, that.disciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, disciplina);
    }
}
