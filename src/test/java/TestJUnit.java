import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import pl.orange.letsMeet.domain.MeetingFinder;

import java.time.LocalTime;

public class TestJUnit {
    @Test
    public void exec() throws JsonProcessingException {
        String calendarJson1 = "{\n" +
                "  \"working_hours\": {\n" +
                "    \"start\": \"09:00\",\n" +
                "    \"end\": \"19:55\"\n" +
                "  },\n" +
                "  \"planned_meeting\": [\n" +
                "    {\n" +
                "      \"start\": \"09:00\",\n" +
                "      \"end\": \"10:30\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"start\": \"12:00\",\n" +
                "      \"end\": \"13:00\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"start\": \"16:00\",\n" +
                "      \"end\": \"18:00\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        String calendarJson2 = "{\n" +
                "  \"working_hours\": {\n" +
                "    \"start\": \"10:00\",\n" +
                "    \"end\": \"18:30\"\n" +
                "  },\n" +
                "  \"planned_meeting\": [\n" +
                "    {\n" +
                "      \"start\": \"10:00\",\n" +
                "      \"end\": \"11:30\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"start\": \"12:30\",\n" +
                "      \"end\": \"14:30\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"start\": \"14:30\",\n" +
                "      \"end\": \"15:00\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"start\": \"16:00\",\n" +
                "      \"end\": \"17:00\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        MeetingFinder meetingFinder = new MeetingFinder();
        LocalTime meetingDuration = LocalTime.of(0, 30);
        meetingFinder.findNoCollisionMeeting(calendarJson1, calendarJson2, meetingDuration);
    }
}



