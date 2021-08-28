package mk.ukim.finki.planes.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.planes.domain.model.exception.PlaneNotFoundException;
import mk.ukim.finki.planes.domain.repository.PlaneRepository;
import mk.ukim.finki.planes.service.PlaneService;
import mk.ukim.finki.planes.service.form.PlaneForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class PlaneServiceImplementation implements PlaneService {

    private final PlaneRepository planeRepository;
    private final Validator validator;

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
    public Plane findById(PlaneId planeId) {
        return this.planeRepository.findById(planeId)
                .orElseThrow(() -> new PlaneNotFoundException("Plane with id " + planeId + " not found."));
    }

    private Plane toDomainObject(PlaneForm planeForm) {
        return new Plane(planeForm.getPlaneName());
    }

}
