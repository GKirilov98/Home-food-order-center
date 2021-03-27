import React from "react";
import UserInfo from "../Profile/UserInfo";

export default class UserEdit extends React.Component{
    render() {
        return (
            <div className="container emp-profile">

                    <div className="row">
                        <div className="col-md-6">
                            <div className="profile-img">
                                <img
                                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
                                    alt=""/>
                                <div className="file btn btn-lg btn-primary">
                                    Change Photo
                                    <input type="file" name="file"/>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="profile-head">
                                <ul className="nav nav-tabs" id="myTab" role="tablist">
                                    <li className="nav-item">
                                        <label className="nav-link" id="home-tab" data-toggle="tab" 
                                           role="tab" aria-controls="home" aria-selected="true">About</label>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <form action="">
                        <div className="row">
                            <div className="col">
                                <div className="tab-content profile-tab" id="myTabContent">
                                    <div className="tab-pane fade show active" id="home" role="tabpanel"
                                         aria-labelledby="home-tab">
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
                                                <label>First Name</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="Kshiti "/>
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-md-6">
                                                <label>Last Name</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue=" Ghelani"/>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-md-6">
                                                <label>Email</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="kshitighelani@gmail.com" />
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="123 456 7890" />
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-md-6">
                                                <label>Adress</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="123 456 7890"/>
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col">
                                                <label> CARD INFO</label>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-cm-3 radio-group mr-4">
                                                <div className="row justify-content-center">
                                                    <div className="row d-flex px-3 mr-3 radio">
                                                        <img className="pay" src="https://i.imgur.com/WIAP9Ku.jpg"/>
                                                        <p className="my-auto">Credit Card</p>
                                                    </div>
                                                    <div className="row d-flex px-3 mr-3 radio gray">
                                                        <img className="pay" src="https://i.imgur.com/OdxcctP.jpg"/>
                                                        <p className="my-auto">Debit Card</p>
                                                    </div>
                                                    <div className="row d-flex px-3 radio gray mb-3">
                                                        <img className="pay" src="https://i.imgur.com/cMk1MtK.jpg"/>
                                                        <p className="my-auto">PayPal</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-md-6">
                                                <label >Name Card</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="123 456 7890"/>
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-md-6">
                                                <label >Card Number</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="123 456 7890"/>
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-md-6">
                                                <label >Expiration Date</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="123 456 7890"/>
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-md-6">
                                                <label >CVV</label>
                                            </div>
                                            <div className="col-md-6">
                                                <input className="mt-0" defaultValue="123 456 7890"/>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <button type="submit" className="btn btn-info mt-3"> Save</button>
                    </form>
            </div>
        );
    }
}
