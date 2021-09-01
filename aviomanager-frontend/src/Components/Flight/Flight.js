import React from "react";
import {Button, Grid, Table, TableBody, TableContainer, TableHead, TableRow, Typography} from "@material-ui/core";
import StyledTableCell from "../../Utilities/StyledTableCell/StyledTableCell";
import FlightItem from "./FlightItem/FlightItem";
import {Link} from "react-router-dom";

const Flight = (props) => {
    return (
        <div>
            <Grid container justifyContent="center" style={{ marginTop: "50px" }}>
                <Grid item xs={10}>
                    <Grid container>
                        <Typography variant="h4" style={{ marginBottom: "20px" }}>Flights</Typography>
                        <TableContainer>
                            <Table>
                                <TableHead style={{ backgroundColor: "lightgray" }}>
                                    <TableRow>
                                        <StyledTableCell>UUID</StyledTableCell>
                                        <StyledTableCell>Departure date</StyledTableCell>
                                        <StyledTableCell>Arrival date</StyledTableCell>
                                        <StyledTableCell>Departure country</StyledTableCell>
                                        <StyledTableCell>Destination</StyledTableCell>
                                        <StyledTableCell>Plane</StyledTableCell>
                                        <StyledTableCell>No. of participants</StyledTableCell>
                                        <StyledTableCell>Status</StyledTableCell>
                                        <StyledTableCell></StyledTableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {props.flights.map((flight) => {
                                        return (
                                            <FlightItem flight={flight} deleteFlight={props.deleteFlight} updateFlight={props.updateFlight} />
                                        );
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Grid>
                    <Grid container justifyContent="flex-end" style={{ marginTop: "30px", marginBottom: "60px" }}>
                        <Link to="/createFlight" style={{ textDecoration: "none" }}>
                            <Button variant="outlined" type="button">Create flight</Button>
                        </Link>
                    </Grid>
                </Grid>
            </Grid>
        </div>
    );
}

export default Flight;