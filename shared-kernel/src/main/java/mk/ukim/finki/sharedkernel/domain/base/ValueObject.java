package mk.ukim.finki.sharedkernel.domain.base;

/**
 * ValueObject interface - A base interface for value objects that extends another interface that also extends the Serializable class.
 * Every value object implements this interface, which ensures serialization and database persistence.
 */
public interface ValueObject extends DomainObject {
}
