import java.util.List;
import java.util.Scanner;


public class DatabaseApp {
    
    private Scanner scanner;
    private MealPlanningDAL mealPlanningDAL;
    private ArcadeGamesDAL arcadeGamesDAL;
    private String username;
    private String password;
    

    public DatabaseApp() {
        this.scanner = new Scanner(System.in);
        this.mealPlanningDAL = new MealPlanningDAL();
        this.arcadeGamesDAL = new ArcadeGamesDAL();
    }
    
 
    public void run() {
        getCredentials();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    runMealPlanningQuery();
                    break;
                case 2:
                    runArcadeGamesQuery();
                    break;
                case 3:
                    runGetRecipesStoredProcedure();
                    break;
                case 4:
                    runArcadeGamesStatementQuery();
                    break;
                case 5:
                    runArcadeGamesPreparedStatementQuery();
                    break;
                case 6:
                    runArcadeGamesCallableStatementQuery();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
  
    private void getCredentials() {
        System.out.println("Welcome to the Database Application");
        System.out.println("==================================");
        
        System.out.print("Enter MySQL username: ");
        username = scanner.nextLine();
        
        System.out.print("Enter MySQL password: ");
        password = scanner.nextLine();
        
        System.out.println();
    }
    

    private void displayMenu() {
        System.out.println("\nDatabase Operations Menu");
        System.out.println("=======================");
        System.out.println("1. Run a query against the MealPlanning database");
        System.out.println("2. Run a query against the ArcadeGames database");
        System.out.println("3. Run GetRecipes stored procedure against the MealPlanning database");
        System.out.println("4. Run Statement query against the ArcadeGames database");
        System.out.println("5. Run PreparedStatement query against the ArcadeGames database");
        System.out.println("6. Run CallableStatement query against the ArcadeGames database");
        System.out.println("0. Exit");
        System.out.print("\nEnter your choice: ");
    }
    
    /**
     * Get the user's menu choice.
     * 
     * @return the selected menu option
     */
    private int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }

    private void runMealPlanningQuery() {
        System.out.println("\nRunning query against MealPlanning database:");
        System.out.println("==========================================");
        
        List<Recipe> recipes = mealPlanningDAL.executeQuery("SELECT * FROM Recipe", username, password);
        
        if (recipes.isEmpty()) {
            System.out.println("No recipes found or error occurred.");
        } else {
            System.out.println("Found " + recipes.size() + " recipes:");
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
            }
        }
    }
    
  
    private void runArcadeGamesQuery() {
        System.out.println("\nRunning query against ArcadeGames database:");
        System.out.println("=========================================");
        
        List<ArcadeGame> games = arcadeGamesDAL.executeStatementQuery(username, password);
        
        if (games.isEmpty()) {
            System.out.println("No games found or error occurred.");
        } else {
            System.out.println("Found " + games.size() + " games:");
            for (ArcadeGame game : games) {
                System.out.println(game);
            }
        }
    }
    
    private void runGetRecipesStoredProcedure() {
        System.out.println("\nRunning GetRecipes stored procedure:");
        System.out.println("==================================");
        
        List<Recipe> recipes = mealPlanningDAL.executeGetRecipes(username, password);
        
        if (recipes.isEmpty()) {
            System.out.println("No recipes found or error occurred.");
        } else {
            System.out.println("Found " + recipes.size() + " recipes:");
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
            }
        }
    }
    
  
    private void runArcadeGamesStatementQuery() {
        System.out.println("\nRunning Statement query against ArcadeGames database:");
        System.out.println("==================================================");
        
        List<ArcadeGame> games = arcadeGamesDAL.executeStatementQuery(username, password);
        
        if (games.isEmpty()) {
            System.out.println("No games found or error occurred.");
        } else {
            System.out.println("Found " + games.size() + " games:");
            for (ArcadeGame game : games) {
                System.out.println(game);
            }
        }
    }

    private void runArcadeGamesPreparedStatementQuery() {
        System.out.println("\nRunning PreparedStatement query against ArcadeGames database:");
        System.out.println("========================================================");
        
        System.out.print("Enter manufacturer name to search for: ");
        String manufacturer = scanner.nextLine();
        
        List<ArcadeGame> games = arcadeGamesDAL.executePreparedStatementQuery(manufacturer, username, password);
        
        if (games.isEmpty()) {
            System.out.println("No games found for manufacturer '" + manufacturer + "' or error occurred.");
        } else {
            System.out.println("Found " + games.size() + " games from " + manufacturer + ":");
            for (ArcadeGame game : games) {
                System.out.println(game);
            }
        }
    }
    
  
    private void runArcadeGamesCallableStatementQuery() {
        System.out.println("\nRunning CallableStatement query against ArcadeGames database:");
        System.out.println("========================================================");
        
        System.out.print("Enter release year to search for: ");
        int year = Integer.parseInt(scanner.nextLine());
        
        List<ArcadeGame> games = arcadeGamesDAL.executeCallableStatementQuery(year, username, password);
        
        if (games.isEmpty()) {
            System.out.println("No games found for year " + year + " or error occurred.");
        } else {
            System.out.println("Found " + games.size() + " games from " + year + ":");
            for (ArcadeGame game : games) {
                System.out.println(game);
            }
        }
    }
    

    public static void main(String[] args) {
        DatabaseApp app = new DatabaseApp();
        app.run();
    }
}