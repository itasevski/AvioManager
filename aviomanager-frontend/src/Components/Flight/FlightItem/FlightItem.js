import React from "react";
import StyledTableCell from "../../../Utilities/StyledTableCell/StyledTableCell";
import StyledTableRow from "../../../Utilities/StyledTableRow/StyledTableRow";
import {IconButton, Tooltip} from "@material-ui/core";
import {Add, Delete, Visibility} from "@material-ui/icons";
import {isAfter, isBefore} from "date-fns";
import {Link} from "react-router-dom";

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
                <Tooltip title="View flight participants">
                    <Link onClick={() => props.fetchFlightParticipants(props.flight.id.id)}
                          to={`/flights/${props.flight.id.id}/flightParticipants`}>
                        <IconButton>
                            <Visibility />
                        </IconButton>
                    </Link>
                </Tooltip>
            </StyledTableCell>
            <StyledTableCell>
                <Tooltip title="Add flight participants">
                    <Link to={`/flights/${props.flight.id.id}/flightParticipants/add`}>
                        <IconButton>
                            <Add />
                        </IconButton>
                    </Link>
                </Tooltip>
            </StyledTableCell>
            <StyledTableCell>
                <IconButton onClick={() => props.deleteFlight(props.flight.id.id)}>
                    <Delete style={{ color: "red" }} />
                </IconButton>
            </StyledTableCell>
        </StyledTableRow>
    );
}

export default FlightItem;