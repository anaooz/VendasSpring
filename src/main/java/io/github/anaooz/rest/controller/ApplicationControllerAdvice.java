package io.github.anaooz.rest.controller;

import io.github.anaooz.exception.PedidoNaoEncontradoException;
import io.github.anaooz.exception.RegraNegocioException;
import io.github.anaooz.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException exception) {
        String mensagemErro = exception.getMessage();

        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidosNotFOundException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }
}
