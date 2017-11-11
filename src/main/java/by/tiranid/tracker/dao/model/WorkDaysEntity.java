package by.tiranid.tracker.dao.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "work_days")
@ToString
@EqualsAndHashCode
public class WorkDaysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
    @Basic
    @Column(name = "work_date", nullable = false)
    @Getter
    @Setter
    private Date workDate;
    @Basic
    @Column(name = "iterations", nullable = false)
    @Getter
    @Setter
    private Byte iterations;
    @Basic
    @Column(name = "work_time", nullable = false, length = -1)
    @Getter
    @Setter
    private String workTime;


}
