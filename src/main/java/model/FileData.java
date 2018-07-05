package model;

import java.nio.file.Path;
import java.util.List;

public abstract class FileData {

    public abstract List<?> getDataContent(Path file);

    public abstract void getTotalCapitalByCountry(Path file);

    public abstract void getNameOfCompanyByCountry(Path file);
}