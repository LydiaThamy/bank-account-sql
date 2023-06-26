package sg.edu.nus.iss.day24_paflecture.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BankAccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    // this handles internal server error exceptions, which are categorised as http
    // exceptions
    public ModelAndView handleBankAccountNotFoundException(BankAccountNotFoundException ex,
            HttpServletRequest request) {

        return new ModelAndView("error.html").addObject("errorMessage",
                new ErrorMessage(404, new Date(), ex.getMessage(), request.getRequestURI()));

    }

    @ExceptionHandler(AccountBlockedOrDisabledException.class)
    public ModelAndView handleBankAccountBlockedOrDisabledException(AccountBlockedOrDisabledException ex,
            HttpServletRequest request) {

        return new ModelAndView("error.html").addObject("errorMessage",
                new ErrorMessage(404, new Date(), ex.getMessage(), request.getRequestURI()));

    }

    @ExceptionHandler(AmountNotSufficientException.class)
    public ModelAndView handleAmountNotSufficientException(AmountNotSufficientException ex,
            HttpServletRequest request) {

        return new ModelAndView("error.html").addObject("errorMessage",
                new ErrorMessage(404, new Date(), ex.getMessage(), request.getRequestURI()));

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex,
            HttpServletRequest request) {

        return new ModelAndView("error.html").addObject("errorMessage",
                new ErrorMessage(404, new Date(), ex.getMessage(), request.getRequestURI()));

    }

    @ExceptionHandler(HttpServerErrorException.class) // captures server exceptions
    public ModelAndView handleHttpServerErrorException(HttpServerErrorException ex,
            HttpServletRequest request) {

        return new ModelAndView("error.html").addObject("errorMessage",
                new ErrorMessage(404, new Date(), ex.getMessage(), request.getRequestURI()));

    }

}
