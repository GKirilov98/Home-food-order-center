import React from "react";
import * as Icon from 'react-bootstrap-icons';
import {Link} from "react-router-dom";
import $ from "jquery";
import constants from "../../utils/constants";
import frontendUtils from "../../utils/frontendUtils";
import {Loading} from "notiflix";
import backendUtils from "../../utils/backendUtils";


export default class Header extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.handleClickToggleSideBar = this.handleClickToggleSideBar.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
        // this.state = {
        //     isCatalog: false
        // }
    }


    handleLogout() {
        Loading.Standard('Loading...',);
        backendUtils.REQ_GET(backendUtils.LOGOUT_URL)
            .then(res => res.json())
            .then(() => {
                frontendUtils.notifyInfo("Излязохте успешно!")
                Loading.Remove();
                sessionStorage.clear();
                this.props.history.push("/")
            })

    }


    handleClickToggleSideBar() {
        $('#sidebar, #content').toggleClass('active');
        $('.collapse.in').toggleClass('in');
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    }


    render() {
        return (
            // <!-- Navigation -->
            <div >
                <div className="navbar navbar-expand-md navbar-dark bg-dark mb-0">
                    {/*<div className="container ">*/}
                    <div>
                        <Link className="navbar-brand" to={frontendUtils.CATALOG_PATH}>Храна за вкъщи</Link>
                    </div>

                    {/*<button type="button" id="sidebarCollapse" className="btn btn-info"*/}
                    {/*        onClick={this.handleClickToggleSideBar}>*/}
                    {/*    <Icon.LayoutSidebarInset/>*/}
                    {/*    <span>Toggle Sidebar</span>*/}
                    {/*</button>*/}
                    <button className="btn btn-dark d-inline-block d-lg-none ml-auto" type="button"
                            data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation">
                    </button>


                    <div className=" navbar-collapse offset-1" id="navbarResponsive">
                        <ul className="navbar-nav ml-auto ">
                            <li className="nav-item">
                                <Link to={frontendUtils.ABOUT_US} className="nav-link">
                                            <span className="pr-2">
                                                <Icon.Book size="20"/>
                                            </span>
                                    About Us
                                </Link>
                            </li>
                            {sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null ? (
                                <React.Fragment>
                                    <li className="nav-item">
                                        <Link to="/auth/register" className="nav-link">
                                            <span className="pr-2">
                                                <Icon.PersonPlus size="20"/>
                                            </span>
                                            Регистрация
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link to="/auth/login" className="nav-link">
                                     <span className="pr-2">
                                         <Icon.PersonCheck size="20"/>
                                    </span>
                                            Вход</Link>
                                    </li>
                                </React.Fragment>
                            ) : (
                                <React.Fragment>
                                    {
                                        sessionStorage.getItem('roles').split(',').map((role) => {
                                            return <React.Fragment>
                                                {role === constants.ROLE_ADMIN ? (
                                                    <React.Fragment>
                                                        <li className="nav-item">
                                                            <Link to={frontendUtils.ADMIN_USER_LIST_PATH}
                                                                  className="nav-link">
                                                                Потребители
                                                            </Link>
                                                        </li>
                                                        <li className="nav-item">
                                                            <Link to={frontendUtils.ADMIN_RECEIPT_LIST_PATH}
                                                                  className="nav-link">
                                                                Касови бележки
                                                            </Link>
                                                        </li>
                                                        <li className="nav-item">
                                                            <Link to={frontendUtils.ADMIN_CREATE_PRODUCT_PATH}
                                                                  className="nav-link">
                                                                Създай продукт
                                                            </Link>
                                                        </li>
                                                    </React.Fragment>
                                                ) : (
                                                    <React.Fragment/>
                                                )}
                                            </React.Fragment>
                                        })
                                    }
                                    <li className="nav-item">

                                        <Link
                                            to={frontendUtils.USER_PROFILE_PATH +
                                            // sessionStorage.getItem(constants.ID_KEY_NAME)}
                                            sessionStorage.getItem(constants.USERNAME_KEY_NAME)}
                                            className="nav-link">
                                            <span className="pr-2">
                                             <Icon.PersonCircle size="20"/>
                                         </span>
                                            Профил</Link>
                                    </li>
                                    <li className="nav-item">

                                        <Link to={frontendUtils.CART_VIEW_PATH} className="nav-link">
                                             <span className="pr-2">
                                             <Icon.CartCheck size="20"/>
                                         </span>
                                            Количка</Link>
                                    </li>
                                    <li className="nav-item" onClick={this.handleLogout}>
                                    <span className="pr-2 nav-link">
                                        <Icon.BoxArrowRight size="20"/>
                                        Излез
                                    </span>
                                    </li>

                                </React.Fragment>
                            )

                            }

                        </ul>
                    </div>
                    {/*</div>*/}
                </div>


                {/*<nav className="navbar navbar-expand-lg navbar-dark bg-dark pb-3">*/}
                {/*    /!*<div className="container ">*!/*/}
                {/*    <div>*/}
                {/*        <a className="navbar-brand" href="#">Start Bootstrap</a>*/}
                {/*        <button className="navbar-toggler" type="button" data-toggle="collapse"*/}
                {/*                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"*/}
                {/*                aria-label="Toggle navigation">*/}
                {/*            <span className="navbar-toggler-icon"/>*/}
                {/*        </button>*/}
                {/*    </div>*/}


                {/*    <div className="collapse navbar-collapse offset-1" id="navbarResponsive">*/}
                {/*        <ul className="navbar-nav ml-auto ">*/}
                {/*            <li className="nav-item">*/}
                {/*                <Link to="/auth/register" className="nav-link">*/}
                {/*                    <span className="pr-2">*/}
                {/*                         <Icon.PersonPlus size="20"/>*/}
                {/*                    </span>*/}
                {/*                    Sign Up*/}
                {/*                </Link>*/}
                {/*            </li>*/}
                {/*            <li className="nav-item">*/}
                {/*                <Link to="/auth/login" className="nav-link">*/}
                {/*                     <span className="pr-2">*/}
                {/*                         <Icon.PersonCheck size="20"/>*/}
                {/*                    </span>*/}
                {/*                    Sign In</Link>*/}
                {/*            </li>*/}
                {/*        </ul>*/}
                {/*    </div>*/}
                {/*    /!*</div>*!/*/}
                {/*</nav>*/}

            </div>

        );
    }
}
