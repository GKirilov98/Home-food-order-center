import React from "react";
import '../../../style/css/Auth/Login/login.css';
import {Link} from 'react-router-dom';
import {faFacebook, faGooglePlusG} from "@fortawesome/free-brands-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export default class Login extends React.Component {
    render() {
        return (
            <div>
                <div className="container" id="container">
                    <div className="form-container sign-in-container">
                        <form action="#">
                            <h1>Вход</h1>
                            <input type="email" placeholder="Email"/>
                            <input type="password" placeholder="Password"/>
                            <span>или  </span>
                            <div className="social-container">
                                <div className="social-container">
                                    <Link to="#" className="social">
                                        <FontAwesomeIcon className="facebook-icon" icon={faFacebook} size="2x"/>
                                    </Link>
                                    <Link to="#" className="social">
                                        <FontAwesomeIcon className="google-plus-g-icon" icon={faGooglePlusG} size="2x"/>
                                    </Link>
                                </div>
                            </div>
                            <Link to="#">Забравена парола?</Link>
                            <button>Sign In</button>
                        </form>
                    </div>
                    <div className="overlay-container">
                        <div className="overlay">
                            <div className="overlay-panel overlay-left">
                                <h1>Welcome Back!</h1>
                                <p>To keep connected with us please login with your personal info</p>
                                <button className="ghost" id="signIn">Sign In</button>
                            </div>
                            <div className="overlay-panel overlay-right">
                                <h1>Hello, Friend!</h1>
                                <p>Enter your personal details and start journey with us</p>
                                <button className="ghost" id="signUp">Sign Up</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
