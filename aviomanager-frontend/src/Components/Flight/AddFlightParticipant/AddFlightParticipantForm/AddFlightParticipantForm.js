import React, {useEffect} from "react";
import {Box, Button, FormLabel, Grid, TextField, Typography} from "@material-ui/core";
import {Autocomplete} from "@material-ui/lab";
import {Link} from "react-router-dom";

const AddFlightParticipantForm = (props) => {
    const [state, setState] = React.useState({
        people: [],
        addedFlightParticipants: []
    });

    useEffect(() => {
        setState({
            ...state,
            people: props.people
        });
    }, [props.people]);


    const handleFormSubmit = (event) => {
        event.preventDefault();
        const person = document.getElementById("person").value;

        const personId = person.split(" ")[0];

        props.addFlightParticipant(props.flightId, personId);

        const addedFlightParticipants = state.addedFlightParticipants;
        addedFlightParticipants.push(person + " - SUCCESSFULLY ADDED" )

        setState({
            ...state,
            addedFlightParticipants: addedFlightParticipants
        });
    }


    return (
        <React.Fragment>
            <form onSubmit={handleFormSubmit}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="person">Person name/ID</FormLabel>
                        <Autocomplete
                            options={state.people}
                            freeSolo
                            id="person"
                            getOptionLabel={(option) => option.id.id + " - " + option.name + " " + option.surname}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input person name or ID..."
                                                                name="person"
                                                                required
                            />}
                        />
                    </Grid>
                </Grid>
                <Grid item xs={12}>
                    <Box mt={3}>
                        <Grid container justifyContent="flex-end">
                            <Link to="/flights" style={{ textDecoration: "none", marginRight: "15px" }}>
                                <Button
                                    type="button"
                                    color="primary"
                                    variant="outlined"
                                >
                                    Back
                                </Button>
                            </Link>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                            >
                                Add
                            </Button>
                        </Grid>
                    </Box>
                </Grid>
                <Grid item xs={12}>
                    <Box mt={5}>
                        <Grid container>
                            {state.addedFlightParticipants.map((addedFlightParticipant) => {
                                return (
                                    <Typography variant="body2">{addedFlightParticipant}</Typography>
                                );
                            })}
                        </Grid>
                    </Box>
                </Grid>
            </form>
        </React.Fragment>
    );
}

export default AddFlightParticipantForm;