package mk.ukim.finki.planes.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

import javax.persistence.Embeddable;

@Embeddable
public class PlaneId extends EntityId {

    private PlaneId() {
        super(PlaneId.generateRandomId(PlaneId.class).getId());
    }

    public PlaneId(@NonNull String id) {
        super(id);
    }

    public PlaneId of(String id) {
        return new PlaneId(id);
    }

}
