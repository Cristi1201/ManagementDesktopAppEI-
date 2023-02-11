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
public class MesajeEntityDto implements Serializable {
    private final int id;
    private final String text;
    private final EntityTypeEnum senderType;
    private final int idSender;
    private final EntityTypeEnum recipientType;
    private final int idRecipient;
    private final Timestamp timestamp;
}
