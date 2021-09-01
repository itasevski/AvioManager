import React from "react";
import {Button, Grid, Table, TableBody, TableContainer, TableHead, TableRow, Typography} from "@material-ui/core";
import StyledTableCell from "../../Utilities/StyledTableCell/StyledTableCell";
import CountryItem from "./CountryItem/CountryItem";
import {Link} from "react-router-dom";

const Country = (props) => {
    return (
        <div>
            <Grid container justifyContent="center" style={{ marginTop: "50px" }}>
                <Grid item xs={5}>
                    <Grid container>
                        <Typography variant="h4" style={{ marginBottom: "20px" }}>Countries</Typography>
                        <TableContainer>
                            <Table>
                                <TableHead style={{ backgroundColor: "lightgray" }}>
                                    <TableRow>
                                        <StyledTableCell>UUID</StyledTableCell>
                                        <StyledTableCell>Country name</StyledTableCell>
                                        <StyledTableCell></StyledTableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {props.countries.map((country) => {
                                        return (
                                            <CountryItem country={country} deleteCountry={props.deleteCountry} />
                                        );
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Grid>
                    <Grid container justifyContent="flex-end" style={{ marginTop: "30px", marginBottom: "60px" }}>
                        <Link to="/createCountry" style={{ textDecoration: "none" }}>
                            <Button variant="outlined" type="button">Create country</Button>
                        </Link>
                    </Grid>
                </Grid>
            </Grid>
        </div>
    );
}

export default Country;