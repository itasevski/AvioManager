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

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            flights: [],
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
                                                                         updateFlight={this.updateFlight} /> } />
                    <Route path={"/createFlight"} exact render={() => <CreateFlight countries={this.state.countries}
                                                                                    planes={this.state.planes} /> } />

                    <Route path={"/countries"} exact render={() => <Country countries={this.state.countries}
                                                                            deleteCountry={this.deleteCountry} /> } />
                    <Route path={"/createCountry"} exact render={() => <CreateCountry /> } />

                    <Route path={"/planes"} exact render={() => <Plane planes={this.state.planes}
                                                                       deletePlane={this.deletePlane} /> } />
                    <Route path={"/createPlane"} exact render={() => <CreatePlane /> } />

                    <Route path={"/people"} exact render={() => <Person people={this.state.people}
                                                                        deletePerson={this.deletePerson} /> } />
                    <Route path={"/createPerson"} exact render={() => <CreatePerson countries={this.state.countries} /> } />

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
            .then((data) => {
                this.fetchPeople();
            });
    }

    deletePlane = (id) => {
        AviomanagerService.deletePlane(id)
            .then((data) => {
                this.fetchPlanes();
            });
    }

    deleteCountry = (id) => {
        AviomanagerService.deleteCountry(id)
            .then((data) => {
                this.fetchCountries();
            });
    }

    updateFlight = (id, flightStatus) => {
        AviomanagerService.updateFlight(id, flightStatus)
            .then((data) => {
                this.fetchFlights();
            });
    }

}

export default App;
