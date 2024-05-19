package pl.orange.letsMeet.domain.model;

import java.util.List;

public class CalendarData {

    public CalendarData(WorkingHours working_hours, List<Meeting> planned_meeting) {
        this.working_hours = working_hours;
        this.planned_meeting = planned_meeting;
    }

    public CalendarData() {
    }

    private WorkingHours working_hours;
    private List<Meeting> planned_meeting;

    public WorkingHours getWorking_hours() {
        return working_hours;
    }

    public List<Meeting> getPlanned_meeting() {
        return planned_meeting;
    }

    public void setWorking_hours(WorkingHours working_hours) {
        this.working_hours = working_hours;
    }

    public void setPlanned_meeting(List<Meeting> planned_meeting) {
        this.planned_meeting = planned_meeting;
    }

    @Override
    public String toString() {
        return "CalendarData{" +
                "working_hours=" + working_hours +
                ", planned_meeting=" + planned_meeting +
                '}';
    }
}