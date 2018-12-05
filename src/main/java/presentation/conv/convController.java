package presentation.conv;


import application.ConversionService;
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

    static final String CONVERSION_URL = "do-conversion";

    private static final String CONVERSION_FORM_OBJ_NAME = "doConversion";

    @Autowired
    private ConversionService service;

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView()
    {
        return "redirect:" + DEFAULT_PAGE_URL;
    }

    @GetMapping("/" + CONVERSION_PAGE_URL)
    public String showAccountSelectionView(ConvForm createConvForm)
    {
        return CONVERSION_PAGE_URL;
    }


    @PostMapping("/" + CONVERSION_URL)
    public String doConversion(@Valid ConvForm convForm, BindingResult bindingResult, Model model)
    {

        model.addAttribute(CONVERSION_FORM_OBJ_NAME, convForm);
        return CONVERSION_PAGE_URL;

    }

}
