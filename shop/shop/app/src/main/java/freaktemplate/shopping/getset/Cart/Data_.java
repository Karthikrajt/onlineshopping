
package siragu.shopping.getset.Cart;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_ implements Serializable
{

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = 5180228722022891190L;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
