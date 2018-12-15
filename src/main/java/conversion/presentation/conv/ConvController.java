package conversion.presentation.conv;


import conversion.application.ConverterService;
import conversion.domain.ConversionCount;
import conversion.domain.ConversionRate;
import conversion.domain.IllegalConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("session")
public class ConvController
{
    static final String DEFAULT_PAGE_URL = "/";
    static final String CONVERSION_PAGE_URL = "conversion";
    static final String DO_CONVERSION_URL = "do-conversion";

    static final String ADMIN_PAGE_URL = "admin";
    static final String CHANGE_RATES_URL = ADMIN_PAGE_URL + "/change-rates";

    private static final String CONVERSION_FORM_OBJ_NAME = "conversionRate";

    private static final String CHANGERATE_FORM_OBJ_NAME = "";

    @Autowired
    private ConverterService service;

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


    @GetMapping("/" + ADMIN_PAGE_URL)
    public String showAdminChangeCurrencyView()
    {
        return "redirect:" + CHANGE_RATES_URL;
    }

    @GetMapping("/" + CHANGE_RATES_URL)
    public String showAdminView(AdminConvForm adminForm, Model model) {
        List<ConversionCount> conversions = (ArrayList<ConversionCount>) service.getConversionsAndCount();
        model.addAttribute("conversions", conversions);
        int convs = 0;
        for (ConversionCount conv : conversions)
            convs += conv.getCount();

        model.addAttribute("convs", convs);
        return CHANGE_RATES_URL;
    }

    /**
     * When a change rate form has been submitted by admin.
     *
     * @param adminForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/" + CHANGE_RATES_URL)
    public String changeRate(@Valid AdminConvForm adminForm, BindingResult bindingResult, Model model)
            throws IllegalConversionException {
        if (bindingResult.hasErrors()) {
            return showAdminView(adminForm,model);
        }

        service.saveConversionRate(adminForm.getFromCurr(), adminForm.getToCurr(), adminForm.getRate());
        System.out.println("Saved to db.");
        return showAdminView(adminForm,model);
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
            throws IllegalConversionException {
        if (bindingResult.hasErrors()) {
            return CONVERSION_PAGE_URL;
        }

        ConversionRate convRate = service.getConversionRate(convForm.getFromCurrency(), convForm.getToCurrency());
        model.addAttribute(CONVERSION_FORM_OBJ_NAME, convRate);
        return CONVERSION_PAGE_URL;
    }
}
