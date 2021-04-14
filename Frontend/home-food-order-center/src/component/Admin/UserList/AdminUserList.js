import React from "react";
import backend from "../../../utils/backendUtils";
import {Loading} from "notiflix";
import constants from "../../../utils/constants";
import {Link} from "react-router-dom";
import frontendUtils from "../../../utils/frontendUtils";
import frontend from "../../../utils/frontendUtils";

export default class AdminUserList extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.loadUsers = this.loadUsers.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            users: null,
            username: null,
            email: null,
            orderBy: null,
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            props.history.push(frontend.LOGIN_PATH);
        }else {
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

    handleClick(event){
        Loading.Standard('Loading...',);
        let idTarget = event.target.id;
        let nameTarget = event.target.name;
        debugger;
        switch (nameTarget){
            case "remove-admin":
                backend.REQ_POST(backend.ADMIN_REMOVE_ADMIN_URL + idTarget)
                    .then(this.loadUsers)
                break
            case "make-admin":
                backend.REQ_POST(backend.ADMIN_MAKE_ADMIN_URL + idTarget)
                    .then(this.loadUsers)
                break;
        }
    }

    handleChange(event) {
        let idTarget = event.target.id;
        let value = event.target.value;

        switch (idTarget) {
            case constants.USERNAME_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        username: value
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
            case "select-order":
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        orderBy: value
                    }
                })
                break;
        }
    }

    handleSubmit(event) {
        event.preventDefault();
        Loading.Standard('Loading...',);
        const objParam = {
            username: this.state.username != null ? (this.state.username) : (""),
            email: this.state.email != null ? (this.state.email) : (""),
            phoneNumber: this.state.phoneNumber != null ? (this.state.phoneNumber) : (""),
            orderBy: this.state.orderBy != null ? (this.state.orderBy) : (""),
        }

        backend.REQ_GET(backend.ADMIN_USER_LIST_PATH, objParam)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        users: res
                    }
                })

            })
    }

    componentDidMount() {
       this.loadUsers();
    }

    loadUsers(){
        Loading.Standard('Loading...',);
        backend.REQ_GET(backend.ADMIN_USER_LIST_PATH)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.setState({
                    users: res
                })
            })
    }

    render() {
        return (
            <div className="container pt-2 mt-3 bg-white">
                <form className="pb-2" onSubmit={this.handleSubmit}>
                    <div className="row text-center">
                        <div className="col-2">
                            <input type="text" placeholder="Потребител" id={constants.USERNAME_NAME}
                                   onChange={this.handleChange}/>
                        </div>

                        <div className="col-2">
                            <input type="text" placeholder="Email" id={constants.EMAIL_NAME}
                                   onChange={this.handleChange}/>
                        </div>
                        <div className="col-2">
                            <input type="text" placeholder="Тел. номер" id={constants.PHONE_NUMBER_NAME}
                                   onChange={this.handleChange}/>
                        </div>

                        <div className="col text-center">
                            <label htmlFor="">Сортирай по:</label>
                            <select className="form-select" id="select-order" onChange={this.handleChange}>
                                <option value="USERNAME ASC" selected>Потребител Възходящ</option>
                                <option value="USERNAME DESC">Потребител Нисходящ</option>
                                <option value="EMAIL ASC">Email Възходящ</option>
                                <option value="EMAIL DESC">Email Нисходящ</option>
                                <option value="PHONE_NUMBER ASC">Тел. номер Възходящ</option>
                                <option value="PHONE_NUMBER DESC">Тел. номер Нисходящ</option>
                            </select>
                        </div>
                        <div className="col">
                            <button type="submit" className="btn w3-hover-light-green w3-green">Search</button>
                        </div>
                    </div>
                </form>
                <div className="row">
                    <table className="table table-hover">
                        <thead className="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Снимка</th>
                            <th scope="col">Потребител</th>
                            <th scope="col">Email</th>
                            <th scope="col">Тел. номер</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.users != null ? (
                            this.state.users.map((user, index) => {
                                return <tr>
                                    <th scope="row">{index + 1}</th>
                                    <td>
                                        <img src={user.imgUrl} alt="" className="img-thumbnail user-image-list m-0"/>
                                    </td>
                                    <td>{user.username}</td>
                                    <td>{user.email}</td>
                                    <td>{user.phoneNumber}</td>
                                    <td>
                                        <Link to={frontendUtils.USER_EDIT_PATH + user.id}
                                              className="btn btn-warning m-0">Edit</Link>
                                    </td>
                                    <td>
                                        <Link to={frontendUtils.USER_PROFILE_PATH + user.id}
                                              className="btn btn-info m-0">View</Link>
                                    </td>
                                    {user.roles.includes(constants.ROLE_ADMIN) ? (
                                        <td>
                                            <button className="btn btn-danger m-0" id={user.id}
                                                    name="remove-admin" onClick={this.handleClick}>Remove Admin</button>
                                        </td>

                                    ) : (<td>
                                            <button className="btn btn-primary m-0" id={user.id}
                                                    name="make-admin" onClick={this.handleClick}>Make Admin</button>
                                        </td>
                                    )

                                    }

                                </tr>
                            })
                        ) : (
                            <span>Loading...</span>
                        )

                        }


                        </tbody>
                    </table>
                </div>

                {/*<nav aria-label="Page navigation example">*/}
                {/*    <ul className="pagination">*/}
                {/*        <li className="page-item"><a className="page-link" href="#">Previous</a></li>*/}
                {/*        <li className="page-item"><a className="page-link" href="#">1</a></li>*/}
                {/*        <li className="page-item"><a className="page-link" href="#">2</a></li>*/}
                {/*        <li className="page-item"><a className="page-link" href="#">3</a></li>*/}
                {/*        <li className="page-item"><a className="page-link" href="#">Next</a></li>*/}
                {/*    </ul>*/}
                {/*</nav>*/}
            </div>
        );
    }
}
