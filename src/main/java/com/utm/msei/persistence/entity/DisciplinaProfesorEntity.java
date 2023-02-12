package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.disciplina_profesor", schema = "public", catalog = "postgres")
public class DisciplinaProfesorEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;

    // TODO see this relationship here

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_profesor", nullable = false)
    private ProfesorEntity idProfesor;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_disciplina", nullable = false)
    private DisciplinaEntity idDisciplina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfesorEntity getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(ProfesorEntity idProfesor) {
        this.idProfesor = idProfesor;
    }

    public DisciplinaEntity getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(DisciplinaEntity idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaProfesorEntity that = (DisciplinaProfesorEntity) o;
        return id == that.id && idProfesor == that.idProfesor && idDisciplina == that.idDisciplina;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idProfesor, idDisciplina);
    }
}
