package metamusic.extract;

import org.json.JSONArray;

import java.nio.file.Path;
import java.util.List;

public interface ExtractorService {

    JSONArray extractMetadata(List<Path> sources);
}
