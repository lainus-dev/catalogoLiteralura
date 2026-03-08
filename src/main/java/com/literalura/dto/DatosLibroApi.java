
package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibroApi {
    private String title;
    private List<DatosAutorApi> authors;
    private List<String> languages;
    private Double download_count;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<DatosAutorApi> getAuthors() { return authors; }
    public void setAuthors(List<DatosAutorApi> authors) { this.authors = authors; }
    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }
    public Double getDownload_count() { return download_count; }
    public void setDownload_count(Double download_count) { this.download_count = download_count; }
}
