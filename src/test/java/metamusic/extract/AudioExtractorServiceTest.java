package metamusic.extract;

import org.jaudiotagger.tag.FieldKey;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AudioExtractorServiceTest {
    @Test
    public void testExtractMetadataContainsPath() {
        List<Path> sources = List.of(Path.of("src/test/resources"));
        JSONArray actual = new AudioExtractorService().extractMetadata(sources);

        assertThat(actual).isNotNull();
        JSONObject first = (JSONObject)actual.get(0);
        assertThat(first).isNotNull();
        assertThat(first.get("path").toString()).contains("src/test/resources");
        JSONObject metadata = (JSONObject)first.get("metadata");
        assertThat(metadata).isNotNull();
        assertThat(metadata.get(FieldKey.ARTIST.toString())).isEqualTo("Vinicius Honorio");
        assertThat(metadata.get(FieldKey.BPM.toString())).isEqualTo("126");
        assertThat(metadata.get(FieldKey.TITLE.toString())).isEqualTo("5A - Walking Shadow (Original Mix)");
    }
}