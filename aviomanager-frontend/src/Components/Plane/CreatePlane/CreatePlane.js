import React from "react";
import {CssBaseline, Paper, Typography} from "@material-ui/core";
import FormStyles from "../../../Utilities/FormStyles/FormStyles";
import CreatePlaneForm from "./CreatePlaneForm/CreatePlaneForm";

const CreatePlane = (props) => {
    const classes = FormStyles();

    return (
        <React.Fragment>
            <CssBaseline />
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <Typography component="h1" variant="h4" align="center" className={classes.typography}>
                        Create plane
                    </Typography>
                    <React.Fragment>
                        <React.Fragment>
                            <CreatePlaneForm planes={props.planes}
                                             createPlane={props.createPlane} />
                        </React.Fragment>
                    </React.Fragment>
                </Paper>
            </main>
        </React.Fragment>
    );
}

export default CreatePlane;