package conversion.domain;

import javax.persistence.*;

@Entity
public class ConversionCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
}
