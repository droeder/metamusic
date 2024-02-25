package metamusic;

import metamusic.service.MetaDataExtractorService;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        // Provide the path to your music file
        String filePath = "/home/droeder/dev/metamusic/data/Vinicius Honorio Walking Shadow(Original Mix).mp3";

        // Read metadata
        AudioFile audioFile = AudioFileIO.read(new File(filePath));
        MetaDataExtractorService extractor = new MetaDataExtractorService();
        extractor.extractMetadata(audioFile);
    }
//    use cases usecases.ingest

}
