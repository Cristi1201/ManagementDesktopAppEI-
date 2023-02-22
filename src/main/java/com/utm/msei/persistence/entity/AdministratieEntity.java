package com.utm.msei.persistence.entity;

import com.utm.msei.persistence.dto.enums.AdministratorStatusEnum;

import javax.persistence.*;

@Entity
@Table(name = "public_administratie", schema = "public", catalog = "msei_db")
public class AdministratieEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AdministratorStatusEnum status;
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserEntity idUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdministratorStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AdministratorStatusEnum status) {
        this.status = status;
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

        AdministratieEntity that = (AdministratieEntity) o;

        if (id != that.id) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}
