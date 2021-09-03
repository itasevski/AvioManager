import React from "react";
import {CssBaseline, Paper, Typography} from "@material-ui/core";
import FormStyles from "../../../Utilities/FormStyles/FormStyles";
import CreateCountryForm from "./CreateCountryForm/CreateCountryForm";

const CreateCountry = (props) => {
    const classes = FormStyles();

    return (
        <React.Fragment>
            <CssBaseline />
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <Typography component="h1" variant="h4" align="center" className={classes.typography}>
                        Create country
                    </Typography>
                    <React.Fragment>
                        <React.Fragment>
                            <CreateCountryForm countries={props.countries}
                                               createCountry={props.createCountry} />
                        </React.Fragment>
                    </React.Fragment>
                </Paper>
            </main>
        </React.Fragment>
    );
}

export default CreateCountry;