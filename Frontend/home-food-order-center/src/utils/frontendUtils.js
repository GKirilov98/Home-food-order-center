import {Notify} from "notiflix";

const LOGIN_PATH = "/auth/login";
const REGISTER_PATH = "/auth/register";
const CATALOG_PATH = "/product/listAl";

const notifyError = function (message) {
    Notify.Failure(message,
        {
            timeout: 3000,
            clickToClose: true,
        });
}

const notifyInfo = function (message) {
    Notify.Success(message,
        {
            timeout: 3000,
            clickToClose: true,
        });
}

export default {
    notifyInfo,
    notifyError,
    LOGIN_PATH,
    REGISTER_PATH,
    CATALOG_PATH
}
