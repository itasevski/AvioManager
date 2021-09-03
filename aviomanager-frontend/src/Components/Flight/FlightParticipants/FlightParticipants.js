import React from "react";
import {
    Button,
    Grid,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@material-ui/core";
import FlightParticipant from "./FlightParticipant/FlightParticipant";
import {Link, useParams} from "react-router-dom";

const FlightParticipants = (props) => {
    const {id} = useParams();

    return (
        <div>
            <Grid container justifyContent="center" style={{ marginTop: "100px" }}>
                <Grid item xs={6}>
                    <Grid container>
                        <Typography variant="h5" style={{ marginBottom: "20px", width: "100%" }}>Flight participants</Typography>
                        <Typography variant="h5" style={{ marginBottom: "20px" }}>Flight ID: {id}</Typography>
                        <TableContainer>
                            <Table aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell style={{ fontSize: "18px" }}>ID</TableCell>
                                        <TableCell style={{ fontSize: "18px" }}>Name</TableCell>
                                        <TableCell style={{ fontSize: "18px" }}>Surname</TableCell>
                                        <TableCell style={{ fontSize: "18px" }}>Role</TableCell>
                                        <TableCell></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {props.flightParticipants.map((flightParticipant) => {
                                        return (
                                            <FlightParticipant flightId={id}
                                                               flightParticipant={flightParticipant}
                                                               deleteFlightParticipant={props.deleteFlightParticipant} />
                                        )
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Grid>
                    <Grid container style={{ marginTop: "30px", marginBottom: "60px" }}>
                        <Link to="/flights" style={{ textDecoration: "none" }}>
                            <Button variant="outlined" type="button">Back</Button>
                        </Link>
                    </Grid>
                </Grid>
            </Grid>
        </div>
    )
}

export default FlightParticipants;