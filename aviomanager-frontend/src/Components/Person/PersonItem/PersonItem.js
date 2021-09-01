import React from "react";
import StyledTableRow from "../../../Utilities/StyledTableRow/StyledTableRow";
import StyledTableCell from "../../../Utilities/StyledTableCell/StyledTableCell";
import {IconButton} from "@material-ui/core";
import {Delete} from "@material-ui/icons";

const PersonItem = (props) => {
    return (
        <StyledTableRow>
            <StyledTableCell>{props.person.id.id}</StyledTableCell>
            <StyledTableCell>{props.person.name}</StyledTableCell>
            <StyledTableCell>{props.person.surname}</StyledTableCell>
            <StyledTableCell>{props.person.country.countryName}</StyledTableCell>
            {props.person.numFlights !== undefined ?
                <StyledTableCell>{props.person.numFlights.value}</StyledTableCell> :
                <StyledTableCell>/</StyledTableCell>
            }
            {props.person.yearsExperience !== undefined ?
                <StyledTableCell>{props.person.yearsExperience.value}</StyledTableCell> :
                <StyledTableCell>/</StyledTableCell>
            }
            <StyledTableCell>
                <IconButton onClick={() => props.deletePerson(props.person.id.id)}>
                    <Delete style={{ color: "red" }} />
                </IconButton>
            </StyledTableCell>
        </StyledTableRow>
    );
}

export default PersonItem;