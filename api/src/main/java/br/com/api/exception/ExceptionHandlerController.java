package br.com.api.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class ExceptionHandlerController {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroValidacaoDTO handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        ErroValidacaoDTO erroValidacaoDTO = new ErroValidacaoDTO();
        List<FieldError> camposComErro = exception.getBindingResult().getFieldErrors();

        camposComErro.stream().forEach(erroCampoX -> {
            String mensagemErro = messageSource.getMessage(erroCampoX, LocaleContextHolder.getLocale());
            erroValidacaoDTO.getErros().put(erroCampoX.getField(), mensagemErro);
        });

        return erroValidacaoDTO;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ErroDtoSimples handlerFuncionarioNaoEncontrado(FuncionarioNaoEncontradoException exception) {

        ErroDtoSimples dtoErro = new ErroDtoSimples(exception.getMessage());
        return dtoErro;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HoleriteGeradoException.class)
    public ErroDtoSimples handlerHoleriteJaGerado(HoleriteGeradoException exception) {

        ErroDtoSimples dtoErro = new ErroDtoSimples(exception.getMessage());
        return dtoErro;
    }
}
