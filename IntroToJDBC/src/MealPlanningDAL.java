
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MealPlanningDAL {
    private DataManager dataManager;
    
    /**
     * Constructor that initializes the DataManager.
     */
    public MealPlanningDAL() {
        this.dataManager = new DataManager();
    }
    
    /**
     * Execute a query against the MealPlanning database.
     * 
     * @param query SQL query to execute
     * @param user MySQL username
     * @param password MySQL password
     * @return A list of Recipe objects
     */
    public List<Recipe> executeQuery(String query, String user, String password) {
        List<Recipe> recipes = new ArrayList<>();
        
        Connection connection = dataManager.getMealPlanningConnection(user, password);
        if (connection == null) {
            System.out.println("Failed to get a connection, cannot execute query");
            return recipes;
        }
        
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            
            while (results.next()) {
                String recipeName = results.getString("RecipeName");
                String cookbookName = results.getString("CookbookName");
                int totalServings = results.getInt("TotalServings");
                boolean isBook = results.getBoolean("IsBook");
                String website = results.getString("Website");
                
                recipes.add(new Recipe(recipeName, cookbookName, totalServings, isBook, website));
            }
            
            // Close resources
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        
        return recipes;
    }
    
    /**
     * Execute the GetRecipes stored procedure.
     * 
     * @param user MySQL username
     * @param password MySQL password
     * @return A list of Recipe objects
     */
    public List<Recipe> executeGetRecipes(String user, String password) {
        List<Recipe> recipes = new ArrayList<>();
        
        Connection connection = dataManager.getMealPlanningConnection(user, password);
        if (connection == null) {
            System.out.println("Failed to get a connection, cannot execute stored procedure");
            return recipes;
        }
        
        try {
            CallableStatement callableStatement = connection.prepareCall("{Call GetRecipes()}");
            ResultSet results = callableStatement.executeQuery();
            
            while (results.next()) {
                String recipeName = results.getString("RecipeName");
                String cookbookName = results.getString("CookbookName");
                int totalServings = results.getInt("TotalServings");
                boolean isBook = results.getBoolean("IsBook");
                String website = results.getString("Website");
                
                recipes.add(new Recipe(recipeName, cookbookName, totalServings, isBook, website));
            }
            
            // Close resources
            results.close();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error executing stored procedure: " + e.getMessage());
        }
        
        return recipes;
    }
    
    /**
     * Execute the InsertNewRecipe stored procedure with parameters.
     * 
     * @param user MySQL username
     * @param password MySQL password
     * @param recipeName name of the recipe
     * @param cookbookName name of the cookbook
     * @param numServings number of servings
     * @param isBook whether the source is a book
     * @param website website URL if applicable
     * @return true if the operation was successful, false otherwise
     */
    public boolean insertNewRecipe(String user, String password, String recipeName, String cookbookName, 
                                  int numServings, boolean isBook, String website) {
        Connection connection = dataManager.getMealPlanningConnection(user, password);
        if (connection == null) {
            System.out.println("Failed to get a connection, cannot execute stored procedure");
            return false;
        }
        
        try {
            CallableStatement callableStatement = connection.prepareCall("{Call InsertNewRecipe(?, ?, ?, ?, ?)}");
            callableStatement.setString(1, recipeName);
            callableStatement.setString(2, cookbookName);
            callableStatement.setInt(3, numServings);
            callableStatement.setBoolean(4, isBook);
            callableStatement.setString(5, website);
            
            callableStatement.execute();
            
            // Close resources
            callableStatement.close();
            connection.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error executing stored procedure: " + e.getMessage());
            return false;
        }
    }
}