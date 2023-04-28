package com.grouproom.xyz.domain.memory.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MemoryResponse {
    private Long memorySeq;
    //    private String memoryImage;
    private Long aztSeq;
    private String aztName;
    private String date;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String location;

    public MemoryResponse(Long memorySeq, Long aztSeq, String aztName, LocalDateTime date, BigDecimal latitude, BigDecimal longitude, String location) {
        this.memorySeq = memorySeq;
//        this.memoryImage = memoryImage
        this.aztSeq = aztSeq;
        this.aztName = aztName;
        this.date = date.toString();
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }
}