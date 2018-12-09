package conversion.application;

import conversion.domain.ConversionRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import conversion.repository.ConversionRepository;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class ConverterService {

    @Autowired
    private ConversionRepository conversionRepo;

    /**
     * Takes an amount of a currency and returns the amount in another currency
     * @param fromCurrencyName
     * @param toCurrencyName
     * @return convertedAmount
     */
    public ConversionRate getConversionRate(String fromCurrencyName, String toCurrencyName)
    {
        return conversionRepo.findByFromCurrAndToCurr(fromCurrencyName, toCurrencyName);
    }

    @Transactional
    public void saveConversionRate(String fromCurr, String toCurr, Double rate) {
        ConversionRate convRate = conversionRepo.findByFromCurrAndToCurr(fromCurr, toCurr);

        if (convRate == null)
            convRate = new ConversionRate(fromCurr, toCurr, rate);
        else
            convRate.setRate(rate);

        conversionRepo.save(convRate);
    }
}
