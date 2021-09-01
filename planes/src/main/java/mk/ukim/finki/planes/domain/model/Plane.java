package mk.ukim.finki.planes.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "plane")
public class Plane extends AbstractEntity<PlaneId> {

    @Enumerated(value = EnumType.STRING)
    private PlaneName planeName;

    protected Plane() {}

    public Plane(@NonNull PlaneName planeName) {
        super(PlaneId.generateRandomId(PlaneId.class));
        this.planeName = planeName;
    }

}
