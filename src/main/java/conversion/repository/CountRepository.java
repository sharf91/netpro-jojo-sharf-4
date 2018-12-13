package conversion.repository;

import conversion.domain.ConversionCount;
import conversion.domain.ConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CountRepository extends JpaRepository<ConversionCount, Long> {
    ConversionCount findByConvRate_Id(Long id);
}
