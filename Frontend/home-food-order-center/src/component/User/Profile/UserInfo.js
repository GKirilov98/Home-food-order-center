import Root from "react";
import React from "react";


export default class UserInfo extends Root.Component {

    constructor(props, context) {
        super(props, context);
    }


    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Потребител</label>
                    </div>
                    <div className="col-md-6 mt-0">
                        <p className="mt-0">{this.props.user.username}</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Име</label>
                    </div>
                    <div className="col-md-6 mt-0">
                        <p className="mt-0">{this.props.user.firstName}</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Фамилия</label>
                    </div>
                    <div className="col-md-6 mt-0">
                        <p className="mt-0">{this.props.user.lastName}</p>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <label>Email</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">{this.props.user.email}</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <label>Телефон за връзка:</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">{this.props.user.phoneNumber}</p>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <label>Дата на регистрация</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">{
                             this.props.user.dateRegistration.split("T")[0] +
                                    " " + this.props.user.dateRegistration.split("T")[1].split(".")[0]
                        }ч.</p>
                    </div>
                </div>


                {/*<UserCardInfo/>*/}

            </div>
        );
    }

}
