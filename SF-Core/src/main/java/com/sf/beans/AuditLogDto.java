package com.sf.beans;

import lombok.Data;

@Data
public class AuditLogDto {


    private Long id;
    private Long productId;
    private Long userId;
    private Long actionId;
    private String date;

}
