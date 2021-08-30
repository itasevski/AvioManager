import React from "react";
import {Box, Button, FormLabel, Grid, TextField} from "@material-ui/core";
import {KeyboardDatePicker, KeyboardTimePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import {Autocomplete} from "@material-ui/lab";
import {Link} from "react-router-dom";

const CreateCountryForm = () => {
    const [state, setState] = React.useState({
        testData: [
            { data: "data1" },
            { data: "data2" },
            { data: "data3" },
            { data: "data4" }
        ]
    });

    return (
        <React.Fragment>
            <form>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <FormLabel htmlFor="countryName">Country name</FormLabel>
                        <Autocomplete
                            options={state.testData}
                            freeSolo
                            id="countryName"
                            getOptionLabel={(option) => option.data}
                            renderInput={(params) => <TextField {...params}
                                                                label="Input country name..."
                                                                name="countryName"
                                                                required
                            />}
                        />
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