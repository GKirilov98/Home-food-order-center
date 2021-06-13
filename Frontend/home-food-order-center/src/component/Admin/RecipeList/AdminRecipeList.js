import React from "react";
import backend from "../../../utils/backendUtils";
import frontendUtils from "../../../utils/frontendUtils";
import {Link} from "react-router-dom";
import constants from "../../../utils/constants";
import {Loading} from "notiflix";
import frontend from "../../../utils/frontendUtils";

export default class AdminRecipeList extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.state = {
            receipts: null,
            statusCode: null,
            username: null,
            receiptId: null,
            dateOrder: null,
            orderBy: null
        }
    }

    static getDerivedStateFromProps(props) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            props.history.push(frontend.LOGIN_PATH);
        } else {
            let isBusiness = false;
            let isAdmin = false;
            sessionStorage.getItem(constants.USER_ROLES_KEY_NAME).split(",")
                .forEach((role) => {
                    if (role === constants.ROLE_BUSINESS) {
                        isBusiness = true;
                    } else if (role === constants.ROLE_ADMIN) {
                        isAdmin = true;
                    }
                })
            if (!isBusiness && !isAdmin) {
                frontendUtils.notifyError("Нямате необходимите права за достап!");
                props.history.push(frontendUtils.CATALOG_PATH);
            }
        }
    }

    handleClick(event){
        backend.REQ_GET(backend.BUSINESS_RECEIPT_REPORT_URL + event.target.id)
            .then(response => {
                const reader = response.body.getReader()
                return new ReadableStream({
                    start(controller) {
                        return pump();
                        function pump() {
                            return reader.read().then(({ done, value }) => {
                                // When no more data needs to be consumed, close the stream
                                if (done) {
                                    controller.close();
                                    return;
                                }
                                // Enqueue the next data chunk into our target stream
                                controller.enqueue(value);
                                return pump();
                            });
                        }
                    }
                })
            })
            .then(stream => new Response(stream))
            .then(response => response.blob())
            .then(blob => {
                const fileName = "download";
                const extension = "zip";
                let blobUrl = URL.createObjectURL(blob);
                const link = document.createElement('a');
                link.href = blobUrl;
                link.setAttribute('download', `${fileName}.${extension}`);
                document.body.appendChild(link);
                link.click();
                Loading.Remove();
            })
            .catch(err =>  {
                Loading.Remove();
                if (err.message === '401') {
                    sessionStorage.clear();
                    frontendUtils.notifyError("Вашата сесия е истекла или нямате необходимите права, моля влезте отново!");
                    this.props.history.push(frontendUtils.LOGIN_PATH);
                }
            } );
    }

    handleSubmit(event) {
        event.preventDefault();
        Loading.Standard('Loading...',);
        let objParams = {
            status: this.state.statusCode != null ? (this.state.statusCode) : (""),
            username: this.state.username != null ? (this.state.username) : (""),
            receiptId: this.state.receiptId != null ? (this.state.receiptId) : (-1),
            date: this.state.dateOrder != null ? (this.state.dateOrder) : (""),
            orderBy: this.state.orderBy != null ? (this.state.orderBy) : ("")
        }

        backend.REQ_GET(backend.BUSINESS_RECEIPT_GETALL_URL, objParams)
            .then(res => res.json())
            .then(res => {
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        receipts: res,
                    }
                })
                Loading.Remove();
            })
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
            case "status-code-search":
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        statusCode: value
                    }
                })
                break;
            case "receipt-id-search":
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        receiptId: value
                    }
                })
                break;
            case "date-order-search":
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        dateOrder: value
                    }
                })
                break;
            case "order-by-search":
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        orderBy: value
                    }
                })
                break;
        }
    }

    componentDidMount() {
        backend.REQ_GET(backend.BUSINESS_RECEIPT_GETALL_URL)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    receipts: res
                })
            })

    }

    render() {
        return (
            <div className="container pt-2 mt-3 bg-white">
                <form className="pb-2" onSubmit={this.handleSubmit}>
                    <div className="row">
                        <div className="col-2">
                            <div>
                                <label htmlFor="">Статус на касова</label>
                            </div>
                            <div>
                                <select className="form-select" onChange={this.handleChange} id="status-code-search">
                                    <option value="ALL" selected>Всички</option>
                                    <option value="SHOPPING">Shopping</option>
                                    <option value="SEND">Send</option>
                                    <option value="PAID">Paid</option>
                                </select>
                            </div>
                        </div>
                        <div className="col-2">
                            <label htmlFor={constants.USERNAME_NAME}>Потребител</label>
                            <input type="text" id={constants.USERNAME_NAME} onChange={this.handleChange}/>
                        </div>

                        <div className="col-2">
                            <label htmlFor="receipt-id-search">Касова ID</label>
                            <input type="number" id="receipt-id-search" onChange={this.handleChange}/>
                        </div>

                        <div className="col-2">
                            <label className="m-0">Дата на поръчка</label>
                            <input type="date" onChange={this.handleChange} id="date-order-search"/>
                        </div>

                        <div className="col-3">
                            <label htmlFor="">Подреди по:</label>
                            <select className="form-select" onChange={this.handleChange} id="order-by-search">
                                <option value="ID ASC" selected>ИД Възходящ</option>
                                <option value="ID DESC">ИД Низходящ</option>
                                <option value="STATUS ASC">Статус Възходящ</option>
                                <option value="STATUS DESC">Статус Низходящ</option>
                                <option value="USERNAME ASC">Потребител Възходящ</option>
                                <option value="USERNAME DESC">Потребител Низходящ</option>
                                <option value="DATE ASC">Дата Възходящ</option>
                                <option value="DATE DESC">Дата Низходящ</option>
                                <option value="AMOUNT ASC">Сума Възходящ</option>
                                <option value="AMOUNT DESC">Сума Низходящ</option>
                            </select>
                        </div>
                        <div className="col-1">
                            <button className="btn w3-hover-light-green w3-green">Търси</button>
                        </div>
                    </div>
                </form>
                <div className="row">
                    <table className="table table-hover">
                        <thead className="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Касова ID</th>
                            <th scope="col">Статус</th>
                            <th scope="col">Потребител</th>
                            <th scope="col">Дата на поръчка</th>
                            <th scope="col">Обща сума</th>
                            <th scope="col"/>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.receipts != null ? (
                                this.state.receipts.map((value, index) => {
                                    return <tr>
                                        <th scope="row">{index + 1}</th>
                                        <td>{value.id}</td>
                                        <td>{value.statusCode}</td>
                                        <td>{value.username}</td>
                                        <td>{value.dateAdded.split("T")[0] + " " + value.dateAdded.split("T")[1].split(".")[0]}</td>
                                        <td>{value.amount.toFixed(2)} лв.</td>
                                        <td>
                                            <Link to={frontendUtils.RECEIPT_VIEW_PATH + value.id}
                                                  className="btn btn-info m-0">Преглед</Link>
                                            <button onClick={this.handleClick} id={value.id}
                                                    className="btn btn-success m-3">Експорт
                                            </button>
                                        </td>

                                    </tr>
                                })
                            ) : (
                                <span/>
                            )
                        }

                        </tbody>
                    </table>
                </div>
            </div>
        );
    }

}
