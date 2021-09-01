import React from "react";
import StyledTableCell from "../../../Utilities/StyledTableCell/StyledTableCell";
import StyledTableRow from "../../../Utilities/StyledTableRow/StyledTableRow";
import {IconButton} from "@material-ui/core";
import {Delete} from "@material-ui/icons";
import {isAfter, isBefore} from "date-fns";

const FlightItem = (props) => {

    const reformatDate = (date) => {
        return date.substr(3,3) + date.substr(0,2) + date.substr(5);
    }

    var now = new Date();
    var departureDate = new Date(reformatDate(props.flight.flightDates.departureDate));
    var arrivalDate = new Date(reformatDate(props.flight.flightDates.arrivalDate));

    if((isAfter(now, departureDate) && isBefore(now, arrivalDate)) && props.flight.flightStatus !== "IN_AIR") {
        props.updateFlight(props.flight.id.id, "IN_AIR");
    }
    else if(isAfter(now, arrivalDate) && props.flight.flightStatus !== "ARRIVED") {
        props.updateFlight(props.flight.id.id, "ARRIVED");
    }

    return (
        <StyledTableRow>
            <StyledTableCell>{props.flight.id.id}</StyledTableCell>
            <StyledTableCell>{props.flight.flightDates.departureDate}</StyledTableCell>
            <StyledTableCell>{props.flight.flightDates.arrivalDate}</StyledTableCell>
            <StyledTableCell>{props.flight.departureCountry.countryName}</StyledTableCell>
            <StyledTableCell>{props.flight.destinationCountry.countryName}</StyledTableCell>
            <StyledTableCell>{props.flight.plane.planeName}</StyledTableCell>
            <StyledTableCell>{props.flight.numParticipants.value}</StyledTableCell>
            <StyledTableCell>{props.flight.flightStatus}</StyledTableCell>
            <StyledTableCell>
                <IconButton onClick={() => props.deleteFlight(props.flight.id.id)}>
                    <Delete style={{ color: "red" }} />
                </IconButton>
            </StyledTableCell>
        </StyledTableRow>
    );
}

export default FlightItem;