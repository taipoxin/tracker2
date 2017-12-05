package by.tiranid.tracker.controller;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class IterationRequest {

    @Getter
    @Setter
    private String hash;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String time;
    @Getter
    @Setter
    private String duration;

    public IterationRequest() {
    }

    public IterationRequest(String hash,
                            String date,
                            String time,
                            String duration) {
        this.hash = hash;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }
}
