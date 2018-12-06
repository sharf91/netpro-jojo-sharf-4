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
    private double rate;

    protected ConversionRate() {
        // Required by JPA
    }

    public ConversionRate(String fromCurr, String toCurr) {
        this.fromCurr = fromCurr;
        this.toCurr = toCurr;
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
    public double getRate() {
        return rate;
    }

    public String toString() {
        return String.format(
                "ConversionRate[id=%d, fromCurr='%s', toCurr='%s', rate='%d']",
                id, fromCurr, toCurr, rate);
    }
}
