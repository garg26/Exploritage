package simplifii.framework.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nbansal2211 on 09/12/16.
 */

public class Office implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("center_name")
    @Expose
    private String name;
    private boolean checked;

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
