package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.MesajeEntity;
import com.utm.msei.persistence.entity.enums.EntityTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link MesajeEntity} entity
 */
@Data
public class MesajeDto implements Serializable {
    private int id;
    private String text;
    private EntityTypeEnum senderType;
    private int idSender;
    private EntityTypeEnum recipientType;
    private int idRecipient;
    private Timestamp timestamp;

    public MesajeDto() {}

    public MesajeDto(String text, EntityTypeEnum senderType, int idSender, EntityTypeEnum recipientType, int idRecipient, Timestamp timestamp) {
        this.text = text;
        this.senderType = senderType;
        this.idSender = idSender;
        this.recipientType = recipientType;
        this.idRecipient = idRecipient;
        this.timestamp = timestamp;
    }
}
