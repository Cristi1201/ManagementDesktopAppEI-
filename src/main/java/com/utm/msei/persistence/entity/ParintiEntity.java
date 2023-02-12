package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.parinti", schema = "public", catalog = "postgres")
public class ParintiEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Column(name = "tata_nume")
    private String tataNume;
    @Basic
    @Column(name = "tata_prenume")
    private String tataPrenume;
    @Basic
    @Column(name = "tata_telefon")
    private String tataTelefon;
    @Basic
    @Column(name = "mama_nume")
    private String mamaNume;
    @Basic
    @Column(name = "mama__prenume")
    private String mamaPrenume;
    @Basic
    @Column(name = "mama__telefon")
    private String mamaTelefon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTataNume() {
        return tataNume;
    }

    public void setTataNume(String tataNume) {
        this.tataNume = tataNume;
    }

    public String getTataPrenume() {
        return tataPrenume;
    }

    public void setTataPrenume(String tataPrenume) {
        this.tataPrenume = tataPrenume;
    }

    public String getTataTelefon() {
        return tataTelefon;
    }

    public void setTataTelefon(String tataTelefon) {
        this.tataTelefon = tataTelefon;
    }

    public String getMamaNume() {
        return mamaNume;
    }

    public void setMamaNume(String mamaNume) {
        this.mamaNume = mamaNume;
    }

    public String getMamaPrenume() {
        return mamaPrenume;
    }

    public void setMamaPrenume(String mamaPrenume) {
        this.mamaPrenume = mamaPrenume;
    }

    public String getMamaTelefon() {
        return mamaTelefon;
    }

    public void setMamaTelefon(String mamaTelefon) {
        this.mamaTelefon = mamaTelefon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParintiEntity that = (ParintiEntity) o;
        return id == that.id && Objects.equals(tataNume, that.tataNume) && Objects.equals(tataPrenume, that.tataPrenume) && Objects.equals(tataTelefon, that.tataTelefon) && Objects.equals(mamaNume, that.mamaNume) && Objects.equals(mamaPrenume, that.mamaPrenume) && Objects.equals(mamaTelefon, that.mamaTelefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tataNume, tataPrenume, tataTelefon, mamaNume, mamaPrenume, mamaTelefon);
    }
}
