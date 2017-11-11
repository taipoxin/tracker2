package by.tiranid.tracker.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "work_iters")
@ToString
@EqualsAndHashCode
public class WorkItersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
    @Basic
    @Column(name = "ddate", nullable = false)
    @Getter
    @Setter
    private Date ddate;
    @Basic
    @Column(name = "ttime", nullable = false)
    @Getter
    @Setter
    private Time ttime;
    @Basic
    @Column(name = "duration", nullable = false)
    @Getter
    @Setter
    private Time duration;


}
