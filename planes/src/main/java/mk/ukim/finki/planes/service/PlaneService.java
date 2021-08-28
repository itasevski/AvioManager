package mk.ukim.finki.planes.service;

import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.planes.service.form.PlaneForm;

import java.util.List;

public interface PlaneService {

    PlaneId createPlane(PlaneForm planeForm);

    List<Plane> findAll();

    Plane findById(PlaneId planeId);

}
