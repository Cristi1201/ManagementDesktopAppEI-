package com.utm.msei.persistence.entity;

import com.utm.msei.persistence.entity.enums.EntityTypeEnum;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "public.mesaje", schema = "public", catalog = "postgres")
public class MesajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "sender_type")
    private EntityTypeEnum senderType;
    @Basic
    @Column(name = "id_sender")
    private int idSender;
    @Basic
    @Column(name = "recipient_type")
    private EntityTypeEnum recipientType;
    @Basic
    @Column(name = "id_recipient")
    private int idRecipient;
    @Basic
    @Column(name = "timestamp")
    private Timestamp timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EntityTypeEnum getSenderType() {
        return senderType;
    }

    public void setSenderType(EntityTypeEnum senderType) {
        this.senderType = senderType;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public EntityTypeEnum getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(EntityTypeEnum recipientType) {
        this.recipientType = recipientType;
    }

    public int getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(int idRecipient) {
        this.idRecipient = idRecipient;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesajeEntity that = (MesajeEntity) o;
        return id == that.id && idSender == that.idSender && idRecipient == that.idRecipient && Objects.equals(text, that.text) && Objects.equals(senderType, that.senderType) && Objects.equals(recipientType, that.recipientType) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, senderType, idSender, recipientType, idRecipient, timestamp);
    }
}
