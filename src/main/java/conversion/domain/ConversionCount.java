package conversion.domain;

import javax.persistence.*;

@Entity
public class ConversionCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "conversionRate")
    private ConversionRate convRate;

    private Long count;

    public ConversionCount() {

    }

    public ConversionCount(ConversionRate convRate) {
        this.convRate = convRate;
    }

    public Long getCount() {
        return count;
    }

    public Long getId() {
        return id;
    }

    public void setCount(Long count) {
        this.count = count;
    }


}
