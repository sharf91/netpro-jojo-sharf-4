package currencyconverter.conv;


import currencyconverter.application.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Scope("session")
public class convController
{
    static final String DEFAULT_PAGE_URL = "/";
    static final String CONVERSION_PAGE_URL = "conversion";
    static final String ADMIN_PAGE_URL = "admin";

    static final String DO_CONVERSION_URL = "do-conversion";

    private static final String CONVERSION_FORM_OBJ_NAME = "doConversion";

    @Autowired
    private ConversionService service;

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView()
    {
        return "redirect:" + CONVERSION_PAGE_URL;
    }

    @GetMapping("/" + CONVERSION_PAGE_URL)
    public String showCurrencyConversionView(ConvForm createConvForm)
    {
        return CONVERSION_PAGE_URL;
    }

    /**
     * When a do conversion form has been submitted.
     *
     * @param convForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/" + DO_CONVERSION_URL)
    public String doConversion(@Valid ConvForm convForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) {
            return CONVERSION_PAGE_URL;
        }

        return DO_CONVERSION_URL;
    }

}
