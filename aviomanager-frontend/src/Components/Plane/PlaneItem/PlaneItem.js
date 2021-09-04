import React from "react";
import StyledTableCell from "../../../Utilities/StyledTableCell/StyledTableCell";
import StyledTableRow from "../../../Utilities/StyledTableRow/StyledTableRow";
import {IconButton} from "@material-ui/core";
import {Delete} from "@material-ui/icons";

const PlaneItem = (props) => {
    if(props.plane.planeName === "NOT_SPECIFIED") {
        return (
            <span></span>
        );
    }

    return (
        <StyledTableRow>
            <StyledTableCell>{props.plane.id.id}</StyledTableCell>
            <StyledTableCell>{props.plane.planeName}</StyledTableCell>
            <StyledTableCell>{props.plane.numSeats.value}</StyledTableCell>
            <StyledTableCell>
                <IconButton onClick={() => props.deletePlane(props.plane.id.id)}>
                    <Delete style={{ color: "red" }} />
                </IconButton>
            </StyledTableCell>
        </StyledTableRow>
    );
}

export default PlaneItem;