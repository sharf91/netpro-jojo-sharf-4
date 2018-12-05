package presentation.conv;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ConvForm {
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

    @Override
    public String toString()
    {
        return amount.toString(); //TODO this may be silly, Leif uses Util call
    }
}
