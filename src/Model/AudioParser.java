package Model;

import Mp3agic.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.SQLOutput;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;

public class AudioParser implements AudioParserInterface{

    /*Sets
    * song name
    * song artist
    * song album
    * song genre
    * song release date
    * song tracknumber
    * song duration
    * song location
    * song filename
    * */
    public SongInterface getSongDetails(String location){
        String fileLocation = location;
        Song s = new Song();

        try {
            InputStream input = new FileInputStream(new File(fileLocation));
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();

            /*// List all metadata
            String[] metadataNames = metadata.names();

            for(String name : metadataNames){
                System.out.println(name + ": " + metadata.get(name));
            }*/

            // Retrieve the necessary info from metadata
            // Names - title, xmpDM:artist etc. - mentioned below may differ based

            s.setName(metadata.get("title"));
            s.setArtist(metadata.get("xmpDM:artist"));
            s.setAlbum(metadata.get("xmpDM:album"));
            s.setGenre(metadata.get("xmpDM:genre"));
            if (!(metadata.get("xmpDM:releaseDate").equals("")) && metadata.get("xmpDM:releaseDate") != null)
                s.setYear(Integer.parseInt(metadata.get("xmpDM:releaseDate")));
            if (!(metadata.get("xmpDM:trackNumber").equals("")) && metadata.get("xmpDM:trackNumber") != null)
            s.setTrackNumber(Integer.parseInt(metadata.get("xmpDM:trackNumber")));
            Double d = Double.parseDouble(metadata.get("xmpDM:duration"));
            d =  d/1000;
            s.setLength(d.intValue());
            s.setFilelocation(location);
            s.setFilename("src/Music/" + s.getArtist() + "-" + s.getName()+ ".mp3");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        return s;
    }

    // Returns the album art of the song in File Format
    // Need to pass song with album art
    // Returns null if no album art
    public File getSongImage(SongInterface s) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File music = new Mp3File(s.getFilelocation());
        File outputfile = new File(s.getArtist() + "_" + s.getAlbum() + ".jpg");
        if (music.hasId3v2Tag()){
            ID3v2 id3v2tag = music.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            if (imageData != null){
                //converting the bytes to an image
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                ImageIO.write(img, "jpg", outputfile);
                return outputfile;
            }
            else{
                //set default picture
            }
        }
         return null;
    }

    public SongInterface setSongImage(SongInterface s, File image) throws InvalidDataException, IOException, UnsupportedTagException, NotSupportedException {
        Mp3File mp3file = new Mp3File(s.getFilelocation());
        ID3v2 id3v2Tag;
        id3v2Tag = new ID3v24Tag();
        mp3file.setId3v2Tag(id3v2Tag);
        byte[] imageData = Files.readAllBytes(image.toPath());
        id3v2Tag.setAlbumImage(imageData, ".jpg");
        id3v2Tag.setTrack(s.getTrackNumber() + "");
        id3v2Tag.setArtist(s.getArtist());
        id3v2Tag.setTitle(s.getName());
        id3v2Tag.setAlbum(s.getAlbum());
        id3v2Tag.setYear(s.getYear() + "");
        id3v2Tag.setGenre(ID3v1Genres.matchGenreDescription(s.getGenre()));
        /*id3v2Tag.setComment("Some comment");
        id3v2Tag.setLyrics("Some lyrics");
        id3v2Tag.setComposer("The Composer");
        id3v2Tag.setPublisher("A Publisher");
        id3v2Tag.setOriginalArtist("Another Artist");*/
        id3v2Tag.setAlbumArtist(s.getArtist());
        //temporarily sets the file name to "temp.mp3"
        mp3file.save("temp.mp3");
        //gets the old file and deletes it
        File file = new File(s.getFilename());
        if (file != null)
            file.delete();
        //renames the temp file to the actual file
        File tempfile =new File("src/Music/temp.mp3");
        File newfile =new File(s.getFilename());
        s.setSongfile(tempfile);
        tempfile.renameTo(newfile);
        return s;
    }

    public SongInterface editSongDetails(SongInterface original, SongInterface changed) throws InvalidDataException, IOException, UnsupportedTagException, NotSupportedException {
        Mp3File mp3file = new Mp3File(original.getFilelocation());
        ID3v2 id3v2Tag;
        id3v2Tag = new ID3v24Tag();
        mp3file.setId3v2Tag(id3v2Tag);
        byte[] imageData = Files.readAllBytes(getSongImage(changed).toPath());

        if (mp3file.hasId3v1Tag()) {
            mp3file.removeId3v1Tag();
        }
        if (mp3file.hasId3v2Tag()) {
            mp3file.removeId3v2Tag();
        }
        if (mp3file.hasCustomTag()) {
            mp3file.removeCustomTag();
        }

        id3v2Tag.setAlbumImage(imageData, ".jpg");
        id3v2Tag.setTrack(changed.getTrackNumber() + "");
        id3v2Tag.setArtist(changed.getArtist());
        id3v2Tag.setTitle(changed.getName());
        id3v2Tag.setAlbum(changed.getAlbum());
        id3v2Tag.setYear(changed.getYear() + "");
        id3v2Tag.setGenre(ID3v1Genres.matchGenreDescription(changed.getGenre()));
        /*id3v2Tag.setComment("Some comment");
        id3v2Tag.setLyrics("Some lyrics");
        id3v2Tag.setComposer("The Composer");
        id3v2Tag.setPublisher("A Publisher");
        id3v2Tag.setOriginalArtist("Another Artist");*/
        id3v2Tag.setAlbumArtist(changed.getArtist());
        //temporarily sets the file name to "temp.mp3"
        mp3file.save("temp.mp3");

        //takes the file again, place it in a song file and delete the mp3 file
        File file = new File("src/Music/temp.mp3");
        SongInterface s = getSongDetails("src/Music/temp.mp3");
        s.setSongfile(file);
        s.setUser(changed.getUser());
        file.delete();
        return s;
    }

}
