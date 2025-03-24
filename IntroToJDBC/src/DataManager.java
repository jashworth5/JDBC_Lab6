
import java.sql.*;

public class DataManager {
    
    /**
     * Gets a connection to the MealPlanning database.
     * 
     * @param user MySQL username
     * @param password MySQL password
     * @return Connection object or null if connection fails
     */
    public Connection getMealPlanningConnection(String user, String password) {
        return getConnection("MealPlanning", user, password);
    }
    
    /**
     * Gets a connection to the ArcadeGames database.
     * 
     * @param user MySQL username
     * @param password MySQL password
     * @return Connection object or null if connection fails
     */
    public Connection getArcadeGamesConnection(String user, String password) {
        return getConnection("ArcadeGames", user, password);
    }
    
    /**
     * Gets a connection to the VideoGameSystems database.
     * 
     * @param user MySQL username
     * @param password MySQL password
     * @return Connection object or null if connection fails
     */
    public Connection getVideoGameSystemsConnection(String user, String password) {
        return getConnection("VideoGameSystems", user, password);
    }
    
    /**
     * Gets a database connection to the specified database.
     * 
     * @param databaseName name of the database to connect to
     * @param user MySQL username
     * @param password MySQL password
     * @return Connection object or null if connection fails
     */
    private Connection getConnection(String databaseName, String user, String password) {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database: " + exception.getMessage());
            return null;
        }
    }
}