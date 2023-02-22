package com.utm.msei.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "public_elev", schema = "public", catalog = "msei_db")
public class ElevEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @Basic
    @Column(name = "id_clasa")
    private Integer idClasa;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_parinti", nullable = false)
    private ParintiEntity idParinti;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity idUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(Integer idClasa) {
        this.idClasa = idClasa;
    }

    public ParintiEntity getIdParinti() {
        return idParinti;
    }

    public void setIdParinti(ParintiEntity idParinti) {
        this.idParinti = idParinti;
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

        ElevEntity that = (ElevEntity) o;

        if (id != that.id) return false;
        if (idClasa != null ? !idClasa.equals(that.idClasa) : that.idClasa != null) return false;
        if (idParinti != null ? !idParinti.equals(that.idParinti) : that.idParinti != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idClasa != null ? idClasa.hashCode() : 0);
        result = 31 * result + (idParinti != null ? idParinti.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}
