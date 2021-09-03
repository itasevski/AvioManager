import React, {useEffect} from "react";
import {Box, Button, FormLabel, Grid, TextField} from "@material-ui/core";
import {Autocomplete} from "@material-ui/lab";
import {Link, useHistory} from "react-router-dom";

const CreatePersonForm = (props) => {
    const history = useHistory();

    const [state, setState] = React.useState({
        name: "",
        surname: "",
        countryId: "",
        numFlights: "",
        yearsExperience: "",

        countries: []
    });

    useEffect(() => {
        setState({
            ...state,
            countries: props.countries
        });
    }, [props.countries]);


    const handleFieldChange = (event) => {
        setState({
            ...state,
            [event.target.name]: event.target.value
        });
    }

    const handleFormSubmit = (event) => {
        event.preventDefault();
        const countryName = document.getElementById("nationality").value;

        const name = state.name;
        const surname = state.surname;
        const countryId = state.countries.find(country => country.countryName === countryName).id.id;
        const numFlights = state.numFlights !== "" ? state.numFlights : null;
        const yearsExperience = state.yearsExperience !== "" ? state.yearsExperience : null;

        props.createPerson(name, surname, countryId, numFlights, yearsExperience);
        history.push("/people");
    }

    return (
        <React.Fragment>
            <form onSubmit={handleFormSubmit}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="departureCountry">Name</FormLabel>
                        <TextField label="Input person name..."
                                   id="name"
                                   name="name"
                                   fullWidth
                                   required
                                   onChange={handleFieldChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="departureCountry">Surname</FormLabel>
                        <TextField label="Input person surname..."
                                   id="surname"
                                   name="surname"
                                   fullWidth
                                   required
                                   onChange={handleFieldChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="nationality">Nationality</FormLabel>
                        <Autocomplete
                            options={state.countries}
                            freeSolo
                            id="nationality"
                            getOptionLabel={(option) => option.countryName}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input person nationality..."
                                                                name="nationality"
                                                                required
                            />}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="personLimit">Flights attended (for flight attendant)</FormLabel>
                        <TextField
                            label="Input number of flights attended..."
                            id="numFlights"
                            name="numFlights"
                            type="number"
                            fullWidth
                            onChange={handleFieldChange}
                            disabled={state.yearsExperience !== ""}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="personLimit">Years experience (for pilot)</FormLabel>
                        <TextField
                            label="Input number of years experience..."
                            id="yearsExperience"
                            name="yearsExperience"
                            type="number"
                            fullWidth
                            onChange={handleFieldChange}
                            disabled={state.numFlights !== ""}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Box mt={2}>
                            <Grid container justifyContent="flex-end">
                                <Link to="/people" style={{ textDecoration: "none", marginRight: "15px" }}>
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

export default CreatePersonForm;