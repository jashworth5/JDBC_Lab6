public class Recipe {
    private String recipeName;
    private String cookbookName;
    private int totalServings;
    private boolean isBook;
    private String website;
    
    /**
     * Constructor for Recipe objects.
     * 
     * @param recipeName the name of the recipe
     * @param cookbookName the name of the cookbook
     * @param totalServings number of servings
     * @param isBook whether the source is a book
     * @param website website URL if applicable
     */
    public Recipe(String recipeName, String cookbookName, int totalServings, boolean isBook, String website) {
        this.recipeName = recipeName;
        this.cookbookName = cookbookName;
        this.totalServings = totalServings;
        this.isBook = isBook;
        this.website = website;
    }
    
    public String getRecipeName() {
        return recipeName;
    }
    
    public String getCookbookName() {
        return cookbookName;
    }
    
    public int getTotalServings() {
        return totalServings;
    }
    
    public boolean isBook() {
        return isBook;
    }
    
    public String getWebsite() {
        return website;
    }
    
    @Override
    public String toString() {
        return "Recipe: " + recipeName + 
               ", Cookbook: " + cookbookName + 
               ", Servings: " + totalServings +
               ", Source: " + (isBook ? "Book" : "Website: " + website);
    }
}