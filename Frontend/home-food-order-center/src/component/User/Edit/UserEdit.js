import React from "react";
import {Confirm, Loading} from "notiflix";
import backend from "../../../utils/backendUtils";
import constants from "../../../utils/constants";
import frontendUtils from "../../../utils/frontendUtils";
import frontend from "../../../utils/frontendUtils";

export default class UserEdit extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
        this.state = {
            user: null,
            imgSrc: null,
            imgPublicId: null,
            firstName: null,
            lastName: null,
            email: null,
            phoneNumber: null
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            frontendUtils.notifyError("Моля влезте в системата!");
            props.history.push(frontend.LOGIN_PATH);
        } else if (sessionStorage.getItem(constants.ID_KEY_NAME) != props.match.params.id){
            let isAdmin = false
            sessionStorage.getItem(constants.USER_ROLES_KEY_NAME).split(",")
                .forEach((role) => {if (role == constants.ROLE_ADMIN){
                    isAdmin = true;
                }} )
            if (!isAdmin){
                frontendUtils.notifyError("Нямате необходимите права за достап!");
                props.history.push(frontendUtils.CATALOG_PATH);
            }
        }


    }

    handleDelete(event) {
        Confirm.Show( '!!! WARNING !!!', 'Сигурни ли сте, че искате да изтриете този профил?',
            'Не', 'Да',
            () => "",
            () => {
            backend.REQ_POST(backend.ADMIN_USER_DELETE_PATH + this.props.match.params.id)
                .then(() => {
                    let isAdmin = false;
                    for (const string of sessionStorage.getItem(constants.USER_ROLES_KEY_NAME).split(",")) {
                        if (string === constants.ROLE_ADMIN){
                            isAdmin = true;
                            break
                        }
                    }

                    frontendUtils.notifyInfo("Потребителя е изтрит успешно!");
                    if (isAdmin){
                        this.props.history.push(frontendUtils.ADMIN_USER_LIST_PATH)
                    } else {
                        sessionStorage.clear();
                        this.props.history.push(frontendUtils.HOME_PATH)
                    }
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
            } );
    }

    handleChange(event) {
        let nameTarget = event.target.id;
        let value = event.target.value;
        switch (nameTarget) {
            case constants.IMAGE_NAME:
                debugger
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        imgSrc: event.target.files[0],
                        imgPublicId: "new",
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
        }
    }
    handleSubmit(event) {
        Loading.Standard('Loading...',);
        event.preventDefault();
        if (this.state.user.imagePublicId !== this.state.imgPublicId) {
            const formData = new FormData()
            formData.append("file", this.state.imgSrc)
            fetch(backend.IMAGE_CREATE_URL, {
                method: "POST", // *GET, POST, PUT, DELETE, etc.
                mode: "cors", // no-cors, cors, *same-origin
                headers: {
                    'Authorization': 'Bearer ' + sessionStorage.getItem(constants.TOKEN_KEY_NAME),
                },
                body: formData
            })
                .then(backend.handleErrors)
                .then(res => res.json())
                .then(res => {
                    this.setState((prevState) => {
                        return {
                            ...prevState,
                            imgSrc: res.url,
                            imgPublicId: res.public_id,
                        };
                    })})
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
                .then(() => this.editUser())
        } else {
            this.editUser()
        }
    }

    editUser() {
        const objBody = {
            imgSrc: this.state.imgSrc,
            imgPublicId: this.state.imgPublicId,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            phoneNumber: this.state.phoneNumber
        }
        backend.REQ_POST(backend.USER_EDIT_PATH + this.state.user.username, objBody)
            .then(res => res.json())
            .then(res => {
                frontendUtils.notifyInfo("Потребителя е променен успешно!")
                this.setState({
                    user: res[0],
                    imgSrc: res[0].imageUrl,
                    firstName: res[0].firstName,
                    lastName: res[0].lastName,
                    email: res[0].email,
                    phoneNumber: res[0].phoneNumber
                })
                Loading.Remove();
            })
            .catch(err => {
                    console.log(err);
                    Loading.Remove();
                    if (err.message === '401') {
                        sessionStorage.clear();
                        frontendUtils.notifyError("Вашата сесия е истекла или нямате необходимите права, моля влезте отново!");
                        this.props.history.push(frontendUtils.LOGIN_PATH);
                    }
                }
            )
    }
    componentDidMount() {
        Loading.Standard('Loading...',);
        let id = this.props.match.params.id;
        backend.REQ_GET(backend.USER_PROFILE_PATH + id)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.setState({
                    user: res[0],
                    imgSrc: res[0].imageUrl,
                    imgPublicId: res[0].imagePublicId,
                    firstName: res[0].firstName,
                    lastName: res[0].lastName,
                    email: res[0].email,
                    phoneNumber: res[0].phoneNumber
                })
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
            <div className="container emp-profile">
                {
                    this.state.user != null ? (
                        <React.Fragment>
                            <form onSubmit={this.handleSubmit}>
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="profile-img">
                                            <img src={this.state.imgSrc} alt=""/>
                                            <div className="file btn btn-lg btn-primary">
                                                Change Photo
                                                <input id={constants.IMAGE_NAME} type="file" name="file"
                                                       onChange={this.handleChange}/>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="profile-head">
                                            <ul className="nav nav-tabs" id="myTab" role="tablist">
                                                <li className="nav-item">
                                                    <label className="nav-link" id="home-tab" data-toggle="tab"
                                                           role="tab" aria-controls="home"
                                                           aria-selected="true">About</label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col">
                                        <div className="tab-content profile-tab" id="myTabContent">
                                            <div className="tab-pane fade show active" id="home" role="tabpanel"
                                                 aria-labelledby="home-tab">
                                                <div className="row mb-1">
                                                    <div className="col-md-6">
                                                        <label>Потребителско име</label>
                                                    </div>
                                                    <div className="col-md-6 mt-0">
                                                        <p className="mt-0">{this.state.user.username}</p>
                                                    </div>
                                                </div>
                                                <div className="row mb-3">
                                                    <div className="col-md-6">
                                                        <label htmlFor={constants.FIRST_NAME_NAME}>Име</label>
                                                    </div>
                                                    <div className="col-md-6">
                                                        <input className="mt-0" id={constants.FIRST_NAME_NAME}
                                                               defaultValue={this.state.firstName}
                                                               onChange={this.handleChange}/>
                                                    </div>
                                                </div>

                                                <div className="row mb-3">
                                                    <div className="col-md-6">
                                                        <label htmlFor={constants.LAST_NAME_NAME}>Фамилия</label>
                                                    </div>
                                                    <div className="col-md-6">
                                                        <input onChange={this.handleChange} className="mt-0"
                                                               id={constants.LAST_NAME_NAME}
                                                               defaultValue={this.state.lastName}/>
                                                    </div>
                                                </div>
                                                <div className="row mb-3">
                                                    <div className="col-md-6">
                                                        <label htmlFor={constants.EMAIL_NAME}>Email</label>
                                                    </div>
                                                    <div className="col-md-6">
                                                        <input onChange={this.handleChange} type="email"
                                                               className="mt-0" id={constants.EMAIL_NAME}
                                                               defaultValue={this.state.email}/>
                                                    </div>
                                                </div>
                                                <div className="row mb-3">
                                                    <div className="col-md-6">
                                                        <label htmlFor={constants.PHONE_NUMBER_NAME}>Телефонен
                                                            номер</label>
                                                    </div>
                                                    <div className="col-md-6">
                                                        <input onChange={this.handleChange} type="text" className="mt-0"
                                                               id={constants.PHONE_NUMBER_NAME}
                                                               defaultValue={this.state.phoneNumber}
                                                               minLength="10" maxLength="10"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <button type="button" className="btn btn-danger mt-3" onClick={this.handleDelete}>Delete</button>


                                <button type="submit" className="btn btn-info mt-3  float-right ">
                                    Save
                                </button>

                            </form>


                        </React.Fragment>
                    ) : (
                        <span>Loading...</span>
                    )
                }


            </div>
        );
    }
}
