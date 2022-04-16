package br.com.api.models;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Cabecalho {

    private String cod;

    private LocalDate data;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
