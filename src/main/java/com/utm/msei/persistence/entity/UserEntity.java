package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "public.user", schema = "public", catalog = "postgres")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "user_type")
    private String userType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin")
    private AdministratieEntity idAdmin;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity idProfesor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_elev")
    private ElevEntity idElev;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public AdministratieEntity getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(AdministratieEntity idAdmin) {
        this.idAdmin = idAdmin;
    }

    public ProfesorEntity getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(ProfesorEntity idProfesor) {
        this.idProfesor = idProfesor;
    }

    public ElevEntity getIdElev() {
        return idElev;
    }

    public void setIdElev(ElevEntity idElev) {
        this.idElev = idElev;
    }

    // TODO add ParintiEntity, if have time

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(userType, that.userType) && Objects.equals(idAdmin, that.idAdmin) && Objects.equals(idProfesor, that.idProfesor) && Objects.equals(idElev, that.idElev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, userType, idAdmin, idProfesor, idElev);
    }
}
