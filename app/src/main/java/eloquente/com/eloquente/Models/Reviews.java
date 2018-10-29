package eloquente.com.eloquente.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ARIELLECIAS on 10/27/2018.
 */

public class Reviews {



    @SerializedName("id")
    private String id;

    @SerializedName("comments")
    private String comments;

    @SerializedName("name")
    private String name;

    @SerializedName("ratings")
    private String ratings;

    @SerializedName("date")
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
