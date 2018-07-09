package service;

import model.Company;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CSVFileServiceTest {


    @InjectMocks
    CSVFileService csvFileService;

    @Test
    public void shouldReturnTotalCapitalByCountry(){
        List<Company> companyList = prepareDataContent();
        long expectResult = 1;
        long actualResult = csvFileService.getTotalCapitalByCountryIsCH(companyList);
        Assert.assertEquals(expectResult,actualResult);
    }

    @Test
    public void shouldReturnListNameOfCompanyByCountry(){
        List<Company> companyList = prepareDataContent();
        List<String> listNameActual = csvFileService.getListNameOfCompanyByCountry(companyList);
        List<String> listNameExpect = listNameOfCompanyByCountry();
        Assert.assertEquals(listNameExpect, listNameActual);
    }

    private List<Company> prepareDataContent(){

        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("1","ELCA VN","1998",200000,"VN",null));
        companyList.add(new Company("1","Smart Dev","1998",200111,"VN",null));
        companyList.add(new Company("1","Fpt","1998",123123,"US",null));
        companyList.add(new Company("1","Evolable","1998",123456,"VN",null));
        companyList.add(new Company("1","Axon","1998",321456,"US",null));
        companyList.add(new Company("1","Simple","1998",145678,"VN",null));
        companyList.add(new Company("1","Gameloft","1998",212354,"VN",null));
        companyList.add(new Company("1","Framgia","1998",124567,"CH",null));
        return companyList;
    }

    private List<Company> listCompanyDescendingByCapital(){

        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("1","ELCA VN","1998",321456,"US",null));
        companyList.add(new Company("1","Smart Dev","1998",212354,"VN",null));
        companyList.add(new Company("1","Fpt","1998",200111,"VN",null));
        companyList.add(new Company("1","Evolable","1998",200000,"VN",null));
        companyList.add(new Company("1","Axon","1998",145678,"VN",null));
        companyList.add(new Company("1","Simple","1998",124567,"CH",null));
        companyList.add(new Company("1","Gameloft","1998",123456,"VN",null));
        companyList.add(new Company("1","Framgia","1998",123123,"US",null));
        return companyList;
    }

    private List<String> listNameOfCompanyByCountry(){
        return new ArrayList<>(Collections.singleton("Framgia"));
    }
}