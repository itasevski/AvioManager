import React from "react";
import {Button, Grid, Table, TableBody, TableContainer, TableHead, TableRow, Typography} from "@material-ui/core";
import StyledTableCell from "../../Utilities/StyledTableCell/StyledTableCell";
import PlaneItem from "./PlaneItem/PlaneItem";
import {Link} from "react-router-dom";

const Plane = (props) => {
    return (
        <div>
            <Grid container justifyContent="center" style={{ marginTop: "50px" }}>
                <Grid item xs={5}>
                    <Grid container>
                        <Typography variant="h4" style={{ marginBottom: "20px" }}>Planes</Typography>
                        <TableContainer>
                            <Table>
                                <TableHead style={{ backgroundColor: "lightgray" }}>
                                    <TableRow>
                                        <StyledTableCell>UUID</StyledTableCell>
                                        <StyledTableCell>Plane name</StyledTableCell>
                                        <StyledTableCell></StyledTableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {props.planes.map((plane) => {
                                        return (
                                            <PlaneItem plane={plane} deletePlane={props.deletePlane} />
                                        );
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Grid>
                    <Grid container justifyContent="flex-end" style={{ marginTop: "30px", marginBottom: "60px" }}>
                        <Link to="/createPlane" style={{ textDecoration: "none" }}>
                            <Button variant="outlined" type="button">Create plane</Button>
                        </Link>
                    </Grid>
                </Grid>
            </Grid>
        </div>
    );
}

export default Plane