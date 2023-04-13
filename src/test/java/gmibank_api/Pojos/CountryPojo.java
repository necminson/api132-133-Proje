package gmibank_api.Pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryPojo {

        private int id;
        private String name;
        private List<StatePojo> states;

    public CountryPojo() {
    }

    public CountryPojo(String name, List<StatePojo> states) {
        this.name = name;
        this.states = states;
    }

    public CountryPojo(int id, String name, List<StatePojo> states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getStates() {
        return states;
    }

    public void setStates(List<StatePojo> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}
