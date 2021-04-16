import './App.css';
import './style/css/common/bootstrap.min.css';
import './style/css/common/w3.css';
import './style/css/User/user-profile.css';
import './style/css/Admin/product-create.css';
import './style/css/style2.css'
import './style/css/Admin/UserList/admin-user-list.css';
import './style/css/Auth/Login/login.css';
import './style/css/Auth/Register/register.css';
import './style/css/Cart/cart.css';
import './style/css/Error/404-not-found.css';
import './style/css/Home/home.css';
import './style/css/Payment/payment.css';
import './style/css/Recept/receipt-view.css';

import {Route,  Switch} from "react-router-dom";

import Catalog from "./component/Product/ListAll/Catalog";
import Footer from "./component/common/Footer";
import Login from "./component/Auth/Login/Login";
import Register from "./component/Auth/Register/Register";
import Home from "./component/Home/Home";
import ProductCreate from "./component/Admin/ProductCreate/ProductCreate";
import ProductDetails from "./component/Product/Details/ProductDetails";
import frontendUtils from "./utils/frontendUtils";
import Cart from "./component/Cart/Cart";
import ReceiptView from "./component/Receipt/ReceiptView";
import Header from "./component/common/Header";
import UserProfile from "./component/User/Profile/UserProfile";
import UserEdit from "./component/User/Edit/UserEdit";
import React from "react";
import AdminUserList from "./component/Admin/UserList/AdminUserList";
import AdminRecipeList from "./component/Admin/RecipeList/AdminRecipeList";
import AdminProductEdit from "./component/Admin/ProductEdit/AdminProductEdit";
import AboutUs from "./component/About/AboutUs";
import GenericNotFound from "./component/Error/GenericNotFound";


export default class App extends React.Component{
        render() {
                return (
                    <React.Fragment>
                        <Route path="/" component={Header} />
                        <Switch>
                            <Route path="/" exact component={Home}/>
                            <Route path="/home" exact component={Home}/>

                            <Route path={frontendUtils.ADMIN_CREATE_PRODUCT_PATH} exact component={ProductCreate}/>
                            <Route path={frontendUtils.ADMIN_USER_LIST_PATH} exact component={AdminUserList} />
                            <Route path={frontendUtils.ADMIN_RECEIPT_LIST_PATH} exact component={AdminRecipeList} />
                            <Route path={frontendUtils.ADMIN_PRODUCT_EDIT_PATH + ":id"} exact component={AdminProductEdit} />

                            <Route path={frontendUtils.PRODUCT_DETAILS_PATH + ":id"} exact component={ProductDetails}/>
                            <Route path={frontendUtils.CART_VIEW_PATH} exact component={Cart}/>
                            <Route path={frontendUtils.REGISTER_PATH} exact component={Register}/>
                            <Route path={frontendUtils.LOGIN_PATH} exact component={Login}/>
                            <Route path={frontendUtils.CATALOG_PATH} exact component={Catalog}/>
                            <Route path={frontendUtils.RECEIPT_VIEW_PATH +":id"} exact component={ReceiptView} />
                            <Route path={frontendUtils.USER_PROFILE_PATH + ":id"} exact component={UserProfile} />
                            <Route path={frontendUtils.USER_EDIT_PATH + ":id"} exact component={UserEdit} />
                            <Route path={frontendUtils.ABOUT_US} exact component={AboutUs} />
                            <Route component={GenericNotFound} />
                        </Switch>
                        <Footer/>
                    </React.Fragment>
                );
        }
}
