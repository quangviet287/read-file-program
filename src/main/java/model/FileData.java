package model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileData {

    List<?> getDataContent(Path file) throws IOException;

}
