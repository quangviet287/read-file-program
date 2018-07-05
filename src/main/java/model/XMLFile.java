package model;

import java.nio.file.Path;
import java.util.List;

public class XMLFile extends FileData {

    private Path file;

    public XMLFile(Path file) {
        this.file = file;
    }

    @Override
    public List<?> getDataContent(Path file) {
        return null;
    }

    @Override
    public void getTotalCapitalByCountry(Path file) {

    }

    @Override
    public void getNameOfCompanyByCountry(Path file) {

    }
}
