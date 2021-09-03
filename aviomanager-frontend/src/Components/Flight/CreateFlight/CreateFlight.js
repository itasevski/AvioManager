import React from "react";
import {CssBaseline, Paper, Typography} from "@material-ui/core";
import FormStyles from "../../../Utilities/FormStyles/FormStyles";
import CreateFlightForm from "./CreateFlightForm/CreateFlightForm";

const CreateFlight = (props) => {
    const classes = FormStyles();

    return (
        <React.Fragment>
            <CssBaseline />
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <Typography component="h1" variant="h4" align="center" className={classes.typography}>
                        Create flight
                    </Typography>
                    <React.Fragment>
                        <React.Fragment>
                            <CreateFlightForm countries={props.countries}
                                              planes={props.planes}
                                              createFlight={props.createFlight} />
                        </React.Fragment>
                    </React.Fragment>
                </Paper>
            </main>
        </React.Fragment>
    );
}

export default CreateFlight;