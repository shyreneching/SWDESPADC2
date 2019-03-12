package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AccountService {
    private MusicPlayerDB db;
    private PlaylistService ps;

    public AccountService(MusicPlayerDB db) {
        this.db = db;
    }

    //adds account to the database. Must be COMPLETE information
    public boolean add(Account a) throws SQLException {
        String query = "INSERT INTO accounts VALUE (?, ?, ?)";
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        try {
            statement.setString(1, a.getUsername());
            statement.setString(2, a.getPassword());
            statement.setString(3, a.getName());

            boolean added = statement.execute();

            return added;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }


        return false;
    }

    //gets all the accounts in the parameter in an arraylist
    public ArrayList<Account> getAll() throws SQLException {
        Connection connection = db.getConnection();
        ArrayList <Account> accounts = new ArrayList<>();

        String query ="SELECT * FROM accounts";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setName(rs.getString("name"));
                accounts.add(a);
            }
            return accounts;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }

    //gets one specific account with the username as a parameter
    public Account getAccount(String username) throws SQLException {
        Connection connection = db.getConnection();

        String query ="SELECT * FROM accounts WHERE username = " + username;
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setName(rs.getString("name"));

                return a;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }

    //pass the username of the account that you want to view the playlist
    public List<Playlist> getUserPlaylist(String username) throws SQLException {
        Connection connection = db.getConnection();
        List <Playlist> playlist = new ArrayList<>();

        String query ="SELECT * FROM playlist WHERE username = '" + username + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                playlist.add(ps.getPlaylist(rs.getString("idplaylist"), username));
            }
            return playlist;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return playlist;
    }

    //pass the username to delete an account
    public boolean delete(String username) throws SQLException {
        String query = "DELETE FROM accounts WHERE username = ?";
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, username);

            boolean deleted  = statement.execute();
            return deleted;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return false;
    }

    //pass the username of the account that wants to be change and an account class with COMPLETE information including the updates
    public boolean update(String username, Account a) throws SQLException {
        //UPDATE
        Connection connection = db.getConnection();

        String query = "UPDATE accounts SET "
                + "name = ?, "
                + "password = ?, "
                + " WHERE username= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, a.getName());
            statement.setString(2, a.getPassword());
            statement.setString(3, username);

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return false;
    }

}
