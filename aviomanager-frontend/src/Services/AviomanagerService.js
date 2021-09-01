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
    }
}


export default AviomanagerService;