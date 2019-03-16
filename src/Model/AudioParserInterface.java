package Model;

import java.io.File;

public interface AudioParserInterface {
    public SongInterface getSongDetails(String location);

    // Returns the album art of the song in File Format
    // Need to pass song with album art
    // Returns null if no album art
    public File getSongImage(SongInterface s);

    public Song setSongImage(Song s, File image);

    public Song editSongDetails(Song original, Song changed);
}
