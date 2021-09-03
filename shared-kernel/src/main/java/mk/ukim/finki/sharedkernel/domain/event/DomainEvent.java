package mk.ukim.finki.sharedkernel.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

/**
 * DomainEvent class - a base event class that contains the topic of the event and a timestamp of its creation.
 */
public class DomainEvent {

    private String topic;
    private LocalDateTime timestamp;

    public DomainEvent(String topic) {
        this.topic = topic;
        timestamp = LocalDateTime.now();
    }

    public String getTopic() {
        return this.topic;
    }

    /**
     * A function that uses the Jackson ObjectMapper to convert objects to JSON which is used as a payload in the event to be sent.
     * @return - the JSON payload.
     */
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String output = null;

        try {
            output = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {

        }

        return output;
    }

    /**
     * A function that converts the JSON payload from the event into an actual instance that extends the DomainEvent base class.
     * @param json - the JSON payload.
     * @param eventClass - the event class that the JSON is going to be cast into.
     * @param <E> - a generic type used as a promise that the event class extends the DomainEvent base class.
     * @return the event class containing the fields injected from the JSON.
     * @throws JsonProcessingException if the JSON payload can't be cast into the event class.
     */
    public static <E extends DomainEvent> E fromJson(String json, Class<E> eventClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, eventClass);
    }

}
