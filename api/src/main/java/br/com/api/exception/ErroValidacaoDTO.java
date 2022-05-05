package br.com.api.exception;

import java.util.HashMap;

public class ErroValidacaoDTO {

    private HashMap<String, String> erros = new HashMap<>();

    public HashMap<String, String> getErros() {
        return erros;
    }

    public void setErros(HashMap<String, String> erros) {
        this.erros = erros;
    }
}
