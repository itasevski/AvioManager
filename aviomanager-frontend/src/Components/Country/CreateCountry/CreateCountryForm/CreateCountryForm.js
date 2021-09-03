import React, {useEffect} from "react";
import {Box, Button, FormLabel, Grid, TextField} from "@material-ui/core";
import {Autocomplete} from "@material-ui/lab";
import {Link} from "react-router-dom";
import { useHistory } from "react-router-dom";

const CreateCountryForm = (props) => {
    const history = useHistory();

    const [state, setState] = React.useState({
        countries: []
    });

    useEffect(() => {
       setState({
           ...state,
           countries: props.countries
       });
    }, [props.countries]);


    const handleFormSubmit = (event) => {
        event.preventDefault();

        const countryName = document.getElementById("countryName").value;

        props.createCountry(countryName);
        history.push("/countries");
    }

    return (
        <React.Fragment>
            <form onSubmit={handleFormSubmit}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="countryName">Country name</FormLabel>
                        <Autocomplete
                            options={state.countries}
                            freeSolo
                            id="countryName"
                            getOptionLabel={(option) => option.countryName}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input country name..."
                                                                name="countryName"
                                                                required
                            />}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <span style={{ color: "red" }}>First add the input string to the list of CountryName enum values in the Shared Kernel, then create the Country.</span>
                    </Grid>
                    <Grid item xs={12}>
                        <Box mt={2}>
                            <Grid container justifyContent="flex-end">
                                <Link to="/countries" style={{ textDecoration: "none", marginRight: "15px" }}>
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

export default CreateCountryForm;