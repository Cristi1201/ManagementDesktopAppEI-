package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "public.activitate", schema = "public", catalog = "postgres")
public class ActivitateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_orar", nullable = false)
    private OrarEntity idOrar;
    @Basic
    @Column(name = "data")
    private Date data;
    @Basic
    @Column(name = "subiect")
    private String subiect;
    @Basic
    @Column(name = "tema")
    private String tema;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrarEntity getIdOrar() {
        return idOrar;
    }

    public void setIdOrar(OrarEntity idOrar) {
        this.idOrar = idOrar;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSubiect() {
        return subiect;
    }

    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitateEntity that = (ActivitateEntity) o;
        return id == that.id && idOrar == that.idOrar && Objects.equals(data, that.data) && Objects.equals(subiect, that.subiect) && Objects.equals(tema, that.tema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idOrar, data, subiect, tema);
    }
}
