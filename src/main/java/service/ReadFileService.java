package service;

import model.Company;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFileService {

    final static Logger logger = Logger.getLogger(ReadFileService.class);

    public void getTotalCapitalByCountry(List<Company> companyList){
        logger.info("Start get total capital by country: ");
            int total = getTotalCapitalByCountryIsCH(companyList);
            logger.info("Total capital of “CH” headquarters is: " + total);
            logger.info("The list is sorted descending: ");
            companyList.stream()
                            .sorted(Comparator.comparing(Company::getCapital)
                            .reversed())
                            .forEach(e-> logger.info(e));
    }


    public void getNameOfCompanyByCountry(List<Company> companyList){
        logger.info("Start get name of company by country: ");
        List<String> listNames = getListNameOfCompanyByCountry(companyList);
        logger.info("List Name of company of “CH” headquarters is: " + listNames);
    }

    public int getTotalCapitalByCountryIsCH(List<Company> companyList){
        return companyList.stream().filter(c->"CH".equals(c.getCountry())).mapToInt(Company::getCapital).sum();
    }

    public List<String> getListNameOfCompanyByCountry(List<Company> companyList){
        return companyList.stream()
                .filter(p->p.getCountry().equals("CH"))
                .map(c->c.getName())
                .collect(Collectors.toList());
    }
}
