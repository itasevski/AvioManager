import React from "react";
import {Box, Button, FormLabel, Grid, TextField} from "@material-ui/core";
import {KeyboardDatePicker, KeyboardTimePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import {Autocomplete} from "@material-ui/lab";
import {Link} from "react-router-dom";

const CreateFlightForm = () => {
    const [state, setState] = React.useState({
        departureDate: new Date(),
        arrivalDate: new Date(),
        testData: [
            { data: "data1" },
            { data: "data2" },
            { data: "data3" },
            { data: "data4" }
        ]
    });

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

    return (
        <React.Fragment>
            <form>
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
                            options={state.testData}
                            freeSolo
                            id="departureCountry"
                            getOptionLabel={(option) => option.data}
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
                            options={state.testData}
                            freeSolo
                            id="destination"
                            getOptionLabel={(option) => option.data}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input destination..."
                                                                name="destination"
                                                                required
                                                                />}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="planeName">Plane model name</FormLabel>
                        <Autocomplete
                            options={state.testData}
                            freeSolo
                            id="planeName"
                            getOptionLabel={(option) => option.data}
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