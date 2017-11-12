package by.tiranid.tracker.learning.consuming_json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPage {

    @Getter
    private String name;
    @Getter
    private String about;
    @Getter
    private String phone;
    @Getter
    private String website;


}
