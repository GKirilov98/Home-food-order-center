import React from "react";
import UserInfo from "./UserInfo";
import {Loading} from "notiflix";
import backend from "../../../utils/backendUtils";
import $ from 'jquery';
import UserRecipe from "./UserRecipe";
import {Link} from "react-router-dom";
import frontendUtils from "../../../utils/frontendUtils";
import constants from "../../../utils/constants";
import frontend from "../../../utils/frontendUtils";

export default class UserProfile extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.handleClick = this.handleClick.bind(this);
        this.state = {
            user: null,
            active: "about-tab"
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            frontendUtils.notifyError("Моля влезте в системата!");
            props.history.push(frontend.LOGIN_PATH);
        }
    }

    handleClick(event){
        let id = event.target.id;
        if (id === "about-tab"){
            $("#receipt-tab").removeClass("active")
            this.setState((prevState) => {
                return {
                    ...prevState,
                    active: "about-tab"
                }
            })
        } else {
            $("#about-tab").removeClass("active")
            this.setState((prevState) => {
                return {
                    ...prevState,
                    active: "receipt-tab"
                }
            })
        }

        event.target.classList.add("active")
    }

    componentDidMount() {
        Loading.Standard('Loading...',);
        let id = this.props.match.params.id;
        backend.REQ_GET(backend.USER_PROFILE_PATH + id)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.setState({
                    user: res[0]
                })
            })
            .catch(err => {
                    console.log(err);
                    Loading.Remove();
                    if (err.message === '401') {
                        sessionStorage.clear();
                        frontendUtils.notifyError("Вашата сесия е истекла, моля влезте отново!");
                        this.props.history.push(frontendUtils.LOGIN_PATH);
                    }
                }
            )
    }



    render() {
        let isAdmin = false;
        let array = this.props.location.pathname.split("/");
        debugger;
        if (array[array.length - 1] === sessionStorage.getItem(constants.USERNAME_KEY_NAME)) {
            isAdmin = true;
        } else {
            sessionStorage.getItem(constants.USER_ROLES_KEY_NAME)
                .split(",")
                .forEach( e => {
                    if (e.toLowerCase() === constants.ROLE_ADMIN.toLowerCase()){
                        isAdmin = true;
                    }
                })
        }


        return (
            <div className="container emp-profile">
                {this.state.user != null ? (
                    <React.Fragment>
                        <div className="row">
                            <div className="col-md-4">
                                <div className="profile-img">
                                    <img src={this.state.user.imageUrl} alt=""/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="profile-head">
                                    <ul className="nav nav-tabs" id="myTab" role="tablist">
                                        <li className="nav-item">
                                            <span className="nav-link active" id="about-tab" data-toggle="tab"
                                                  role="tab" aria-controls="home" aria-selected="true"
                                            onClick={this.handleClick}>
                                                Относно
                                            </span>
                                        </li>
                                        <li className="nav-item">
                                            <span className="nav-link " id="receipt-tab" data-toggle="tab"
                                               role="tab" aria-controls="profile" onClick={this.handleClick}
                                                  aria-selected="false">Списък с касови бележки</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            { isAdmin ? ( <div className="col-md-2">
                                <Link to={frontendUtils.USER_EDIT_PATH + this.state.user.username} className="btn btn-warning">Редакция</Link>
                            </div>):(<React.Fragment />)}

                        </div>
                        <div className="row pt-3 ml-5">
                            <div className="col">
                                <div className="tab-content profile-tab" id="myTabContent">
                                    <div className="tab-pane fade show active" id="home" role="tabpanel"
                                         aria-labelledby="home-tab">
                                        { this.state.active === "about-tab" ? (
                                            <UserInfo user={this.state.user}/>
                                        ): (
                                            <UserRecipe receipts={this.state.user.receipts}/>
                                        )

                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </React.Fragment>
                ) : (
                    <span>Loading...</span>
                )

                }

            </div>
        );
    }

}
