import React, {Component} from "react";
import {BrowserRouter as Router, Route} from "react-router-dom";
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

class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <main>
                <Router>
                    <Header />

                    <Route path={"/flights"} exact render={() => <Flight /> } />
                    <Route path={"/createFlight"} exact render={() => <CreateFlight /> } />

                    <Route path={"/countries"} exact render={() => <Country /> } />
                    <Route path={"/createCountry"} exact render={() => <CreateCountry /> } />

                    <Route path={"/planes"} exact render={() => <Plane /> } />
                    <Route path={"/createPlane"} exact render={() => <CreatePlane /> } />

                    <Route path={"/people"} exact render={() => <Person /> } />
                    <Route path={"/createPerson"} exact render={() => <CreatePerson /> } />

                    <Route path={"/"} exact render={() => <Flight /> } />
                </Router>
            </main>
        );
    }

    componentDidMount() {
    }

}

export default App;
