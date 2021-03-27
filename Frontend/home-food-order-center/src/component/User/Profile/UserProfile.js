import React from "react";
import '../../../style/css/User/user-profile.css';
import UserInfo from "./UserInfo";
import UserRecipe from "./UserRecipe";

export default class UserProfile extends React.Component {
    render() {
        return (
            <div className="container emp-profile">
                <div className="row">
                    <div className="col-md-4">
                        <div className="profile-img">
                            <img
                                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
                                alt=""/>
                            {/*<div className="file btn btn-lg btn-primary">*/}
                            {/*    Change Photo*/}
                            {/*    <input type="file" name="file"/>*/}
                            {/*</div>*/}
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="profile-head">
                            <h5>
                                Kshiti Ghelani
                            </h5>
                            <ul className="nav nav-tabs" id="myTab" role="tablist">
                                <li className="nav-item">
                                    <a className="nav-link" id="home-tab" data-toggle="tab" href="#home"
                                       role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link active" id="profile-tab" data-toggle="tab" href="#profile"
                                       role="tab" aria-controls="profile" aria-selected="false">Recipes</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div className="col-md-2">
                        <a className="btn btn-warning">Edit</a>
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <div className="tab-content profile-tab" id="myTabContent">
                            <div className="tab-pane fade show active" id="home" role="tabpanel"
                                 aria-labelledby="home-tab">
                                {/*if (true) */}
                                <UserInfo/>
                                {/*else*/}
                                {/*<UserRecipe/>*/}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
