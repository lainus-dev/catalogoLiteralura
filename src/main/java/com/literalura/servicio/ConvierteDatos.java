
package com.literalura.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.RespuestaGutendex;
import org.springframework.stereotype.Service;

@Service
public class ConvierteDatos {
    private final ObjectMapper mapper = new ObjectMapper();

    public RespuestaGutendex obtenerRespuesta(String json) {
        try {
            return mapper.readValue(json, RespuestaGutendex.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir JSON", e);
        }
    }
}
