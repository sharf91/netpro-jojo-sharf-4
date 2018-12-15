package conversion.application;

import conversion.domain.ConversionCount;
import conversion.domain.ConversionRate;

import conversion.domain.IllegalConversionException;
import conversion.repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import conversion.repository.ConversionRepository;

import java.util.List;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class ConverterService {

    @Autowired
    private ConversionRepository conversionRepo;

    @Autowired
    private CountRepository countRepo;

    @Transactional
    public ConversionRate getConversionRate(String fromCurrencyName, String toCurrencyName) throws IllegalConversionException {
        ConversionRate convRate = conversionRepo.findByFromCurrAndToCurr(fromCurrencyName, toCurrencyName);
        if (convRate == null) {
            if(fromCurrencyName.equalsIgnoreCase(toCurrencyName))
                throw new IllegalConversionException("Tried to convert between the same currency.");
            else
                throw new IllegalConversionException("Conversion could not be found.");
        }

        ConversionCount convCount = countRepo.findByConvRate_Id(convRate.getId());
        if (convCount == null)
            convCount = new ConversionCount(convRate);

        convCount.setCount(convCount.getCount() + 1);
        countRepo.save(convCount);
        return convRate;
    }

    @Transactional
    public void saveConversionRate(String fromCurr, String toCurr, Double rate) throws IllegalConversionException {
        ConversionRate convRate = conversionRepo.findByFromCurrAndToCurr(fromCurr, toCurr);

        if (convRate == null)
            convRate = new ConversionRate(fromCurr, toCurr, rate);
        else
            convRate.setRate(rate);

        conversionRepo.save(convRate);
    }

    @Transactional
    public List<ConversionCount> getConversionsAndCount() {
        return countRepo.findAll();
    }
}
