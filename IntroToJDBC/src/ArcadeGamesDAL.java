import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArcadeGamesDAL {
    private DataManager dataManager;
    

    public ArcadeGamesDAL() {
        this.dataManager = new DataManager();
    }
    
    /**
     * Execute a query using a Statement against the ArcadeGames database.
     * 
     * @param query SQL query to execute
     * @param user MySQL username
     * @param password MySQL password
     * @return A list of ArcadeGame objects
     */
    public List<ArcadeGame> executeStatementQuery(String user, String password) {
        List<ArcadeGame> games = new ArrayList<>();
        String query = "SELECT * FROM ArcadeGame";
        
        Connection connection = dataManager.getArcadeGamesConnection(user, password);
        if (connection == null) {
            System.out.println("Failed to get a connection, cannot execute query");
            return games;
        }
        
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            
            while (results.next()) {
                int gameId = results.getInt("GameId");
                String gameName = results.getString("GameName");
                int releaseYear = results.getInt("ReleaseYear");
                String manufacturer = results.getString("Manufacturer");
                
                games.add(new ArcadeGame(gameId, gameName, releaseYear, manufacturer));
            }
            
            results.close();
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("Error executing statement query: " + e.getMessage());
        }
        
        return games;
    }
    
    /**
     * Execute a query using a PreparedStatement against the ArcadeGames database.
     * 
     * @param manufacturerName the manufacturer to search for
     * @param user MySQL username
     * @param password MySQL password
     * @return A list of ArcadeGame objects
     */
    public List<ArcadeGame> executePreparedStatementQuery(String manufacturerName, String user, String password) {
        List<ArcadeGame> games = new ArrayList<>();
        String query = "SELECT * FROM ArcadeGame WHERE Manufacturer = ?";
        
        Connection connection = dataManager.getArcadeGamesConnection(user, password);
        if (connection == null) {
            System.out.println("Failed to get a connection, cannot execute query");
            return games;
        }
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, manufacturerName);
            
            ResultSet results = preparedStatement.executeQuery();
            
            while (results.next()) {
                int gameId = results.getInt("GameId");
                String gameName = results.getString("GameName");
                int releaseYear = results.getInt("ReleaseYear");
                String manufacturer = results.getString("Manufacturer");
                
                games.add(new ArcadeGame(gameId, gameName, releaseYear, manufacturer));
            }
            
            results.close();
            preparedStatement.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("Error executing prepared statement query: " + e.getMessage());
        }
        
        return games;
    }
    
    /**
     * Execute a stored procedure using a CallableStatement against the ArcadeGames database.
     * 
     * @param year the release year to search for
     * @param user MySQL username
     * @param password MySQL password
     * @return A list of ArcadeGame objects
     */
    public List<ArcadeGame> executeCallableStatementQuery(int year, String user, String password) {
        List<ArcadeGame> games = new ArrayList<>();
        
        Connection connection = dataManager.getArcadeGamesConnection(user, password);
        if (connection == null) {
            System.out.println("Failed to get a connection, cannot execute stored procedure");
            return games;
        }
        
        try {
            CallableStatement callableStatement = connection.prepareCall("{Call GetGamesByYear(?)}");
            callableStatement.setInt(1, year);
            
            ResultSet results = callableStatement.executeQuery();
            
            while (results.next()) {
                int gameId = results.getInt("GameId");
                String gameName = results.getString("GameName");
                int releaseYear = results.getInt("ReleaseYear");
                String manufacturer = results.getString("Manufacturer");
                
                games.add(new ArcadeGame(gameId, gameName, releaseYear, manufacturer));
            }
            
            results.close();
            callableStatement.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("Error executing callable statement query: " + e.getMessage());
        }
        
        return games;
    }
}