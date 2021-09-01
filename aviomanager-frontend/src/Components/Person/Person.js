import React from "react";
import {Button, Grid, Table, TableBody, TableContainer, TableHead, TableRow, Typography} from "@material-ui/core";
import StyledTableCell from "../../Utilities/StyledTableCell/StyledTableCell";
import PersonItem from "./PersonItem/PersonItem";
import {Link} from "react-router-dom";

const Person = (props) => {
    return (
        <div>
            <Grid container justifyContent="center" style={{ marginTop: "50px" }}>
                <Grid item xs={10}>
                    <Grid container>
                        <Typography variant="h4" style={{ marginBottom: "20px" }}>People</Typography>
                        <TableContainer>
                            <Table>
                                <TableHead style={{ backgroundColor: "lightgray" }}>
                                    <TableRow>
                                        <StyledTableCell>UUID</StyledTableCell>
                                        <StyledTableCell>Name</StyledTableCell>
                                        <StyledTableCell>Surname</StyledTableCell>
                                        <StyledTableCell>Nationality</StyledTableCell>
                                        <StyledTableCell>No. of flights attended</StyledTableCell>
                                        <StyledTableCell>Years experience</StyledTableCell>
                                        <StyledTableCell></StyledTableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {props.people.map((person) => {
                                        return (
                                            <PersonItem person={person} deletePerson={props.deletePerson} />
                                        );
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Grid>
                    <Grid container justifyContent="flex-end" style={{ marginTop: "30px", marginBottom: "60px" }}>
                        <Link to="/createPerson" style={{ textDecoration: "none" }}>
                            <Button variant="outlined" type="button">Create person</Button>
                        </Link>
                    </Grid>
                </Grid>
            </Grid>
        </div>
    );
}

export default Person;