package gmibank_api.Pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country_Without_IdPojo {
        private String name;
        private List<StatePojo> states;

    public Country_Without_IdPojo() {
    }

    public Country_Without_IdPojo(String name, List<StatePojo> states) {
        this.name = name;
        this.states = states;
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
        return "Country_Without_IdPojo{" +
                "name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}
