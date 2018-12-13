package conversion.domain;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class ConversionRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String fromCurr;
    private String toCurr;
    private Double rate;

    protected ConversionRate() {
        // Required by JPA
    }

    public ConversionRate(String fromCurr, String toCurr) throws IllegalConversionException{
        if (fromCurr.equals(toCurr))
            throw new IllegalConversionException("Cannot convert from " + fromCurr + " to " + toCurr);
        this.fromCurr = fromCurr;
        this.toCurr = toCurr;
    }

    public ConversionRate(String fromCurr, String toCurr, Double rate) throws IllegalConversionException {
        this(fromCurr, toCurr);
        this.rate = rate;
    }

    public Long getId() {
        return id; }

    public String getFromCurr() {
        return fromCurr;
    }

    public String getToCurr() {
        return toCurr;
    }

    @PositiveOrZero
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getConvertedAmount(Double amount) {
        return amount*rate;
    }

    public String toString() {
        return String.format(
                "ConversionRate[id=%d, fromCurr='%s', toCurr='%s', rate='%fW']",
                id, fromCurr, toCurr, rate);
    }
}
