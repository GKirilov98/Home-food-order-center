import frontendUtils from "./frontendUtils";
import constants from "./constants";
import {Loading} from "notiflix";


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
//Админ панел
const ADMIN_USER_DELETE_PATH = ROOT_URL + "/admin/user/delete/";
const ADMIN_MAKE_ADMIN_URL = ROOT_URL + "/admin/makeAdmin/";
const ADMIN_REMOVE_ADMIN_URL = ROOT_URL + "/admin/removeAdmin/";
//Бизнес потребител панел
const BUSINESS_PRODUCT_EDIT_PATH = ROOT_URL + "/business/product/edit/";
const BUSINESS_RECEIPT_GETALL_URL = ROOT_URL + "/business/receipt/getAll";
const BUSINESS_RECEIPT_PAID_URL = ROOT_URL + "/business/receipt/paid/";
const BUSINESS_USER_LIST_PATH = ROOT_URL + "/business/user/getAll";
const BUSINESS_PRODUCT_CREATE_URL = ROOT_URL + "/business/product/create";
const BUSINESS_MAKE_BUSINESS_URL = ROOT_URL + "/business/makeBusiness/";
const BUSINESS_REMOVE_BUSINESS_URL = ROOT_URL + "/business/removeBusiness/";
const BUSINESS_RECEIPT_REPORT_URL = ROOT_URL + "/report/receipt/";
const BUSINESS_RECEIPT_DELETE_PATH = ROOT_URL + "/business/receipt/delete/";

function handleErrors(response) {
    if (!response.ok) {
        if (response.status === 500) {
            response.json()
                .then((data) => {
                    Loading.Remove();
                    frontendUtils.notifyError(data.message)
                })
        } else if (response.status === 400) {
            response.json()
                .then((data) => {
                    Loading.Remove();
                    frontendUtils.notifyError(data.errors[0].defaultMessage)
                })
        } else if (response.status === 401 && response.statusText.length === 0) {
            response.json()
                .then(() => {
                    Loading.Remove();
                    frontendUtils.notifyError("Вашата сесия е изтекла, моля влезте в профила си отново!");
                })
        }

        if (response.status !== 403) {
            throw Error(response.status);
        }

    }

    Loading.Remove();
    return response;
}

//Заявки към backend
const REQ_POST = function (url, data) {
    Loading.Standard('Loading...');
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
    Loading.Standard('Loading...',);
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
    BUSINESS_PRODUCT_CREATE_URL,
    BUSINESS_USER_LIST_PATH,
    BUSINESS_RECEIPT_GETALL_URL,
    BUSINESS_RECEIPT_PAID_URL,
    BUSINESS_PRODUCT_EDIT_PATH,
    BUSINESS_MAKE_BUSINESS_URL,
    BUSINESS_REMOVE_BUSINESS_URL,
    BUSINESS_RECEIPT_REPORT_URL,
    BUSINESS_RECEIPT_DELETE_PATH
};
export default backend;
