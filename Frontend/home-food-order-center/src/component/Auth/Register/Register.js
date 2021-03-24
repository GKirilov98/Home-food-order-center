import React from "react";
import '../../../style/css/Auth/Register/register.css';

export default class Register extends React.Component {
    render() {
        return (
            <div className="signup-form">
                <form method="post">
                    <h2>Sign UP</h2>
                    <p className="hint-text">Create your account. It's free and only takes a minute.</p>
                    <div className="form-group">
                        <div className="row">
                            <div className="col">
                                <input type="text" className="form-control" name="first_name" placeholder="First Name"
                                       required="required"/>
                            </div>
                            <div className="col"><input type="text" className="form-control" name="last_name"
                                                        placeholder="Last Name" required="required"/>
                            </div>
                        </div>
                    </div>
                    <div className="form-group">
                        <input type="email" className="form-control" name="email" placeholder="Email"
                               required="required"/>
                    </div>
                    <div className="form-group">
                        <input type="password" className="form-control" name="password" placeholder="Password"
                               required="required"/>
                    </div>
                    <div className="form-group">
                        <input type="password" className="form-control" name="confirm_password"
                               placeholder="Confirm Password" required="required"/>
                    </div>
                    <div className="form-group">
                        <label className="form-check-label">
                            <div className="row">
                                <div className="col-2">
                                    <input type="checkbox" required="required"/>
                                </div>
                                <div className="col text-dark">
                                    I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a>
                                </div>
                            </div>
                        </label>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-success btn-lg btn-">Sign Up</button>
                    </div>
                </form>
                <div className="text-center form-control text-dark">Already have an account?
                    <a href="#" className="w3-hover-text-green text-info">Sign in</a>
                </div>
            </div>
        );
    }
}
