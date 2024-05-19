package pl.orange.letsMeet.domain.model;

import java.time.LocalTime;

public class TimeStamp {
    private LocalTime start;
    private LocalTime end;

    public TimeStamp(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public TimeStamp() {
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
        return
                "{start='" + start + '\'' +
                        ", end='" + end + '\'' +
                        '}';
    }
}
