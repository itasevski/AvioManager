import React, {useEffect} from "react";
import {Box, Button, FormLabel, Grid, TextField} from "@material-ui/core";
import {Autocomplete} from "@material-ui/lab";
import {Link, useHistory} from "react-router-dom";

const CreatePlaneForm = (props) => {
    const history = useHistory();

    const [state, setState] = React.useState({
        planes: []
    });

    useEffect(() => {
        setState({
            ...state,
            planes: props.planes
        });
    }, [props.planes]);

    const handleFormSubmit = (event) => {
        event.preventDefault();

        const planeName = document.getElementById("planeName").value;

        props.createPlane(planeName);
        history.push("/planes");
    }

    return (
        <React.Fragment>
            <form onSubmit={handleFormSubmit}>
                <Grid container spacing={3}>
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
                        <span style={{ color: "red" }}>First add the input string to the list of PlaneName enum values in the Shared Kernel, then create the Plane.</span>
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

export default CreatePlaneForm;