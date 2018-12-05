package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.ConversionRepository;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class ConversionService{

    @Autowired
    private ConversionRepository conversionRepo;

    /**
     * Takes an amount of a currency and returns the amount in another currency
     * @param fromCurrencyName
     * @param toCurrencyName
     * @param amount
     * @return convertedAmount
     */
    public double getConversionRate(String fromCurrencyName, String toCurrencyName, double amount)
    {
        return conversionRepo.getConversionRate(fromCurrencyName, toCurrencyName);
    }

}
