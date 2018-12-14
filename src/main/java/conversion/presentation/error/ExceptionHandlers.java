package conversion.presentation.error;

import conversion.domain.IllegalConversionException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    public static final String ERROR_PAGE_URL = "error";
    public static final String ERROR_PATH = "failure";

    public static final String ERROR_TYPE = "errorType";
    public static final String ERROR_INFO = "errorInfo";

    public static final String ERROR_CONV_FAILED = "Conversion Failed";

    public static final String ERROR_GENERIC_TYPE = "Operation Failed";
    public static final String ERROR_GENERIC_INFO = "Sorry, something went wrong. Please try again.";

    @ExceptionHandler(IllegalConversionException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleException(IllegalConversionException ex, Model model) {
        if (ex.getMessage().toUpperCase().contains("FOUND")) {
            model.addAttribute(ERROR_TYPE, ERROR_CONV_FAILED);
            model.addAttribute(ERROR_INFO, "Could not find this conversion, try again.");
        } else if (ex.getMessage().toUpperCase().contains("CONVERT")) {
            model.addAttribute(ERROR_TYPE, ERROR_CONV_FAILED);
            model.addAttribute(ERROR_INFO, "Cannot convert from the specified currency to the same currency.");
        } else {
            model.addAttribute(ERROR_TYPE, ERROR_GENERIC_TYPE);
            model.addAttribute(ERROR_INFO, ERROR_GENERIC_INFO);
        }

        return ERROR_PAGE_URL;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception, Model model) {
        model.addAttribute(ERROR_TYPE, ERROR_GENERIC_TYPE);
        model.addAttribute(ERROR_INFO, ERROR_GENERIC_INFO);
        return ERROR_PAGE_URL;
    }

    @GetMapping("/" + ERROR_PATH)
    public String handleHttpError(HttpServletRequest request, HttpServletResponse response, Model model) {
        int statusCode = Integer.parseInt(
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString()
        );

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute(ERROR_TYPE, "Page not Found");
            model.addAttribute(ERROR_INFO, "Sorry, the page could not be found. Please let us know what you tried to do.");
            response.setStatus(statusCode);
        } else {
            model.addAttribute(ERROR_TYPE, ERROR_GENERIC_TYPE);
            model.addAttribute(ERROR_INFO, ERROR_GENERIC_INFO);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return ERROR_PAGE_URL;
    }

    @Override
    public String getErrorPath() {
        return "/" + ERROR_PATH;
    }
}
