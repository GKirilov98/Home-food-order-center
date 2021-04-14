import {Notify} from "notiflix";

const HOME_PATH = "/home";
const ABOUT_US = "/about";
const LOGIN_PATH = "/auth/login";
const REGISTER_PATH = "/auth/register";
const CATALOG_PATH = "/catalog";
const PRODUCT_DETAILS_PATH = "/product/details/"
const CART_VIEW_PATH = "/cart/view";
const RECEIPT_VIEW_PATH = "/receipt/view/";

const USER_PROFILE_PATH = "/user/profile/";
const USER_EDIT_PATH = "/user/edit/";

const ADMIN_CREATE_PRODUCT_PATH = "/admin/product/create";
const ADMIN_USER_LIST_PATH = "/admin/user/listAll";
const ADMIN_RECEIPT_LIST_PATH = "/admin/receipt/listAll";
const ADMIN_PRODUCT_EDIT_PATH = "/admin/product/edit/";




//TODO...
const ADMIN_PRODUCT_LIST_PATH = "/admin/product/listAll";

const notifyError = function (message) {
    Notify.Failure(message,
        {
            timeout: 2000,
            clickToClose: true,
        });
}

const notifyInfo = function (message) {
    Notify.Success(message,
        {
            timeout: 2000,
            clickToClose: true,
        });
}

export default {
    HOME_PATH,
    ADMIN_USER_LIST_PATH,
    ADMIN_CREATE_PRODUCT_PATH,
    ADMIN_RECEIPT_LIST_PATH,
    RECEIPT_VIEW_PATH,
    CART_VIEW_PATH,
    PRODUCT_DETAILS_PATH,
    notifyInfo,
    notifyError,
    LOGIN_PATH,
    REGISTER_PATH,
    CATALOG_PATH,
    USER_PROFILE_PATH,
    USER_EDIT_PATH,
    ADMIN_PRODUCT_EDIT_PATH,
    ABOUT_US,
}
