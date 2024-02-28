package metamusic.extract;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioExtractorService implements ExtractorService {

    private static final Logger logger =  Logger.getLogger(AudioExtractorService.class.getName());
//TODO: maybe use streams for processing all files
    @Override
    public JSONArray extractMetadata(List<Path> sources) {
        JSONArray result = new JSONArray();
        for (File source : sources.stream().map(Path::toFile).toList()){
            if (source.isDirectory() && source.canRead()){
                processDirectory(source, result);
            }
        }
        return result;
    }

    private void processDirectory(File source, JSONArray result) {
        if (source.isDirectory() && source.canRead()){
            File[] children = source.listFiles();
            if (children != null){
                for (File child : children){
                    if (child.isDirectory()){
                        processDirectory(child, result);
                    } else {
                        processFile(child, result);
                    }
                }
            }
        }
    }

    private void processFile(File child, JSONArray result) {
        if (!child.exists()){
            logger.log(Level.WARNING, "File does not exist: " + child.getAbsolutePath());
        }
        if (!child.getName().endsWith(".mp3")) {
            logger.log(Level.WARNING, "File ignored: " + child.getAbsolutePath());
        }
        AudioFile audioFile = getAudioFile(child);
        if (audioFile == null){
            logger.log(Level.WARNING, "Error processing file: " + child.getAbsolutePath());
        }
        JSONObject fileJson = new JSONObject();
        fileJson.put("path", child.getAbsolutePath());
        fileJson.put("metadata", getMetadata(audioFile));
        result.put(fileJson);
    }

    private static JSONObject getMetadata(AudioFile audioFile) {
        JSONObject jsonMetadata = new JSONObject();
        Tag tag = audioFile.getTag();
        jsonMetadata.put(FieldKey.TITLE.toString(), tag.getFirst(FieldKey.TITLE));
        jsonMetadata.put(FieldKey.ARTIST.toString(), tag.getFirst(FieldKey.ARTIST));
        jsonMetadata.put(FieldKey.ALBUM.toString(), tag.getFirst(FieldKey.ALBUM));
        jsonMetadata.put(FieldKey.YEAR.toString(), tag.getFirst(FieldKey.YEAR));
        jsonMetadata.put(FieldKey.GENRE.toString(), tag.getFirst(FieldKey.GENRE));
        jsonMetadata.put(FieldKey.COMMENT.toString(), tag.getFirst(FieldKey.COMMENT));
        jsonMetadata.put(FieldKey.TRACK.toString(), tag.getFirst(FieldKey.TRACK));
        jsonMetadata.put(FieldKey.DISC_NO.toString(), tag.getFirst(FieldKey.DISC_NO));
        jsonMetadata.put(FieldKey.COMPOSER.toString(), tag.getFirst(FieldKey.COMPOSER));
        jsonMetadata.put(FieldKey.RECORD_LABEL.toString(), tag.getFirst(FieldKey.RECORD_LABEL));
        jsonMetadata.put(FieldKey.ORIGINAL_ARTIST.toString(), tag.getFirst(FieldKey.ORIGINAL_ARTIST));
        jsonMetadata.put(FieldKey.ALBUM_ARTIST.toString(), tag.getFirst(FieldKey.ALBUM_ARTIST));
        jsonMetadata.put(FieldKey.CONDUCTOR.toString(), tag.getFirst(FieldKey.CONDUCTOR));
        jsonMetadata.put(FieldKey.LYRICIST.toString(), tag.getFirst(FieldKey.LYRICIST));
        jsonMetadata.put(FieldKey.LANGUAGE.toString(), tag.getFirst(FieldKey.LANGUAGE));
        jsonMetadata.put(FieldKey.KEY.toString(), tag.getFirst(FieldKey.KEY));
        jsonMetadata.put(FieldKey.BPM.toString(), tag.getFirst(FieldKey.BPM));
        jsonMetadata.put(FieldKey.ISRC.toString(), tag.getFirst(FieldKey.ISRC));
        jsonMetadata.put(FieldKey.BARCODE.toString(), tag.getFirst(FieldKey.BARCODE));
        jsonMetadata.put(FieldKey.CATALOG_NO.toString(), tag.getFirst(FieldKey.CATALOG_NO));
        jsonMetadata.put(FieldKey.MEDIA.toString(), tag.getFirst(FieldKey.MEDIA));
        jsonMetadata.put(FieldKey.ENCODER.toString(), tag.getFirst(FieldKey.ENCODER));
        jsonMetadata.put(FieldKey.MOOD.toString(), tag.getFirst(FieldKey.MOOD));
        jsonMetadata.put(FieldKey.RATING.toString(), tag.getFirst(FieldKey.RATING));
        jsonMetadata.put(FieldKey.LYRICS.toString(), tag.getFirst(FieldKey.LYRICS));
        jsonMetadata.put(FieldKey.ALBUM_SORT.toString(), tag.getFirst(FieldKey.ALBUM_SORT));
        jsonMetadata.put(FieldKey.ALBUM_ARTIST_SORT.toString(), tag.getFirst(FieldKey.ALBUM_ARTIST_SORT));
        jsonMetadata.put(FieldKey.TITLE_SORT.toString(), tag.getFirst(FieldKey.TITLE_SORT));

        return jsonMetadata;
    }

    private AudioFile getAudioFile(File child) {
        try {
            return AudioFileIO.read(child);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error processing file: " + child.getAbsolutePath() + " " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}