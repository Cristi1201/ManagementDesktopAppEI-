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
    @JoinColumn(name = "id_tata", nullable = false)
    private TataEntity idTata;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mama", nullable = false)
    private MamaEntity idMama;
    @OneToOne()
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

    public TataEntity getIdTata() {
        return idTata;
    }
    public MamaEntity getIdMama() {
        return idMama;
    }

    public void setIdTata(TataEntity idTata) {
        this.idTata = idTata;
    }

    public void setIdMama(MamaEntity idMama) {
        this.idMama = idMama;
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
        if (idTata != null ? !idTata.equals(that.idTata) : that.idTata != null) return false;
        if (idMama != null ? !idMama.equals(that.idMama) : that.idMama != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idClasa != null ? idClasa.hashCode() : 0);
        result = 31 * result + (idTata != null ? idTata.hashCode() : 0);
        result = 31 * result + (idMama != null ? idMama.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}
