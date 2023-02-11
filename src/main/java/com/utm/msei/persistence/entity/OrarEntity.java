package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.orar", schema = "public", catalog = "postgres")
public class OrarEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_disc_prof", nullable = false)
    private DisciplinaProfesorEntity idDiscProf;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_zi", nullable = false)
    private ZiSaptamanaEntity idZi;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_clasa", nullable = false)
    private ClasaEntity idClasa;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_durata", nullable = false)
    private DurataLectieEntity idDurata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DisciplinaProfesorEntity getIdDiscProf() {
        return idDiscProf;
    }

    public void setIdDiscProf(DisciplinaProfesorEntity idDiscProf) {
        this.idDiscProf = idDiscProf;
    }

    public ZiSaptamanaEntity getIdZi() {
        return idZi;
    }

    public void setIdZi(ZiSaptamanaEntity idZi) {
        this.idZi = idZi;
    }

    public ClasaEntity getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(ClasaEntity idClasa) {
        this.idClasa = idClasa;
    }

    public DurataLectieEntity getIdDurata() {
        return idDurata;
    }

    public void setIdDurata(DurataLectieEntity idDurata) {
        this.idDurata = idDurata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrarEntity that = (OrarEntity) o;
        return id == that.id && idDiscProf == that.idDiscProf && idZi == that.idZi && idClasa == that.idClasa && idDurata == that.idDurata;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDiscProf, idZi, idClasa, idDurata);
    }
}
