package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "public.administratie", schema = "public", catalog = "postgres")
public class AdministratieEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
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
    @Column(name = "telefon")
    private String telefon;
    @Basic
    @Column(name = "status")
    private String status;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        AdministratieEntity that = (AdministratieEntity) o;
        return id == that.id && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(idnp, that.idnp) && Objects.equals(telefon, that.telefon) && Objects.equals(status, that.status) && Arrays.equals(poza, that.poza);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nume, prenume, idnp, telefon, status);
        result = 31 * result + Arrays.hashCode(poza);
        return result;
    }
}
