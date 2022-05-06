package br.com.api.exception;

public class FuncionarioJaExistenteException extends RuntimeException {

    public FuncionarioJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
