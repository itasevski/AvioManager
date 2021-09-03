package mk.ukim.finki.planes.service;

import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.planes.service.form.PlaneForm;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;

import java.util.List;
import java.util.Optional;

public interface PlaneService {

    PlaneId createPlane(PlaneForm planeForm);

    List<Plane> findAll();

    Optional<Plane> findById(PlaneId planeId);

    Optional<Plane> findByPlaneName(PlaneName planeName);

    void deleteById(PlaneId planeId);

}
