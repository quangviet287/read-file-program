package service;

import exception.TypeNotSupportedException;
import model.CSVFile;
import model.Company;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVFileServiceImplTest {

    @Mock
    CSVFile csvFile;

    @Test(expected = TypeNotSupportedException.class)
    public void shouldThrowTypeNotSupportedExceptionWhenTypeInvalid() throws TypeNotSupportedException {
        //CSVFileServiceImpl csvFileService = new CSVFileServiceImpl(prepareFileTypeInvalid());
    }

    /*@Test
    public void shouldReturnCompanyByCountrySuccess() throws IOException {
        Mockito.when(csvFile.getDataContent(Mockito.any())).thenReturn(prepareDataContent());
        csvFile.getNameOfCompanyByCountry(prepareFileTypeValid());
    }*/

    private Path prepareFileTypeInvalid(){
        return Paths.get("F:\\project\\data\\a.abc");
    }

    private Path prepareFileTypeValid(){
        return Paths.get("F:\\project\\data\\a.csv");
    }

    private List<Company> prepareDataContent(){

        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("1","ELCA VN","1998",200000,"VN",null));
        companyList.add(new Company("1","ELCA VN","1998",200111,"VN",null));
        companyList.add(new Company("1","ELCA VN","1998",123123,"VN",null));
        companyList.add(new Company("1","ELCA VN","1998",123456,"VN",null));
        companyList.add(new Company("1","ELCA VN","1998",321456,"VN",null));
        companyList.add(new Company("1","ELCA VN","1998",145678,"VN",null));
        companyList.add(new Company("1","ELCA VN","1998",212354,"VN",null));
        companyList.add(new Company("1","ELCA VN","1998",124567,"CH",null));
        return companyList;
    }
}