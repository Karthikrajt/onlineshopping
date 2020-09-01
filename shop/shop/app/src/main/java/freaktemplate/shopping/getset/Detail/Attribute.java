package siragu.shopping.getset.Detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attribute {

    @SerializedName("attributeset")
    @Expose
    private String set;
    @SerializedName("attribute")
    @Expose
    private String attributename;
    @SerializedName("value")
    @Expose
    private String value;

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }


    public String getAttributename() {
        return attributename;
    }

    public void setAttributename(String attributename) {
        this.attributename = attributename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
