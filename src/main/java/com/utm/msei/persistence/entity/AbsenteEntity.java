package com.utm.msei.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "public.absente", schema = "public", catalog = "postgres")
public class AbsenteEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq", allocationSize = 1)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_elev", nullable = false)
    private ElevEntity idElev;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_lectie", nullable = false)
    private OrarEntity idLectie;
    @Basic
    @Column(name = "m_n")
    private String mN;
    @Basic
    @Column(name = "data")
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ElevEntity getIdElev() {
        return idElev;
    }

    public void setIdElev(ElevEntity idElev) {
        this.idElev = idElev;
    }

    public OrarEntity getIdLectie() {
        return idLectie;
    }

    public void setIdLectie(OrarEntity idLectie) {
        this.idLectie = idLectie;
    }

    public String getmN() {
        return mN;
    }

    public void setmN(String mN) {
        this.mN = mN;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenteEntity that = (AbsenteEntity) o;
        return id == that.id && idElev == that.idElev && idLectie == that.idLectie && Objects.equals(mN, that.mN) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idElev, idLectie, mN, data);
    }
}
