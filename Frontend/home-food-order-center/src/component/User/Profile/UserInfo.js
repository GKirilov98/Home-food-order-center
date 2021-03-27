import Root from "react";
import React from "react";
import UserCardInfo from "./UserCardInfo";

export default class UserInfo extends Root.Component {
    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Username</label>
                    </div>
                    <div className="col-md-6 mt-0">
                        <p className="mt-0">Kshiti123</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Name</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">Kshiti Ghelani</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Email</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">kshitighelani@gmail.com</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Phone</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">123 456 7890</p>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <label>Adress</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">123 456 7890</p>
                    </div>
                </div>


                <UserCardInfo/>

            </div>
        );
    }

}
