package by.tiranid.tracker.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "work_days")
public class WorkDaysEntity {
    private Long id;
    private Date workDate;
    private Byte iterations;
    private String workTime;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "work_date", nullable = false)
    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    @Basic
    @Column(name = "iterations", nullable = false)
    public Byte getIterations() {
        return iterations;
    }

    public void setIterations(Byte iterations) {
        this.iterations = iterations;
    }

    @Basic
    @Column(name = "work_time", nullable = false, length = -1)
    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkDaysEntity that = (WorkDaysEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (iterations != null ? !iterations.equals(that.iterations) : that.iterations != null) return false;
        if (workDate != null ? !workDate.equals(that.workDate) : that.workDate != null) return false;
        if (workTime != null ? !workTime.equals(that.workTime) : that.workTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + (workDate != null ? workDate.hashCode() : 0);
        result = 31 * result + (iterations != null ? iterations.hashCode() : 0);
        result = 31 * result + (workTime != null ? workTime.hashCode() : 0);
        return (int) result;
    }
	
	@Override
    public String toString() {
        return new StringBuilder()
                .append("id: " + this.id + "\n")
                .append("work_date: " + this.workDate + "\n")
                .append("iterations: " + this.iterations + "\n")
                .append("work_time: " + this.workTime + "\n")
                .toString();
    }
}
