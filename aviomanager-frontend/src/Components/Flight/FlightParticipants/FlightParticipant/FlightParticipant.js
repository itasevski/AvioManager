import React from "react";
import {IconButton, TableCell, TableRow} from "@material-ui/core";
import {Delete} from "@material-ui/icons";

const FlightParticipant = (props) => {
    return (
        <TableRow>
            <TableCell>{props.flightParticipant.id.id}</TableCell>
            <TableCell>{props.flightParticipant.person.name}</TableCell>
            <TableCell>{props.flightParticipant.person.surname}</TableCell>
            <TableCell>{props.flightParticipant.flightParticipantRole}</TableCell>
            <TableCell>
                <IconButton onClick={() => props.deleteFlightParticipant(props.flightId, props.flightParticipant.id.id)}>
                    <Delete style={{ color: "red" }} />
                </IconButton>
            </TableCell>
        </TableRow>
    )
}

export default FlightParticipant;