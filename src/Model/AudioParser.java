package Model;

import Mp3agic.*;

import java.awt.image.BufferedImage;
import java.io.*;
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

public class AudioParser {

    public Song getSongDetails(String location){
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
            s.setYear(Integer.parseInt(metadata.get("xmpDM:releaseDate")));
            s.setTrackNumber(Integer.parseInt(metadata.get("xmpDM:trackNumber")));
            Double d = Double.parseDouble(metadata.get("xmpDM:duration"));
            d =  d/1000;
            s.setLength(d.intValue());



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
    public File getSongImage(Song s) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File music = new Mp3File(s.getFilelocation());
        File outputfile = new File(s.getName() + ".jpg");
        if (music.hasId3v2Tag()){
            ID3v2 id3v2tag = music.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            //converting the bytes to an image
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
            ImageIO.write(img, "jpg", outputfile);
            return outputfile;
        }
         return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        String fileLocation = "C:/Users/Shyrene/Downloads/Music/Taeyeon - I’m the Greatest.mp3";

        try {

            InputStream input = new FileInputStream(new File(fileLocation));
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();

            // List all metadata
            String[] metadataNames = metadata.names();

            for(String name : metadataNames){
                System.out.println(name + ": " + metadata.get(name));
            }

            // Retrieve the necessary info from metadata
            // Names - title, xmpDM:artist etc. - mentioned below may differ based
            System.out.println("----------------------------------------------");
            System.out.println("Title: " + metadata.get("title"));
            System.out.println("Artists: " + metadata.get("xmpDM:artist"));
            System.out.println("Composer : "+metadata.get("xmpDM:composer"));
            System.out.println("Genre : "+metadata.get("xmpDM:genre"));
            System.out.println("Album : "+metadata.get("xmpDM:album"));
            System.out.println("Track Number : "+metadata.get("xmpDM:trackNumber"));
            double i = Double.parseDouble(metadata.get("xmpDM:duration"));
            i = i/1000;
            //int k  = Integer.parseInt(k);
            System.out.println("Duration : "+ (int)i);


            AudioParser ap = new AudioParser();
            Song s = ap.getSongDetails("C:/Users/Shyrene/Downloads/Music/Taeyeon - I’m the Greatest.mp3");
            System.out.println(s.getLength());

            MusicPlayerDB db = new MusicPlayerDB();
            SongService ss = new SongService(db);
            //s.setSongid("S01");
            //s.setFilelocation("C:/Users/Shyrene/Downloads/Music/Taeyeon - I’m the Greatest.mp3");
            //s.setSongfile(new File(fileLocation));
            //ss.add(s);
            Song song =  ss.getSong("S01", "A01");
            File f = song.getSongfile();
            System.out.println(f.getName());

            Mp3File music = new Mp3File(song.getFilelocation());
            if (music.hasId3v2Tag()){
                ID3v2 id3v2tag = music.getId3v2Tag();
                byte[] imageData = id3v2tag.getAlbumImage();
                //converting the bytes to an image
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                File outputfile = new File("image.jpg");
                ImageIO.write(img, "jpg", outputfile);
            }



            Mp3File mp3file = new Mp3File(song.getFilelocation());
            if (mp3file.hasId3v1Tag()) {
                mp3file.removeId3v1Tag();
            }
            if (mp3file.hasId3v2Tag()) {
                mp3file.removeId3v2Tag();
            }
            if (mp3file.hasCustomTag()) {
                mp3file.removeCustomTag();
            }
            ID3v2 id3v2Tag;
            id3v2Tag = new ID3v24Tag();
            mp3file.setId3v2Tag(id3v2Tag);

            id3v2Tag.setTrack("5");
            id3v2Tag.setArtist("Taeyeon");
            id3v2Tag.setTitle("The Title");
            id3v2Tag.setAlbum("The Album");
            id3v2Tag.setYear("2001");
            id3v2Tag.setGenre(12);
            id3v2Tag.setComment("Some comment");
            id3v2Tag.setLyrics("Some lyrics");
            id3v2Tag.setComposer("The Composer");
            id3v2Tag.setPublisher("A Publisher");
            id3v2Tag.setOriginalArtist("Another Artist");
            id3v2Tag.setAlbumArtist("An Artist");
            id3v2Tag.setCopyright("Copyright");
            id3v2Tag.setUrl("http://foobar");
            id3v2Tag.setEncoder("The Encoder");
            ID3v2 id3v2tag = music.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            id3v2Tag.setAlbumImage(imageData, ".jpg");
            mp3file.save("MyMp3File.mp3");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        }
    }
}
