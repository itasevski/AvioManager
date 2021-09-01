package mk.ukim.finki.planes.service;

import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.planes.service.form.PlaneForm;

import java.util.List;
import java.util.Optional;

public interface PlaneService {

    PlaneId createPlane(PlaneForm planeForm);

    List<Plane> findAll();

    Optional<Plane> findById(PlaneId planeId);

    void deleteById(PlaneId planeId);

}
