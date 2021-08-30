import React from "react";
import {Box, Button, FormLabel, Grid, TextField} from "@material-ui/core";
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
                                <Link to="/planes" style={{ textDecoration: "none", marginRight: "15px" }}>
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