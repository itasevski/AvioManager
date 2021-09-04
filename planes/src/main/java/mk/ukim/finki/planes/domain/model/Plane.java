package mk.ukim.finki.planes.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "plane")
public class Plane extends AbstractEntity<PlaneId> {

    @Enumerated(value = EnumType.STRING)
    private PlaneName planeName;

    @AttributeOverride(name = "value", column = @Column(name = "num_seats"))
    private NumberOfUnits numSeats;

    protected Plane() {}

    public Plane(@NonNull PlaneName planeName, @NonNull NumberOfUnits numSeats) {
        super(PlaneId.generateRandomId(PlaneId.class));
        this.planeName = planeName;
        this.numSeats = numSeats;
    }

}
