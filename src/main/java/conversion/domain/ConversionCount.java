package conversion.domain;

import javax.persistence.*;

@Entity
public class ConversionCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "conversion_id", nullable = false, referencedColumnName = "id")
    private ConversionRate convRate;

    private Long count;

    public ConversionCount() { }

    public ConversionCount(ConversionRate convRate) {
        this.convRate = convRate;
        this.count = Long.valueOf(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public ConversionRate getConvRate() {
        return convRate;
    }

    public void setConvRate(ConversionRate convRate) { this.convRate = convRate; }

    public String toString() {
        return String.format("ConversionCount[Primkey: %d, ConversionRate: %s, Count: %d]", id, convRate, count);
    }
}
