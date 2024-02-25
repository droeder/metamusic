package ingest;

import metamusic.extract.ExtractorService;
import metamusic.index.IndexService;
import metamusic.ingest.IngestAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.mockito.Mockito.*;


class IngestAllTest {

    private ArrayList<Path> sourcePaths;

    @BeforeEach
    public void setUp() {
//        this.sourcePaths = new ArrayList<>(){{
//            add(Path.of(URI.create("/home/droeder/dev/metamusic/data")));
//        }};
        this.sourcePaths = new ArrayList<>();
    }

    @Test
    public void testIngestion(){
        ExtractorService extractorMock = mock(ExtractorService.class);
        IndexService indexerMock = mock(IndexService.class);
        var subject = new IngestAll(this.sourcePaths, indexerMock, extractorMock);
        subject.execute();
        verify(extractorMock, times(1)).extractMetadata();
        verify(indexerMock, times(1)).index();
    }
}