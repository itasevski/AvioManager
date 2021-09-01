package mk.ukim.finki.flightmanagement.service;

import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightStatus;
import mk.ukim.finki.flightmanagement.service.form.FlightForm;
import mk.ukim.finki.flightmanagement.service.form.FlightParticipantForm;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    FlightId createFlight(FlightForm flightForm);

    List<Flight> findAll();

    Optional<Flight> findById(FlightId flightId);

    Optional<Flight> updateById(FlightId flightId, FlightStatus flightStatus);

    void deleteById(FlightId flightId);

    void addFlightParticipant(FlightId flightId, FlightParticipantForm flightParticipantForm);

    void removeFlightParticipant(FlightId flightId, FlightParticipantId flightParticipantId);

}
