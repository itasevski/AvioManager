package mk.ukim.finki.planes.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.planes.domain.model.exception.PlaneNotFoundException;
import mk.ukim.finki.planes.domain.repository.PlaneRepository;
import mk.ukim.finki.planes.service.PlaneService;
import mk.ukim.finki.planes.service.form.PlaneForm;
import mk.ukim.finki.sharedkernel.domain.event.plane.PlaneDeletedEvent;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * PlaneServiceImplementation class - contains all implemented application services for the "planes" bounded context. All of the operations/services/methods
 * will be executed in a single transaction, hence the @Transactional annotation. This will ensure atomicity.
 */
@Service
@Transactional
@AllArgsConstructor
public class PlaneServiceImplementation implements PlaneService {

    private final PlaneRepository planeRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public PlaneId createPlane(PlaneForm planeForm) {
        Objects.requireNonNull(planeForm, "Plane form must not be null.");

        var violations = this.validator.validate(planeForm);
        if(violations.size() > 0) throw new ConstraintViolationException("The plane form is not valid.", violations);

        Plane plane = toDomainObject(planeForm);
        this.planeRepository.saveAndFlush(plane);

        return plane.getId();
    }

    @Override
    public List<Plane> findAll() {
        return this.planeRepository.findAll();
    }

    @Override
    public Optional<Plane> findById(PlaneId planeId) {
        return this.planeRepository.findById(planeId);
    }

    @Override
    public Optional<Plane> findByPlaneName(PlaneName planeName) {
        return this.planeRepository.findByPlaneName(planeName);
    }

    @Override
    public void deleteById(PlaneId planeId) {
        if(!this.planeRepository.existsById(planeId)) throw new PlaneNotFoundException("Plane with id " + planeId + " does not exist.");
        this.planeRepository.deleteById(planeId);

        Optional<Plane> plane = findByPlaneName(PlaneName.NOT_SPECIFIED);

        if(plane.isEmpty()) throw new PlaneNotFoundException("Plane with name " + PlaneName.NOT_SPECIFIED.name() + " does not exist.");

        this.domainEventPublisher.publish(new PlaneDeletedEvent(planeId.getId(), plane.get().getId().getId()));
    }

    private Plane toDomainObject(PlaneForm planeForm) {
        return new Plane(planeForm.getPlaneName(), planeForm.getNumSeats());
    }

}
