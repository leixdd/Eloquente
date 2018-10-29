package eloquente.com.eloquente.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ARIELLECIAS on 10/29/2018.
 */

public class Reserve {

    @SerializedName("fx_sched_id")
    private String fx_sched_id;

    @SerializedName("fx_account_id")
    private String fx_account_id;

    @SerializedName("fx_packages_id")
    private String fx_packages_id;

    @SerializedName("fx_inclusions")
    private String fx_inclusions;

    @SerializedName("fx_date")
    private String fx_date;

    @SerializedName("fx_timeStart")
    private String fx_timeStart;

    @SerializedName("fx_timeEnd")
    private String fx_timeEnd;

    @SerializedName("fx_totalAmount")
    private String fx_totalAmount;

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

    public String getFx_sched_id() {
        return fx_sched_id;
    }

    public void setFx_sched_id(String fx_sched_id) {
        this.fx_sched_id = fx_sched_id;
    }

    public String getFx_account_id() {
        return fx_account_id;
    }

    public void setFx_account_id(String fx_account_id) {
        this.fx_account_id = fx_account_id;
    }

    public String getFx_packages_id() {
        return fx_packages_id;
    }

    public void setFx_packages_id(String fx_packages_id) {
        this.fx_packages_id = fx_packages_id;
    }

    public String getFx_inclusions() {
        return fx_inclusions;
    }

    public void setFx_inclusions(String fx_inclusions) {
        this.fx_inclusions = fx_inclusions;
    }

    public String getFx_date() {
        return fx_date;
    }

    public void setFx_date(String fx_date) {
        this.fx_date = fx_date;
    }

    public String getFx_timeStart() {
        return fx_timeStart;
    }

    public void setFx_timeStart(String fx_timeStart) {
        this.fx_timeStart = fx_timeStart;
    }

    public String getFx_timeEnd() {
        return fx_timeEnd;
    }

    public void setFx_timeEnd(String fx_timeEnd) {
        this.fx_timeEnd = fx_timeEnd;
    }

    public String getFx_totalAmount() {
        return fx_totalAmount;
    }

    public void setFx_totalAmount(String fx_totalAmount) {
        this.fx_totalAmount = fx_totalAmount;
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
