import React from "react";
import {Link} from 'react-router-dom';
import * as Icon from 'react-bootstrap-icons';
import backend from "../../../utils/backendUtils";
import constants from "../../../utils/constants";
import frontend from "../../../utils/frontendUtils";
import {Loading} from "notiflix";
import frontendUtils from "../../../utils/frontendUtils";

export default class Login extends React.Component {


    constructor(props, context) {
        super(props, context);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            username: null,
            password: null
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) != null) {
            props.history.push(frontend.CATALOG_PATH);
        }
    }

    handleChange(event) {
        let nameTarget = event.target.name;
        let value = event.target.value;
        switch (nameTarget) {
            case constants.USERNAME_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        username: value
                    }
                })
                break;
            case constants.PASSWORD_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        password: value
                    }
                })
                break;
        }
    }


    handleSubmit(event) {
        event.preventDefault();
        Loading.Standard('Loading...',);
        backend.REQ_POST(backend.LOGIN_URL, this.state)
            .then(data => data.json())
            .then((data) => {
                sessionStorage.setItem(constants.TOKEN_KEY_NAME, data[0].accessToken)
                sessionStorage.setItem(constants.USER_ROLES_KEY_NAME, data[0].roles)
                sessionStorage.setItem(constants.USERNAME_KEY_NAME, data[0].username)
                sessionStorage.setItem(constants.ID_KEY_NAME, data[0].id)
                Loading.Remove();
                frontend.notifyInfo("Влязохте успешно!")
                this.props.history.push(frontend.CATALOG_PATH);
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
        return (

            <div className="container form-control text-dark login-form login-container">
                <form onSubmit={this.handleSubmit} className="text-center">
                    <h1>Вход</h1>
                    <input className="form-control p-3 mb-3"
                           type="text" placeholder="Потребителско име" name={constants.USERNAME_NAME}
                           maxLength='15' minLength='6' required='required'
                           onChange={this.handleChange}/>
                    <input className="form-control p-3 mb-3" type="password" placeholder="Парола"
                           name={constants.PASSWORD_NAME}
                           minLength='6' maxLength='20'
                           required="required" onChange={this.handleChange}/>
                    <span>или  </span>
                    <div className="social-container">
                        <div className="social-container">
                            <Link to="#" className="social">
                                <Icon.Facebook color="blue" size="30"/>
                            </Link>
                            <Link to="#" className="social">
                                <Icon.Google color="red" size="30"/>
                            </Link>
                        </div>
                    </div>

                    <div className="col">
                        <Link to="todo" className=" w3-hover-border-blue">Забравена парола?</Link>
                    </div>
                    <div className="col">
                        <button type="submit" className="login-button">Влез</button>
                    </div>

                </form>
            </div>

        );
    }
}
