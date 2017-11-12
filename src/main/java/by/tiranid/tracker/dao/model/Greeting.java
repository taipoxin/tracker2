package by.tiranid.tracker.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Greeting {

    @Getter
    private final long id;
    @Getter
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

}
