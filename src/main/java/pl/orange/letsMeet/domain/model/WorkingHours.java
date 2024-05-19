package pl.orange.letsMeet.domain.model;

import java.time.LocalTime;

public class WorkingHours {
    private LocalTime start;
    private LocalTime end;

    public WorkingHours(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public WorkingHours() {
    }


    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "WorkingHours{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
