package Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.lang.NullPointerException;
import java.util.TimeZone;


public class MusicPlayerDB {
    public final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public final static String URL = "jdbc:mysql://localhost:3306/";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "kitagawamizuki";
    public final static String DATABASE = "MusicPlayer";

    public Connection getConnection(){
        try {
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(
                    URL +
                            DATABASE + "?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone="
                            + TimeZone.getDefault().getID(),
                    USERNAME,
                    PASSWORD);
            System.out.println("[MYSQL} Connection successful!");
            return connection;
        } catch (SQLException e){
            System.out.println("[MSQL} Not able to connect");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e){
            System.out.println("[MSQL} Not able to connect");
            e.printStackTrace();
            return null;
        }
    }

    /*public static void main (String [] args) throws SQLException, NullPointerException {
        MusicPlayerDB db = new MusicPlayerDB();
        DotProperty service = new DotProperty(db);
        List<Location> location = service.getLocationService().getAll();

        System.out.println(location.size());

        for (int i = 0 ; i < location.size(); i++){
            System.out.println(location.get(i).getLocationName() + " " + location.get(i).getLocationID());
        }

        List<Facilities> f = service.getFacilitiesService().getAll();
        for (int i = 0 ; i < f.size(); i++){
            System.out.println(f.get(i).getFacilities() + " \n" + f.get(i).getDescription());
        }

        if(service.getAgentService().getAll() != null ) {
            List<Agent> a = service.getAgentService().getAll();
            if (a != null) {
                Agent agent = service.getAgentService().getAgent(service.getCurraccount().getAccountID());
                System.out.println(agent.getAccountID());
            }
        }


        /*Accounts a = new Accounts();
        a.setFirstname("Shyrene");
        a.setLastname("Ching");
        a.setEmail("shfbsfdga_ching@dlsu.edu.ph");
        a.setPassword("jhkn");
        System.out.println(service.register(a));

        }*/
}
