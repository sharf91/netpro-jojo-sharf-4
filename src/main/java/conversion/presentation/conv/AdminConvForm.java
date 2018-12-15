package conversion.presentation.conv;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class AdminConvForm {

    @NotBlank(message = "Please specify currency")
    private String fromCurr;

    @NotBlank(message = "Please specify currency")
    private String toCurr;

    @NotNull(message = "Please specify amount")
    @PositiveOrZero(message = "Amount must be greater than or equal to zero")
    private Double rate;

    public String getToCurr() {
        return toCurr;
    }

    public String getFromCurr() {
        return fromCurr;
    }

    public Double getRate() {
        return rate;
    }

    public void setFromCurr(String fromCurr) {
        this.fromCurr = fromCurr.toUpperCase();
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setToCurr(String toCurr) {
        this.toCurr = toCurr.toUpperCase();
    }

    @Override
    public String toString()
    {
        return rate.toString();
    }
}
