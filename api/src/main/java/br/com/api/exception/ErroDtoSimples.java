package br.com.api.exception;

public class ErroDtoSimples {

    private  String erro;

    public ErroDtoSimples(String mensagemErro) {
        this.erro = mensagemErro;
    }

    public String getErro() {
        return erro;
    }
}
