import React from "react";
import StyledTableCell from "../../../Utilities/StyledTableCell/StyledTableCell";
import StyledTableRow from "../../../Utilities/StyledTableRow/StyledTableRow";
import {IconButton} from "@material-ui/core";
import {Delete} from "@material-ui/icons";

const CountryItem = (props) => {
    if(props.country.countryName === "NOT_SPECIFIED") {
        return (
            <span></span>
        );
    }

    return (
        <StyledTableRow>
            <StyledTableCell>{props.country.id.id}</StyledTableCell>
            <StyledTableCell>{props.country.countryName}</StyledTableCell>
            <StyledTableCell>
                <IconButton onClick={() => props.deleteCountry(props.country.id.id)}>
                    <Delete style={{ color: "red" }} />
                </IconButton>
            </StyledTableCell>
        </StyledTableRow>
    );
}

export default CountryItem;