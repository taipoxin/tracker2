package by.tiranid.old;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Visit {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String description;
}
