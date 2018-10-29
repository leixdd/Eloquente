package eloquente.com.eloquente.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ARIELLECIAS on 10/28/2018.
 */

public class Services {

    @SerializedName("packages")
    @Expose
    private List<Packages> packages;

    @SerializedName("inclusions")
    @Expose
    private List<Inclusion> inclusions;

    public List<Packages> getPackages() {
        return packages;
    }

    public void setPackages(List<Packages> packages) {
        this.packages = packages;
    }

    public List<Inclusion> getInclusions() {
        return inclusions;
    }

    public void setInclusions(List<Inclusion> inclusions) {
        this.inclusions = inclusions;
    }
}
