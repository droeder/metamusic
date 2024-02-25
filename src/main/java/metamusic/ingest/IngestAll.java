package metamusic.ingest;

import metamusic.extract.ExtractorService;
import metamusic.index.IndexService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IngestAll {

    private final IndexService indexService;
    private final ExtractorService extractorService;
    private final List<Path> source = new ArrayList<>();


    public IngestAll(ArrayList<Path> sourcePaths, IndexService indexService, ExtractorService extractorService) {
        this.indexService = indexService;
        this.extractorService = extractorService;
        if( source != null ){
         this.source.addAll(sourcePaths);
        }
    }

    public void execute() {
//        read all
        extractorService.extractMetadata();
//        ingest
        indexService.index();
    }
}
