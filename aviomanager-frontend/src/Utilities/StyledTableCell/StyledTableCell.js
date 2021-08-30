import {TableCell, withStyles} from "@material-ui/core";

const StyledTableCell = withStyles((theme) => ({
    head: {
        color: theme.palette.common.black,
        fontSize: 18
    },
    body: {
        fontSize: 17
    },
}))(TableCell);

export default StyledTableCell;