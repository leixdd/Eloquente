package eloquente.com.eloquente.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ARIELLECIAS on 10/28/2018.
 */

public class Packages {

    @SerializedName("package_id")
    private String packageID;

    @SerializedName("package_name")
    private String package_name;

    @SerializedName("amount")
    private String amount;

    @SerializedName("pax")
    private String pax;

    @SerializedName("soup")
    private String soup;

    @SerializedName("appetizer")
    private String appetizer;

    @SerializedName("salad")
    private String salad;

    @SerializedName("fish")
    private String fish;

    @SerializedName("pasta")
    private String pasta;

    @SerializedName("beef")
    private String beef;

    @SerializedName("vegetables")
    private String vegetables;

    @SerializedName("chicken")
    private String chicken;

    @SerializedName("dessert")
    private String dessert;

    @SerializedName("drinks")
    private String drinks;

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public String getAppetizer() {
        return appetizer;
    }

    public void setAppetizer(String appetizer) {
        this.appetizer = appetizer;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public String getFish() {
        return fish;
    }

    public void setFish(String fish) {
        this.fish = fish;
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }

    public String getBeef() {
        return beef;
    }

    public void setBeef(String beef) {
        this.beef = beef;
    }

    public String getVegetables() {
        return vegetables;
    }

    public void setVegetables(String vegetables) {
        this.vegetables = vegetables;
    }

    public String getChicken() {
        return chicken;
    }

    public void setChicken(String chicken) {
        this.chicken = chicken;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }
}
