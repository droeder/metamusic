package metamusic.service;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class MetaDataExtractorService {

    public void extractMetadata(AudioFile audioFile) {
        Tag tag = audioFile.getTag();

        // Extract metadata
        String title = tag.getFirst(FieldKey.TITLE);
        String artist = tag.getFirst(FieldKey.ARTIST);
        String album = tag.getFirst(FieldKey.ALBUM);

        // Print metadata
        System.out.println("Title: " + title);
        System.out.println("Artist: " + artist);
        System.out.println("Album: " + album);
    }
}
