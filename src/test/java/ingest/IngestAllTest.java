package ingest;

import metamusic.extract.AudioExtractorService;
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
        this.sourcePaths = new ArrayList<>(){{
            add(Path.of(("src/test/resources")));
        }};
    }

    @Test
    public void testIngestion(){
        ExtractorService extractorMock = mock(AudioExtractorService.class);
        IndexService indexerMock = mock(IndexService.class);
        var subject = new IngestAll(this.sourcePaths, indexerMock, extractorMock);
        subject.execute();
        verify(extractorMock, times(1)).extractMetadata(this.sourcePaths);
        verify(indexerMock, times(1)).index();
    }
}