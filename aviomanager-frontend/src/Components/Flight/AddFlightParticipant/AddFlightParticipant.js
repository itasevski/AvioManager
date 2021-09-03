import React from "react";
import FormStyles from "../../../Utilities/FormStyles/FormStyles";
import {CssBaseline, Paper, Typography} from "@material-ui/core";
import AddFlightParticipantForm from "./AddFlightParticipantForm/AddFlightParticipantForm";
import {useParams} from "react-router-dom";

const AddFlightParticipant = (props) => {
    const classes = FormStyles();
    const {id} = useParams();

    return (
        <React.Fragment>
            <CssBaseline />
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <Typography component="h1" variant="h5" align="center" className={classes.typography}>
                        Add flight participant
                    </Typography>
                    <React.Fragment>
                        <React.Fragment>
                            <AddFlightParticipantForm flightId={id}
                                                      people={props.people}
                                                      addFlightParticipant={props.addFlightParticipant} />
                        </React.Fragment>
                    </React.Fragment>
                </Paper>
            </main>
        </React.Fragment>
    );
}

export default AddFlightParticipant;