package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "public.elev", schema = "public", catalog = "postgres")
public class ElevEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nume")
    private String nume;
    @Basic
    @Column(name = "prenume")
    private String prenume;
    @Basic
    @Column(name = "idnp")
    private String idnp;
    @Basic
    @Column(name = "data_nastere")
    private Date dataNastere;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_clasa", nullable = false)
    private ClasaEntity idClasa;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_parinti", nullable = false)
    private ParintiEntity idParinti;
    @Basic
    @Column(name = "telefon")
    private String telefon;
    @Basic
    @Column(name = "poza")
    private byte[] poza;

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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getIdnp() {
        return idnp;
    }

    public void setIdnp(String idnp) {
        this.idnp = idnp;
    }

    public Date getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(Date dataNastere) {
        this.dataNastere = dataNastere;
    }

    public ClasaEntity getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(ClasaEntity idClasa) {
        this.idClasa = idClasa;
    }

    public ParintiEntity getIdParinti() {
        return idParinti;
    }

    public void setIdParinti(ParintiEntity idParinti) {
        this.idParinti = idParinti;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public byte[] getPoza() {
        return poza;
    }

    public void setPoza(byte[] poza) {
        this.poza = poza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElevEntity that = (ElevEntity) o;
        return id == that.id && idClasa == that.idClasa && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(idnp, that.idnp) && Objects.equals(dataNastere, that.dataNastere) && Objects.equals(idParinti, that.idParinti) && Objects.equals(telefon, that.telefon) && Arrays.equals(poza, that.poza);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nume, prenume, idnp, dataNastere, idClasa, idParinti, telefon);
        result = 31 * result + Arrays.hashCode(poza);
        return result;
    }
}
