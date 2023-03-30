package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GorestPojo {
    private String meta;
    GorestDataPojo data;

    public GorestPojo() {
    }
    public GorestPojo(String meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GorestPojo{" +
                "meta='" + meta + '\'' +
                ", data=" + data +
                '}';
    }
}
