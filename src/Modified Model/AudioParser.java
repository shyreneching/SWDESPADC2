package Model;
import java.awt.image.BufferedImage;
import java.io.*;
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

    /**
     * @param args
     */
    public static void main(String[] args) {

        String fileLocation = "C:/Users/Shyrene/Downloads/Music/Taeyeon - I’m the Greatest.mp3";

        try {

            /*Mp3File song = new Mp3File(fileLocation);
            if (song.hasId3v2Tag()){
                ID3v2 id3v2tag = song.getId3v2Tag();
                byte[] imageData = id3v2tag.getAlbumImage();
                //converting the bytes to an image
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
            }*/

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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

    }
}
