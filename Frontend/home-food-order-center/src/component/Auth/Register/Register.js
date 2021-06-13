import React from "react";
import backend from "../../../utils/backendUtils";
import frontend from "../../../utils/frontendUtils";
import constants from "../../../utils/constants";
import {Link} from "react-router-dom";
import {Loading} from "notiflix";
import frontendUtils from "../../../utils/frontendUtils";
import messagesUi from "../../../utils/messages-ui";


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
            if ((firstChar === 0 && char !== '0') || (char < '0') || (char > '9') || splitPhoneNumber.length !== 10) {
                frontend.notifyError(messagesUi.PHONE_NUMBER)
                Loading.Remove();
                return;
            }

            firstChar ++;
        }

        if (this.state.password !== this.state.confirmPassword) {
            frontend.notifyError(messagesUi.PASSWORD_NOT_MATCH)
            Loading.Remove();
        } else {
            backend.REQ_POST(backend.REGISTER_URL, this.state)
                .then(() => {
                    Loading.Remove();
                    frontend.notifyInfo(messagesUi.REGISTER_USER_SUCCESS)
                    this.props.history.push(frontend.LOGIN_PATH);})
                .catch(err => {
                        console.log(err);
                        Loading.Remove();
                        if (err.message === '401') {
                            sessionStorage.clear();
                            frontendUtils.notifyError(messagesUi.INVALID_SESSION);
                            this.props.history.push(frontendUtils.LOGIN_PATH);
                        }
                    }
                )
        }
    }

    render() {

        return (
            <div className="signup-form container">
                <form onSubmit={this.handleSubmit} className="text-center">
                    <h2>Регистрация</h2>
                    <p className="hint-text">Създайте своя акант. Безплатно е и е нужно по-малко от минута</p>
                    <div className="form-group">
                        <div className="row">
                            <div className="col ml-1 mr-1">
                                <input type="text" className="form-control" name={constants.USERNAME_NAME}
                                       placeholder="Потребителско име"
                                       maxLength='15' minLength='6' required='required'
                                       onChange={this.handleChange}/>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <div className="row">
                            <div className="col">
                                <input type="text" className="form-control" name={constants.FIRST_NAME_NAME}
                                       placeholder="Първо име" minLength='4' maxLength='30'
                                       required="required" onChange={this.handleChange}/>
                            </div>
                            <div className="col">
                                <input type="text" className="form-control" name={constants.LAST_NAME_NAME}
                                       placeholder="Фамилия" minLength='4' maxLength='30'
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
                                       placeholder="Тел. номер"
                                       minLength='10' maxLength='10' required="required" onChange={this.handleChange}/>
                            </div>
                        </div>
                    </div>


                    <div className="form-group">
                        <div className="row">
                            <div className="col">
                                <input type="password" className="form-control" name={constants.PASSWORD_NAME}
                                       placeholder="Парола" minLength='6' maxLength='20'
                                       required="required" onChange={this.handleChange}/>
                            </div>
                            <div className="col">
                                <input type="password" className="form-control" name={constants.CONFIRM_PASSWORD_NAME}
                                       placeholder="Повтори парола" minLength='6' maxLength='20'
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
                        <button type="submit" className="btn btn-success btn-lg btn-">Регистрирай</button>
                    </div>
                </form>
                <div className="text-center form-control text-dark">Вече имате акаунт?
                    <Link to={frontend.LOGIN_PATH} className="w3-hover-text-green text-info">Вход</Link>
                </div>
            </div>
        );
    }


}
