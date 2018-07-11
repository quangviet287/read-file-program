package service;

import model.Company;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFileService {


    private static final String CH_COUNTRY = "CH";
    private static final Logger LOGGER = Logger.getLogger(ReadFileService.class);

    public void showTotalCapitalByCountry(List<Company> companyList){
        LOGGER.info("Start get total capital by country: ");
            int total = getTotalCapitalByCountryIsCH(companyList);
            LOGGER.info("Total capital of “CH” headquarters is: " + total);
            LOGGER.info("The list is sorted descending: ");
            companyList.stream()
                            .sorted(Comparator.comparing(Company::getCapital)
                            .reversed())
                            .forEach(e-> LOGGER.info(e));
    }


    public void showNameOfCompanyByCountry(List<Company> companyList){
        LOGGER.info("Start get name of company by country: ");
        List<String> listNames = getListNameOfCompanyByCountry(companyList);
        LOGGER.info("List Name of company of “CH” headquarters is: " + listNames);
    }

    public int getTotalCapitalByCountryIsCH(List<Company> companyList){
        return companyList.stream().filter(c-> CH_COUNTRY.equals(c.getCountry())).mapToInt(Company::getCapital).sum();
    }

    public List<String> getListNameOfCompanyByCountry(List<Company> companyList){
        return companyList.stream()
                .filter(c->CH_COUNTRY.equals(c.getCountry()))
                .map(c->c.getName())
                .collect(Collectors.toList());
    }
}
