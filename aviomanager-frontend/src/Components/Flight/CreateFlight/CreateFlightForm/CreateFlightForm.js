import React, {useEffect} from "react";
import {Box, Button, FormLabel, Grid, TextField} from "@material-ui/core";
import {KeyboardDatePicker, KeyboardTimePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import {Autocomplete} from "@material-ui/lab";
import {Link, useHistory} from "react-router-dom";
import {format} from "date-fns";

const CreateFlightForm = (props) => {
    const history = useHistory();

    const [state, setState] = React.useState({
        departureDate: new Date(),
        arrivalDate: new Date(),
        departureCountryId: "",
        destinationCountryId: "",
        planeId: "",

        countries: [],
        planes: []
    });

    useEffect(() => {
        setState({
            ...state,
            countries: props.countries,
            planes: props.planes
        });
    }, [props.countries]);

    const handleDepartureDateChange = (date) => {
        setState({
            ...state,
            departureDate: date
        });
    };

    const handleArrivalDateChange = (date) => {
        setState({
            ...state,
            arrivalDate: date
        });
    };

    const handleFieldChange = (event) => {
        setState({
            ...state,
            [event.target.name]: event.target.value
        });
    }

    const handleFormSubmit = (event) => {
        event.preventDefault();
        const departureCountryName = document.getElementById("departureCountry").value;
        const destinationCountryName = document.getElementById("destinationCountry").value;
        const planeName = document.getElementById("planeName").value;

        const departureDate = format(state.departureDate, "dd/MM/yyyy HH:mm");
        const arrivalDate = format(state.arrivalDate, "dd/MM/yyyy HH:mm");
        const departureCountryId = state.countries.find(country => country.countryName === departureCountryName).id.id;
        const destinationCountryId = state.countries.find(country => country.countryName === destinationCountryName).id.id;
        const planeId = state.planes.find(plane => plane.planeName === planeName).id.id;

        props.createFlight(departureDate, arrivalDate, departureCountryId, destinationCountryId, planeId);
        history.push("/flights");
    }

    return (
        <React.Fragment>
            <form onSubmit={handleFormSubmit}>
                <Grid container spacing={3}>
                    <Grid item xs={7}>
                        <FormLabel htmlFor="date">Departure date</FormLabel>
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <KeyboardDatePicker
                                required
                                fullWidth
                                margin="normal"
                                id="departureDate"
                                name="departureDate"
                                label="Pick a date"
                                format="dd/MM/yyyy"
                                value={state.departureDate}
                                onChange={handleDepartureDateChange}
                                KeyboardButtonProps={{
                                    'aria-label': 'change date',
                                }}
                            />
                        </MuiPickersUtilsProvider>
                    </Grid>
                    <Grid item xs={5}>
                        <FormLabel htmlFor="time">Departure time</FormLabel>
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <KeyboardTimePicker
                                required
                                fullWidth
                                margin="normal"
                                id="departureTime"
                                name="departureTime"
                                label="Pick a time"
                                value={state.departureDate}
                                onChange={handleDepartureDateChange}
                                KeyboardButtonProps={{
                                    'aria-label': 'change time',
                                }}
                            />
                        </MuiPickersUtilsProvider>
                    </Grid>
                    <Grid item xs={7}>
                        <FormLabel htmlFor="date">Arrival date</FormLabel>
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <KeyboardDatePicker
                                required
                                fullWidth
                                margin="normal"
                                id="arrivalDate"
                                name="arrivalDate"
                                label="Pick a date"
                                format="dd/MM/yyyy"
                                value={state.arrivalDate}
                                onChange={handleArrivalDateChange}
                                KeyboardButtonProps={{
                                    'aria-label': 'change date',
                                }}
                            />
                        </MuiPickersUtilsProvider>
                    </Grid>
                    <Grid item xs={5}>
                        <FormLabel htmlFor="time">Arrival time</FormLabel>
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <KeyboardTimePicker
                                required
                                fullWidth
                                margin="normal"
                                id="arrivalTime"
                                name="arrivalTime"
                                label="Pick a time"
                                value={state.arrivalDate}
                                onChange={handleArrivalDateChange}
                                KeyboardButtonProps={{
                                    'aria-label': 'change time',
                                }}
                            />
                        </MuiPickersUtilsProvider>
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="departureCountry">Departure country</FormLabel>
                        <Autocomplete
                            options={state.countries}
                            freeSolo
                            id="departureCountry"
                            getOptionLabel={(option) => option.countryName}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input departure country..."
                                                                name="departureCountry"
                                                                required
                                                                />}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="destination">Destination</FormLabel>
                        <Autocomplete
                            options={state.countries}
                            freeSolo
                            id="destinationCountry"
                            getOptionLabel={(option) => option.countryName}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input destination country..."
                                                                name="destinationCountry"
                                                                required
                                                                />}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="planeName">Plane model name</FormLabel>
                        <Autocomplete
                            options={state.planes}
                            freeSolo
                            id="planeName"
                            getOptionLabel={(option) => option.planeName}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input plane model name..."
                                                                name="planeName"
                                                                required
                                                                />}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Box mt={2}>
                            <Grid container justifyContent="flex-end">
                                <Link to="/flights" style={{ textDecoration: "none", marginRight: "15px" }}>
                                    <Button
                                        type="button"
                                        color="primary"
                                        variant="outlined"
                                    >
                                        Cancel
                                    </Button>
                                </Link>
                                <Button
                                    type="submit"
                                    variant="contained"
                                    color="primary"
                                >
                                    Create
                                </Button>
                            </Grid>
                        </Box>
                    </Grid>
                </Grid>
            </form>
        </React.Fragment>
    );
}

export default CreateFlightForm;