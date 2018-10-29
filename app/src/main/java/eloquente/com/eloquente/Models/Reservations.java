package eloquente.com.eloquente.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ARIELLECIAS on 10/29/2018.
 */

public class Reservations {

    @SerializedName("fx_sched_id")
    private String fx_sched_id;

    @SerializedName("image")
    private String image;

    @SerializedName("fx_totalAmount")
    private String fx_totalAmount;

    @SerializedName("name")
    private String name;

    public String getFx_sched_id() {
        return fx_sched_id;
    }

    public void setFx_sched_id(String fx_sched_id) {
        this.fx_sched_id = fx_sched_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFx_totalAmount() {
        return fx_totalAmount;
    }

    public void setFx_totalAmount(String fx_totalAmount) {
        this.fx_totalAmount = fx_totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
