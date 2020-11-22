package br.com.cep.api.exceptioncontroller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cep.domain.DomainException;



@ControllerAdvice
@EnableWebMvc
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	/*
	 @Override
	    protected ResponseEntity<Object> handleExceptionInternal(
	                                      Exception exception, 
	                                      Object body, 
	                                      HttpHeaders headers, 
	                                      HttpStatus status, 
	                                      WebRequest request) {
	        // for all exceptions that are not overriden, the body is null, so we can
	        // just provide new body based on error message and call super method
	        var apiError = Objects.isNull(body) 
	                ? new Problema() // <-- 
	                : body;
	        return super.handleExceptionInternal(exception, apiError, headers, status, request);
	    }
	    */
	
	 @Override
	 protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	     logger.info(ex.getMessage());
	     
	     var campos = new ArrayList<Problema.Campo>();
			
			for(ObjectError error: ex.getBindingResult().getAllErrors()) {
				
				var nome = ((FieldError)error).getField();
				//error.getDefaultMessage();
				var mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
				
				var campo = new Problema.Campo();
				campo.setNome(nome);
				campo.setMensagem(mensagem);
				
				campos.add(campo);
				
			}
			
			var problema = new Problema();
			problema.setStatus(status.value());
			problema.setTitulo("Campos inválidos");
			problema.setDataHora(LocalDateTime.now());
			problema.setCampos(campos);
	     
	     
			return super.handleExceptionInternal(ex, problema, headers, status, request);
	 }
	 
	
	@Override
	@Order(1)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var campos = new ArrayList<Problema.Campo>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			
			var nome = ((FieldError)error).getField();
			//error.getDefaultMessage();
			var mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			var campo = new Problema.Campo();
			campo.setNome(nome);
			campo.setMensagem(mensagem);
			
			campos.add(campo);
			
		}
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Campos inválidos");
		problema.setDataHora(LocalDateTime.now());
		problema.setCampos(campos);
				
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	

	//Informa que esse método deve tratar as exceções do tipo DomainException
	@ExceptionHandler(DomainException.class)
	@Order(2)
	public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(LocalDateTime.now());
		
		
		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	
	@ExceptionHandler(value = Exception.class)
	@Order(3)
	public ResponseEntity<Object> handleExceptionn(Exception ex, WebRequest request) {
		var status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Erro interno");
		problema.setDataHora(LocalDateTime.now());
		
		
		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	

	
	
}
