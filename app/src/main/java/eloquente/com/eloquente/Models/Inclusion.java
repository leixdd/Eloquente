package eloquente.com.eloquente.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ARIELLECIAS on 10/28/2018.
 */

public class Inclusion {

    @SerializedName("inclusions_group_id")
    private String inclusions_group_id;

    @SerializedName("inclusions")
    private String inclusions;

    @SerializedName("price")
    private String price;

    public String getInclusions_group_id() {
        return inclusions_group_id;
    }

    public void setInclusions_group_id(String inclusions_group_id) {
        this.inclusions_group_id = inclusions_group_id;
    }

    public String getInclusions() {
        return inclusions;
    }

    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
