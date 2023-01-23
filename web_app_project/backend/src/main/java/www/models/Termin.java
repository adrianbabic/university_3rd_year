package www.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "termin")
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endTime;
//    @Column(nullable = false)
//    private int startTime;
//    @Column(nullable = false)
//    private int endTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instruktor_id", nullable = false)
    private Instruktor instruktor;

    public Termin() {
    }

    public Termin(Date startTime, Date endTime, Instruktor instruktor) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.instruktor = instruktor;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Termin{" +
                "StartTime=" + startTime +
                ", EndTime=" + endTime +
                '}';
    }

}
