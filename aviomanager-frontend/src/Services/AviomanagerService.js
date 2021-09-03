import AxiosAviomanager from "../Utilities/AxiosAviomanager/AxiosAviomanager";

const AviomanagerService = {
    fetchFlights: () => {
        return AxiosAviomanager.get("http://localhost:9092/api/flight");
    },
    fetchPeople: () => {
        return AxiosAviomanager.get("http://localhost:9091/api/person");
    },
    fetchPlanes: () => {
        return AxiosAviomanager.get("http://localhost:9090/api/plane");
    },
    fetchCountries: () => {
        return AxiosAviomanager.get("http://localhost:9093/api/country");
    },
    deleteFlight: (id) => {
        return AxiosAviomanager.get(`http://localhost:9092/api/flight/delete-id/${id}`);
    },
    deletePerson: (id) => {
        return AxiosAviomanager.get(`http://localhost:9091/api/person/delete-id/${id}`);
    },
    deletePlane: (id) => {
        return AxiosAviomanager.get(`http://localhost:9090/api/plane/delete-id/${id}`);
    },
    deleteCountry: (id) => {
        return AxiosAviomanager.get(`http://localhost:9093/api/country/delete-id/${id}`);
    },
    updateFlight: (id, flightStatus) => {
        return AxiosAviomanager.get(`http://localhost:9092/api/flight/update-id/${id}?flightStatus=${flightStatus}`)
    },
    createFlight: (departureDate, arrivalDate, departureCountryId, destinationCountryId, planeId) => {
        return AxiosAviomanager.post("http://localhost:9092/api/flight/create", {
            "flightDates": {
                "departureDate": departureDate,
                "arrivalDate": arrivalDate
            },
            "departureCountryId": departureCountryId,
            "destinationCountryId": destinationCountryId,
            "planeId": planeId
        });
    },
    createPerson: (name, surname, countryId, numFlights, yearsExperience) => {
        return AxiosAviomanager.post("http://localhost:9091/api/person/create", {
            "name": name,
            "surname": surname,
            "countryId": countryId,
            "numFlights": numFlights,
            "yearsExperience": yearsExperience
        });
    },
    createPlane: (planeName) => {
        return AxiosAviomanager.post("http://localhost:9090/api/plane/create", {
            "planeName": planeName
        });
    },
    createCountry: (countryName) => {
        return AxiosAviomanager.post("http://localhost:9093/api/country/create", {
            "countryName": countryName
        });
    },
    fetchFlightParticipantsByFlightId: (id) => {
        return AxiosAviomanager.get(`http://localhost:9092/api/flight/find-fp/${id}`);
    },
    addFlightParticipant: (flightId, personId) => {
        return AxiosAviomanager.post(`http://localhost:9092/api/flight/add-fp/${flightId}`, {
            "personId": personId
        });
    },
    deleteFlightParticipant: (flightId, flightParticipantId) => {
        return AxiosAviomanager.get(`http://localhost:9092/api/flight/delete-fp/${flightId}?flightParticipantId=${flightParticipantId}`)
    }

}


export default AviomanagerService;