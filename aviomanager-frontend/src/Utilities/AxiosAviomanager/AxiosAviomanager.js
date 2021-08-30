import axios from "axios";

const AxiosAviomanager = axios.create({
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
});

export default AxiosAviomanager;