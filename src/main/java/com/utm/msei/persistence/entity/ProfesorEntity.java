package com.utm.msei.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "public_profesor", schema = "public", catalog = "msei_db")
public class ProfesorEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Column(name = "grad_didactic")
    private String gradDidactic;  // TODO to review
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity idUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradDidactic() {
        return gradDidactic;
    }

    public void setGradDidactic(String gradDidactic) {
        this.gradDidactic = gradDidactic;
    }

    public UserEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfesorEntity that = (ProfesorEntity) o;

        if (id != that.id) return false;
        if (gradDidactic != null ? !gradDidactic.equals(that.gradDidactic) : that.gradDidactic != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gradDidactic != null ? gradDidactic.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}
