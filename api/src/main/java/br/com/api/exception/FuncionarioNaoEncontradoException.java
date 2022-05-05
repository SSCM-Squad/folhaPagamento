package br.com.api.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException{

    public FuncionarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
