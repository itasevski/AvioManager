import React from "react";
import {CssBaseline, Paper, Typography} from "@material-ui/core";
import FormStyles from "../../../Utilities/FormStyles/FormStyles";
import CreatePersonForm from "./CreatePersonForm/CreatePersonForm";

const CreatePerson = (props) => {
    const classes = FormStyles();

    return (
        <React.Fragment>
            <CssBaseline />
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <Typography component="h1" variant="h4" align="center" className={classes.typography}>
                        Create person
                    </Typography>
                    <React.Fragment>
                        <React.Fragment>
                            <CreatePersonForm countries={props.countries}
                                              createPerson={props.createPerson} />
                        </React.Fragment>
                    </React.Fragment>
                </Paper>
            </main>
        </React.Fragment>
    );
}

export default CreatePerson;