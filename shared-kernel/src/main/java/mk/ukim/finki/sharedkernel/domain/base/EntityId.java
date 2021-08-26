package mk.ukim.finki.sharedkernel.domain.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * EntityId class - a superclass for entity primary keys. Every entity's correlated id in every bounded context extends this class.
 * It implements the Serializable class, ensuring the ID's serialization and database persistence.
 */
@Getter
@Embeddable
@MappedSuperclass
public class EntityId implements Serializable {

    private String id;

    @JsonCreator
    protected EntityId(@NonNull String id) {
        this.id = Objects.requireNonNull(id, "ID of entity must not be null.");
    }

    public static <ID extends EntityId> ID generateRandomId(@NonNull Class<ID> callerClass) {
        Objects.requireNonNull(callerClass, "Caller class must not be null");
        try {
            return callerClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + callerClass, e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.id == null || !this.getClass().equals(obj.getClass())) return false;

        var other = (EntityId) obj;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
