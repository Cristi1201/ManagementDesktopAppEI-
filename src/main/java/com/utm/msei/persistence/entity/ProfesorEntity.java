package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "public.profesor", schema = "public", catalog = "postgres")
public class ProfesorEntity {
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

    // TODO see types of this and create enum (maybe) / delete this

    @Basic
    @Column(name = "grad_didactic")
    private String gradDidactic;
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

    public String getGradDidactic() {
        return gradDidactic;
    }

    public void setGradDidactic(String gradDidactic) {
        this.gradDidactic = gradDidactic;
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
        ProfesorEntity that = (ProfesorEntity) o;
        return id == that.id && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(idnp, that.idnp) && Objects.equals(telefon, that.telefon) && Objects.equals(gradDidactic, that.gradDidactic) && Arrays.equals(poza, that.poza);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nume, prenume, idnp, telefon, gradDidactic);
        result = 31 * result + Arrays.hashCode(poza);
        return result;
    }
}
