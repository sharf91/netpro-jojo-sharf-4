package conversion.presentation.conv;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ConvForm {

    @NotBlank(message = "Please specify currency")
    private String fromCurrency;

    @NotBlank(message = "Please specify currency")
    private String toCurrency;

    @NotNull(message = "Please specify amount")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount=amount;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency.toUpperCase();
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency)
    {
        this.fromCurrency = fromCurrency.toUpperCase();
    }

    @Override
    public String toString()
    {
        return amount.toString();
    }
}
