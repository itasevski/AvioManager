package mk.ukim.finki.flightmanagement.service;

import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import mk.ukim.finki.flightmanagement.service.form.FlightForm;
import mk.ukim.finki.flightmanagement.service.form.FlightParticipantForm;

import java.util.List;

public interface FlightService {

    FlightId createFlight(FlightForm flightForm);

    List<Flight> findAll();

    Flight findById(FlightId flightId);

    void addFlightParticipant(FlightId flightId, FlightParticipantForm flightParticipantForm);

    void removeFlightParticipant(FlightId flightId, FlightParticipantId flightParticipantId);

}
