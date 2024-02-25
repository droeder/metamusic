package metamusic.config;

import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public List<Path> sourcePaths = new ArrayList<Path>(){{
        add(Path.of(URI.create("/home/droeder/dev/metamusic/data")));
    }};


}
