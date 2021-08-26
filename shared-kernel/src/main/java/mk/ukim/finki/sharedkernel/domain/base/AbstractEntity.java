package mk.ukim.finki.sharedkernel.domain.base;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * AbstractEntity class - a superclass for entities. Every entity in every bounded context extends this class.
 * @param <ID> - A generic type for the Entity's ID, that must extend the base ID superclass. This ID's related
 *            fields will be persisted (embedded) in the database, creating a primary composite key for the entity.
 */
@Getter
@MappedSuperclass
public class AbstractEntity<ID extends EntityId> {

    @EmbeddedId
    private ID id;

    protected AbstractEntity() {}

    protected AbstractEntity(@NonNull ID id) {
        this.id = Objects.requireNonNull(id, "ID of entity must not be null");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !this.getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        var other = (AbstractEntity<?>) obj;
        return this.id != null && this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? super.hashCode() : this.id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", this.getClass().getSimpleName(), this.id);
    }

}
