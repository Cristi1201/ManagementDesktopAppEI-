package com.utm.msei.persistence.entity;

import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

@Entity
@Table(name = "public_user", schema = "public", catalog = "msei_db")
public class UserEntity {
    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    @Length(min = 20, max = 100)
    private String password;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private EntityTypeEnum userType;
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
    @Column(name = "data_nastere")
    private Date dataNastere;
    @Basic
    @Column(name = "poza")
    private byte[] poza;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EntityTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(EntityTypeEnum userType) {
        this.userType = userType;
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

    public Date getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(Date dataNastere) {
        this.dataNastere = dataNastere;
    }

    public byte[] getPoza() {
        return poza;
    }

    public void setPoza(byte[] poza) {
        this.poza = poza;
    }

    @Override
//    @SuppressWarnings()
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (prenume != null ? !prenume.equals(that.prenume) : that.prenume != null) return false;
        if (idnp != null ? !idnp.equals(that.idnp) : that.idnp != null) return false;
        if (telefon != null ? !telefon.equals(that.telefon) : that.telefon != null) return false;
        if (dataNastere != null ? !dataNastere.equals(that.dataNastere) : that.dataNastere != null) return false;
        if (!Arrays.equals(poza, that.poza)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (prenume != null ? prenume.hashCode() : 0);
        result = 31 * result + (idnp != null ? idnp.hashCode() : 0);
        result = 31 * result + (telefon != null ? telefon.hashCode() : 0);
        result = 31 * result + (dataNastere != null ? dataNastere.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(poza);
        return result;
    }
}
