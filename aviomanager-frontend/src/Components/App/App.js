import React, {Component} from "react";
import {BrowserRouter as Router, Route, Redirect} from "react-router-dom";
import './App.css';
import Header from "../Header/Header";
import Flight from "../Flight/Flight";
import Country from "../Country/Country";
import Plane from "../Plane/Plane";
import Person from "../Person/Person";
import CreateFlight from "../Flight/CreateFlight/CreateFlight";
import CreatePerson from "../Person/CreatePerson/CreatePerson";
import CreateCountry from "../Country/CreateCountry/CreateCountry";
import CreatePlane from "../Plane/CreatePlane/CreatePlane";
import AviomanagerService from "../../Services/AviomanagerService";
import FlightParticipants from "../Flight/FlightParticipants/FlightParticipants";
import AddFlightParticipant from "../Flight/AddFlightParticipant/AddFlightParticipant";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            flights: [],
            flightParticipants: [],
            people: [],
            planes: [],
            countries: []
        }
    }

    render() {
        return (
            <main>
                <Router>
                    <Header />

                    <Route path={"/flights"} exact render={() => <Flight flights={this.state.flights}
                                                                         deleteFlight={this.deleteFlight}
                                                                         updateFlight={this.updateFlight}
                                                                         fetchFlightParticipants={this.fetchFlightParticipantsByFlightId} /> } />
                    <Route path={"/flights/:id/flightParticipants"} exact render={() => <FlightParticipants flightParticipants={this.state.flightParticipants}
                                                                                                            deleteFlightParticipant={this.deleteFlightParticipant} /> } />
                    <Route path={"/flights/:id/flightParticipants/add"} exact render={() => <AddFlightParticipant people={this.state.people}
                                                                                                                  addFlightParticipant={this.addFlightParticipant} />} />
                    <Route path={"/createFlight"} exact render={() => <CreateFlight createFlight={this.createFlight}
                                                                                    countries={this.state.countries}
                                                                                    planes={this.state.planes} /> } />

                    <Route path={"/countries"} exact render={() => <Country countries={this.state.countries}
                                                                            deleteCountry={this.deleteCountry} /> } />
                    <Route path={"/createCountry"} exact render={() => <CreateCountry countries={this.state.countries}
                                                                                      createCountry={this.createCountry} /> } />

                    <Route path={"/planes"} exact render={() => <Plane planes={this.state.planes}
                                                                       deletePlane={this.deletePlane} /> } />
                    <Route path={"/createPlane"} exact render={() => <CreatePlane planes={this.state.planes}
                                                                                  createPlane={this.createPlane} /> } />

                    <Route path={"/people"} exact render={() => <Person people={this.state.people}
                                                                        deletePerson={this.deletePerson} /> } />
                    <Route path={"/createPerson"} exact render={() => <CreatePerson createPerson={this.createPerson}
                                                                                    countries={this.state.countries} /> } />

                    <Route path={"/"} exact render={() => <Redirect to="/flights"  /> } />
                </Router>
            </main>
        );
    }

    componentDidMount() {
        this.fetchFlights();
        this.fetchPeople();
        this.fetchPlanes();
        this.fetchCountries();
    }

    fetchFlights = () => {
        AviomanagerService.fetchFlights()
            .then((data) => {
                this.setState({
                    flights: data.data
                });
            });
    }

    fetchPeople = () => {
        AviomanagerService.fetchPeople()
            .then((data) => {
                this.setState({
                    people: data.data
                });
            });
    }

    fetchPlanes = ()  => {
        AviomanagerService.fetchPlanes()
            .then((data) => {
                this.setState({
                    planes: data.data
                });
            });
    }

    fetchCountries = () => {
        AviomanagerService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                });
            });
    }

    deleteFlight = (id) => {
        AviomanagerService.deleteFlight(id)
            .then((data) => {
                this.fetchFlights();
            });
    }

    deletePerson = (id) => {
        AviomanagerService.deletePerson(id)
            .then(async (data) => {
                this.fetchPeople();
                await new Promise(resolve => setTimeout(resolve, 1500));
                this.fetchFlights();
            });
    }

    deletePlane = (id) => {
        AviomanagerService.deletePlane(id)
            .then(async (data) => {
                this.fetchPlanes();
                await new Promise(resolve => setTimeout(resolve, 1500));
                this.fetchFlights();
            });
    }

    deleteCountry = (id) => {
        AviomanagerService.deleteCountry(id)
            .then(async (data) => {
                this.fetchCountries();
                await new Promise(resolve => setTimeout(resolve, 1500));
                this.fetchPeople();
                this.fetchFlights();
            });
    }

    updateFlight = (id, flightStatus) => {
        AviomanagerService.updateFlight(id, flightStatus)
            .then((data) => {
                this.fetchFlights();
                this.fetchPeople();
            });
    }

    createFlight = (departureDate, arrivalDate, departureCountryId, destinationCountryId, planeId) => {
        AviomanagerService.createFlight(departureDate, arrivalDate, departureCountryId, destinationCountryId, planeId)
            .then((data) => {
                this.fetchFlights();
            });
    }

    createPerson = (name, surname, countryId, numFlights, yearsExperience) => {
        AviomanagerService.createPerson(name, surname, countryId, numFlights, yearsExperience)
            .then((data) => {
                this.fetchPeople();
            });
    }

    createPlane = (planeName) => {
        AviomanagerService.createPlane(planeName)
            .then((data) => {
                this.fetchPlanes();
            });
    }

    createCountry = (countryName) => {
        AviomanagerService.createCountry(countryName)
            .then((data) => {
                this.fetchCountries();
            });
    }

    fetchFlightParticipantsByFlightId = (id) => {
        AviomanagerService.fetchFlightParticipantsByFlightId(id)
            .then((data) => {
                this.setState({
                    flightParticipants: data.data
                });
            });
    }

    addFlightParticipant = (flightId, personId) => {
        AviomanagerService.addFlightParticipant(flightId, personId)
            .then((data) => {
                this.fetchFlights();
            });
    }

    deleteFlightParticipant = (flightId, flightParticipantId) => {
        AviomanagerService.deleteFlightParticipant(flightId, flightParticipantId)
            .then((data) => {
                this.fetchFlightParticipantsByFlightId(flightId);
                this.fetchFlights();
            });
    }

}

export default App;
