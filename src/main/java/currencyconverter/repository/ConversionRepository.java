package currencyconverter.repository;

import currencyconverter.domain.ConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ConversionRepository extends JpaRepository<ConversionRate, Long>
{
    ConversionRate getConversionRate(String fromCurrency, String toCurrency);
}
