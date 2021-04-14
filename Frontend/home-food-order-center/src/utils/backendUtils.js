import frontendUtils from "./frontendUtils";
import axios from "axios";
import constants from "./constants";


//Основния път
const ROOT_URL = "http://localhost:9004";
//Релативни пътища
const REGISTER_URL = ROOT_URL + "/auth/register";
const LOGIN_URL = ROOT_URL + "/auth/login";
const LOGOUT_URL = ROOT_URL + "/auth/logout";
const IMAGE_CREATE_URL = ROOT_URL + "/image/upload";
const IMAGE_DELETE_URL = ROOT_URL + "/image/delete";
const CATEGORY_GETALL_URL = ROOT_URL + "/category/getAllForSelect";
const PODUCT_GETALL_URL = ROOT_URL + "/product/getAll";
const PRODUCT_DETAILS_URL = ROOT_URL + "/product/details/";
const ORDER_ADD_URL = ROOT_URL + "/order/add";
const ORDER_DELETE_URL = ROOT_URL + "/order/delete/";
const RECEIPT_GET_BY_STATUS_SHOPPING_URL = ROOT_URL + "/receipt/currUser/shopping";
const RECEIPT_CONFIRM_URL = ROOT_URL + "/receipt/confirm";
const RECEIPT_GETALL_URL = ROOT_URL + "/receipt/getAll";
const RECEIPT_GET_BY_ID_URL = ROOT_URL + "/receipt/getById/";
const USER_PROFILE_PATH = ROOT_URL + "/user/profile/";
const USER_EDIT_PATH = ROOT_URL + "/user/edit/";

const ADMIN_USER_DELETE_PATH = ROOT_URL + "/admin/user/delete/";
const ADMIN_PRODUCT_CREATE_URL = ROOT_URL + "/admin/product/create";
const ADMIN_USER_LIST_PATH = ROOT_URL + "/admin/user/getAll";
const ADMIN_PRODUCT_EDIT_PATH = ROOT_URL + "/admin/product/edit/";
const ADMIN_MAKE_ADMIN_URL = ROOT_URL + "/admin/makeAdmin/";
const ADMIN_REMOVE_ADMIN_URL = ROOT_URL + "/admin/removeAdmin/";
const ADMIN_RECEIPT_GETALL_URL = ROOT_URL + "/admin/receipt/getAll";
const ADMIN_RECEIPT_PAID_URL = ROOT_URL + "/admin/receipt/paid/";


function handleErrors(response) {
    if (!response.ok && response.status == '500') {
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
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + sessionStorage.getItem(constants.TOKEN_KEY_NAME),
        },
        body: JSON.stringify(data)
    }).then(handleErrors)
};

// const REQ_DELETE = function (url) {
//     return fetch(url, {
//         method: "DELETE", // *GET, POST, PUT, DELETE, etc.
//         mode: "cors", // no-cors, cors, *same-origin
//         headers: {
//             // 'Accept': 'application/json',
//             // 'Content-Type': 'application/json',
//             'Authorization': 'Bearer ' + sessionStorage.getItem(constants.TOKEN_KEY_NAME),
//         }
//     }).then(handleErrors)
// };

const REQ_GET = function (url, params) {
    if (params == null) {
        return fetch(url, {
            method: "GET", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, cors, *same-origin
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + sessionStorage.getItem(constants.TOKEN_KEY_NAME),
            },
        }).then(handleErrors)
    } else {
        let query = Object.keys(params)
            .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
            .join('&');
        return fetch(url + "?" + query, {
            method: "GET", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, cors, *same-origin
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + sessionStorage.getItem(constants.TOKEN_KEY_NAME),
            },
        }).then(handleErrors)
    }

};

const backend = {
    ADMIN_USER_LIST_PATH,
    USER_PROFILE_PATH,
    RECEIPT_GET_BY_ID_URL,
    RECEIPT_GETALL_URL,
    RECEIPT_CONFIRM_URL,
    ORDER_DELETE_URL,
    RECEIPT_GET_BY_STATUS_SHOPPING_URL,
    ORDER_ADD_URL,
    PRODUCT_DETAILS_URL,
    PODUCT_GETALL_URL,
    CATEGORY_GETALL_URL,
    IMAGE_DELETE_URL,
    IMAGE_CREATE_URL,
    ADMIN_PRODUCT_CREATE_URL,
    LOGOUT_URL,
    LOGIN_URL,
    REGISTER_URL,
    REQ_POST,
    REQ_GET,
    handleErrors,
    USER_EDIT_PATH,
    ADMIN_USER_DELETE_PATH,
    ADMIN_MAKE_ADMIN_URL,
    ADMIN_REMOVE_ADMIN_URL,
    ADMIN_RECEIPT_GETALL_URL,
    ADMIN_RECEIPT_PAID_URL,
    ADMIN_PRODUCT_EDIT_PATH
};
export default backend;
