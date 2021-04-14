import React from "react";
import backend from "../../../utils/backendUtils";
import frontend from "../../../utils/frontendUtils";
import constants from "../../../utils/constants";
import {Link} from "react-router-dom";
import {Loading} from "notiflix";
import frontendUtils from "../../../utils/frontendUtils";


export default class Register extends React.Component {


    constructor(props, context) {
        super(props, context);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);

        this.state = {
            username: null,
            firstName: null,
            lastName: null,
            email: null,
            phoneNumber: null,
            password: null,
            confirmPassword: null,
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
            case constants.FIRST_NAME_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        firstName: value
                    }
                })
                break;
            case constants.LAST_NAME_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        lastName: value
                    }
                })
                break;
            case constants.EMAIL_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        email: value
                    }
                })
                break;
            case constants.PHONE_NUMBER_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        phoneNumber: value
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
            case constants.CONFIRM_PASSWORD_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        confirmPassword: value
                    }
                })
                break;
        }
    }


    handleSubmit(event) {
        event.preventDefault();
        Loading.Standard('Loading...',);
        let splitPhoneNumber = this.state.phoneNumber.split('');
        let firstChar = 0;
        for (const char of splitPhoneNumber) {
            if ((firstChar === 0 & char !== '0') || (char < '0') || (char > '9')) {
                debugger;
                frontend.notifyError("Phone number must start with 0 and contains only numbers!")
                return;
            }

            firstChar ++;
        }

        if (this.state.password !== this.state.confirmPassword) {
            frontend.notifyError("Password doesn't match!")
        } else {
            backend.REQ_POST(backend.REGISTER_URL, this.state)
                .then(() => {
                    Loading.Remove();
                    frontend.notifyInfo("Потребителя е регистриран!")
                    this.props.history.push(frontend.LOGIN_PATH);})
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
    }

    render() {

        return (
            <div className="signup-form container">
                <form onSubmit={this.handleSubmit}>
                    <h2>Sign UP</h2>
                    <p className="hint-text">Create your account. It's free and only takes a minute.</p>
                    <div className="form-group">
                        <div className="row">
                            <div className="col ml-1 mr-1">
                                <input type="text" className="form-control" name={constants.USERNAME_NAME}
                                       placeholder="Username"
                                       maxLength='15' minLength='6' required='required'
                                       onChange={this.handleChange}/>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <div className="row">
                            <div className="col">
                                <input type="text" className="form-control" name={constants.FIRST_NAME_NAME}
                                       placeholder="First Name" minLength='4' maxLength='30'
                                       required="required" onChange={this.handleChange}/>
                            </div>
                            <div className="col">
                                <input type="text" className="form-control" name={constants.LAST_NAME_NAME}
                                       placeholder="Last Name" minLength='4' maxLength='30'
                                       required="required" onChange={this.handleChange}/>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <div className="row">
                            <div className="col">
                                <input type="email" className="form-control" name={constants.EMAIL_NAME}
                                       placeholder="Email"
                                       required="required" onChange={this.handleChange}/>
                            </div>
                            <div className="col">
                                <input type="text" className="form-control" name={constants.PHONE_NUMBER_NAME}
                                       placeholder="Phone number"
                                       minLength='10' maxLength='10' required="required" onChange={this.handleChange}/>
                            </div>
                        </div>
                    </div>


                    <div className="form-group">
                        <div className="row">
                            <div className="col">
                                <input type="password" className="form-control" name={constants.PASSWORD_NAME}
                                       placeholder="Password" minLength='6' maxLength='20'
                                       required="required" onChange={this.handleChange}/>
                            </div>
                            <div className="col">
                                <input type="password" className="form-control" name={constants.CONFIRM_PASSWORD_NAME}
                                       placeholder="Confirm Password" minLength='6' maxLength='20'
                                       required="required" onChange={this.handleChange}/>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <label className="form-check-label">
                            <div className="row">
                                <div className="col-2">
                                    <input type="checkbox" required="required"/>
                                </div>
                                <div className="col text-dark">
                                    I accept the <span className="w3-text-green w3-hover-blue">Terms of Use</span> &amp;
                                    <span className="w3-text-green w3-hover-blue">Privacy Policy</span>
                                </div>
                            </div>
                        </label>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-success btn-lg btn-">Sign Up</button>
                    </div>
                </form>
                <div className="text-center form-control text-dark">Already have an account?
                    <Link to={frontend.LOGIN_PATH} className="w3-hover-text-green text-info">Sign in</Link>
                </div>
            </div>
        );
    }


}
