package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "public.tema_acasa", schema = "public", catalog = "postgres")
public class TemaAcasaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_elev", nullable = false)
    private ElevEntity idElev;
    @Basic
    @Column(name = "lucrare")
    private byte[] lucrare;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_activitate", nullable = false)
    private ActivitateEntity idActivitate;

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

    public byte[] getLucrare() {
        return lucrare;
    }

    public void setLucrare(byte[] lucrare) {
        this.lucrare = lucrare;
    }

    public ActivitateEntity getIdActivitate() {
        return idActivitate;
    }

    public void setIdActivitate(ActivitateEntity idActivitate) {
        this.idActivitate = idActivitate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemaAcasaEntity that = (TemaAcasaEntity) o;
        return id == that.id && idElev == that.idElev && idActivitate == that.idActivitate && Arrays.equals(lucrare, that.lucrare);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, idElev, idActivitate);
        result = 31 * result + Arrays.hashCode(lucrare);
        return result;
    }
}
