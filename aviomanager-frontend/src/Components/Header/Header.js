import React from "react";
import {AppBar, Toolbar, Typography} from "@material-ui/core";
import {Link} from "react-router-dom";
import "./Header.css";

const Header = () => {
    return (
        <div id="headerContainer">
            <AppBar position="static" color="transparent">
                <Toolbar>
                    <Typography variant="h4" style={{ paddingRight: "50px" }}>
                        <Link className="headerTitleLink" to="/">AvioManager</Link>
                    </Typography>
                    <Typography variant="h5" style={{ paddingRight: "25px" }}>
                        <Link className="headerLink" to="/flights">Flights</Link>
                    </Typography>
                    <Typography variant="h5" style={{ paddingRight: "25px" }}>
                        <Link className="headerLink" to="/countries">Countries</Link>
                    </Typography>
                    <Typography variant="h5" style={{ paddingRight: "25px" }}>
                        <Link className="headerLink" to="/planes">Planes</Link>
                    </Typography>
                    <Typography variant="h5">
                        <Link className="headerLink" to="/people">People</Link>
                    </Typography>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default Header;