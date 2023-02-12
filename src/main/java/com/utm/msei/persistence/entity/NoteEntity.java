package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "public.note", schema = "public", catalog = "postgres")
public class NoteEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_elev", nullable = false)
    private ElevEntity idElev;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_lectie", nullable = false)
    private OrarEntity idLectie;
    @Basic
    @Column(name = "nota")
    private int nota;
    @Basic
    @Column(name = "data")
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ElevEntity getIdElev() {
        return idElev;
    }

    public void setIdElev(ElevEntity idElev) {
        this.idElev = idElev;
    }

    public OrarEntity getIdLectie() {
        return idLectie;
    }

    public void setIdLectie(OrarEntity idLectie) {
        this.idLectie = idLectie;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteEntity that = (NoteEntity) o;
        return id == that.id && idElev == that.idElev && idLectie == that.idLectie && nota == that.nota && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idElev, idLectie, nota, data);
    }
}
