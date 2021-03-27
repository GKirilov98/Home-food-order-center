import React from "react";
import '../../../style/css/Auth/Login/login.css';
import {Link} from 'react-router-dom';
import * as Icon from 'react-bootstrap-icons';
import backend from "../../../utils/backendUtils";
import constants from "../../../utils/constants";
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
        debugger
        event.preventDefault();
            debugger
        backend.REQ_POST(backend.LOGIN_URL, this.state)
            .then(data => data.json())
            .then((data) => {
                sessionStorage.setItem(constants.TOKEN_KEY_NAME, data[0].token)
                frontendUtils.notifyInfo("Login Success!")
                debugger;
                    // this.props.history.push(frontend.CATALOG_PATH);
                }
            )
    }

    render() {
        return (

            <div className="container form-control text-dark login-form">
                <form onSubmit={this.handleSubmit}>
                    <h1>Вход</h1>
                    <input type="text" placeholder="Username" name={constants.USERNAME_NAME}
                           maxLength='15' minLength='6' required='required'
                           onChange={this.handleChange}/>
                    <input type="password" placeholder="Password"
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

                    <Link to="todo" className=" w3-hover-border-blue">Забравена парола?</Link>
                    <button type="submit" className="login-button">Sign in</button>
                </form>
            </div>

        );
    }
}
