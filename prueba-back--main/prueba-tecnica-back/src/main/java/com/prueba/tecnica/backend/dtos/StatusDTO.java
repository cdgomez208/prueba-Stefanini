package com.prueba.tecnica.backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.prueba.tecnica.backend.utils.contants.SUCCESS_CODE;
import static com.prueba.tecnica.backend.utils.contants.SUCCESS_MESSAGE;

@Data
public class StatusDTO {

    private int  code;
    private String message;

    public StatusDTO (String message,int code)
    {
        this.message = message;
        this.code = code;
    }
}
