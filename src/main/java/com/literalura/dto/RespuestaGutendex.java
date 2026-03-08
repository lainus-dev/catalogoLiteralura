
package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaGutendex {
    private List<DatosLibroApi> results;
    public List<DatosLibroApi> getResults() { return results; }
    public void setResults(List<DatosLibroApi> results) { this.results = results; }
}
