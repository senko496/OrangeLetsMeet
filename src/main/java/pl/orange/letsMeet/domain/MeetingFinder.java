package pl.orange.letsMeet.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.orange.letsMeet.domain.model.CalendarData;
import pl.orange.letsMeet.domain.model.Meeting;
import pl.orange.letsMeet.domain.model.TimeStamp;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingFinder {
    public List<TimeStamp> findNoCollisionMeeting(String calendarJson1, String calendarJson2, LocalTime meetingDuration) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModules(new JavaTimeModule());

        CalendarData calendar1 = objectMapper.readValue(calendarJson1, CalendarData.class);
        CalendarData calendar2 = objectMapper.readValue(calendarJson2, CalendarData.class);

        return findAvailableMeetingTimes(calendar1, calendar2, meetingDuration);
    }

    private List<TimeStamp> findAvailableMeetingTimes(CalendarData calendar1, CalendarData calendar2, LocalTime meetingDuration) {
        List<TimeStamp> availableTimes;
        List<Meeting> allMeetings = new ArrayList<>();

        allMeetings.addAll(calendar1.getPlanned_meeting());
        allMeetings.addAll(calendar2.getPlanned_meeting());

        LocalTime commonStart = calculateCommonStart(calendar1, calendar2);
        LocalTime commonEnd = calculateCommonEnd(calendar1, calendar2);

        var timeStamps = createThirtyMinuteTimeStamps(meetingDuration, commonStart, commonEnd);

        var allMeetingsMapped = allMeetings.stream().map(m -> new TimeStamp(m.getStart(), m.getEnd())).collect(Collectors.toList());
        availableTimes = timeStamps.stream().filter(oneTimeStamp -> checkMeetingAvailability(oneTimeStamp, allMeetingsMapped)).collect(Collectors.toList());

        System.out.println(availableTimes);
        return availableTimes;
    }

    private List<TimeStamp> createThirtyMinuteTimeStamps(LocalTime meetingDuration, LocalTime commonStart, LocalTime commonEnd) {
        List<TimeStamp> timeStamps = new ArrayList<>();
        while (commonStart.isBefore(commonEnd)) {
            TimeStamp timeStamp = new TimeStamp(commonStart, commonStart.plusMinutes(30));
            timeStamps.add(timeStamp);
            commonStart = commonStart.plusMinutes(meetingDuration.getMinute());
        }
        return timeStamps;
    }

    private boolean checkMeetingAvailability(TimeStamp oneTimeStamp, List<TimeStamp> allMeetingsMapped) {
        for (TimeStamp t : allMeetingsMapped) {
            if ((oneTimeStamp.getStart().isAfter(t.getStart()) || oneTimeStamp.getStart().equals(t.getStart()))
                    && (oneTimeStamp.getEnd().isBefore(t.getEnd()) || oneTimeStamp.getEnd().equals(t.getEnd()))) {
                return false;
            }
        }
        return true;
    }

    private LocalTime calculateCommonStart(CalendarData calendar1, CalendarData calendar2) {
        LocalTime start1 = calendar1.getWorking_hours().getStart();
        LocalTime start2 = calendar2.getWorking_hours().getStart();
        return start1.isAfter(start2) ? start1 : start2;
    }

    private LocalTime calculateCommonEnd(CalendarData calendar1, CalendarData calendar2) {
        LocalTime end1 = calendar1.getWorking_hours().getEnd();
        LocalTime end2 = calendar2.getWorking_hours().getEnd();
        return end1.isBefore(end2) ? end1 : end2;
    }
}


