import frontendUtils from "./frontendUtils";


//Основния път
const ROOT_URL = "http://localhost:9004";
//Релативни пътища
const REGISTER_URL = ROOT_URL + "/auth/register";
const LOGIN_URL = ROOT_URL + "/auth/login";
const LOGOUT_URL = ROOT_URL + "/auth/logout";


function handleErrors(response) {
    if (!response.ok) {
        response.json()
            .then((data) => {
                frontendUtils.notifyError(data.message)
            })
        throw Error(response.status);
    }
    return response;
}

//Заявки към backend
const REQ_POST = function (url, data) {
    return fetch(url, {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        mode: "cors", // no-cors, cors, *same-origin
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(handleErrors)
};

const REQ_GET = function (url, data) {
    return fetch(url, {
        method: "GET", // *GET, POST, PUT, DELETE, etc.
        mode: "cors", // no-cors, cors, *same-origin
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(handleErrors)
};

const backend = {
    LOGOUT_URL,
    LOGIN_URL,
    REGISTER_URL,
    REQ_POST,
    REQ_GET
};
export default backend;
