import React from "react";
import * as Icon from 'react-bootstrap-icons';
import {Confirm, Loading} from "notiflix";
import backend from "../../utils/backendUtils";
import {Link} from "react-router-dom";
import frontendUtils from "../../utils/frontendUtils";
import constants from "../../utils/constants";
import frontend from "../../utils/frontendUtils";

export default class ReceiptView extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.handleClick = this.handleClick.bind(this);
        this.state = {
            receipt: null
        }
    }

    static getDerivedStateFromProps(props) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            props.history.push(frontend.LOGIN_PATH);
        }
    }

    handleClick(event) {
        Loading.Standard('Loading...',);
        let id = event.target.id;
        if (id === "delete-button"){

            Confirm.Show( '!!! WARNING !!!', 'Сигурни ли сте, че искате да изтриете тази касова бележка?',
                'Не', 'Да',
                () => "",
                () => {
                    backend.REQ_POST(backend.BUSINESS_RECEIPT_DELETE_PATH + this.state.receipt.id)
                        .then(() => {
                            let isAdmin = false;
                            for (const string of sessionStorage.getItem(constants.USER_ROLES_KEY_NAME).split(",")) {
                                if (string === constants.ROLE_ADMIN || string === constants.ROLE_BUSINESS){
                                    isAdmin = true;
                                    break
                                }
                            }

                            frontendUtils.notifyInfo("Касовата бележка е изтрита успешно!");
                            if (isAdmin){
                                this.props.history.push(frontendUtils.ADMIN_RECEIPT_LIST_PATH)
                            }
                        })
                        .catch(err => {
                                Loading.Remove();
                                if (err.message === '401') {
                                    sessionStorage.clear();
                                    frontendUtils.notifyError("Вашата сесия е истекла, моля влезте отново!");
                                    this.props.history.push(frontendUtils.LOGIN_PATH);
                                }
                            }
                        )
                } );
        } else if (id === "paid-button") {
            backend.REQ_POST(backend.BUSINESS_RECEIPT_PAID_URL + this.state.receipt.id)
                .then(res => res.json())
                .then(res => {
                    if (res[0].user.username !== sessionStorage.getItem(constants.USERNAME_KEY_NAME)) {
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
                            this.props.history.push(frontendUtils.CATALOG_PATH);
                        }
                    }


                    this.setState({
                        receipt: res[0]
                    })
                    Loading.Remove();
                })
                .catch(err => {
                        Loading.Remove();
                        if (err.message === '401') {
                            sessionStorage.clear();
                            frontendUtils.notifyError("Вашата сесия е истекла или нямате необходимите права, моля влезте отново!");
                            this.props.history.push(frontendUtils.LOGIN_PATH);
                        }
                    }
                )
        } else {

            backend.REQ_GET(backend.BUSINESS_RECEIPT_REPORT_URL + this.state.receipt.id)
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

    }

    componentDidMount() {
        Loading.Standard('Loading...',);
        backend.REQ_GET(backend.RECEIPT_GET_BY_ID_URL + this.props.match.params.id)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.setState({
                    receipt: res[0]
                })
            })
            .catch(err => {
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
            <div className="page-content container bg-white mt-2 pb-3">
                {this.state.receipt != null ? (
                    <React.Fragment>
                        <div className="page-header text-blue-d2">
                            <h1 className="page-title text-secondary-d1">
                                ReceiptID#:
                                <small className="page-info">
                                    {this.state.receipt.id}
                                </small>
                            </h1>
                        </div>
                        <div className="container px-0">
                            <div className="row mt-4">
                                <div className="col-12 col-lg-10 offset-lg-1">
                                    <div className="row">
                                        <div className="col-sm-6">
                                            <div>
                                                <span className="text-sm text-grey-m2 align-middle">
                                                    Username на получател:
                                                </span>
                                                <span className="text-600 text-110 text-blue align-middle">
                                                    {this.state.receipt.user.username}
                                                </span>
                                            </div>

                                            <div>
                                                <span className="text-sm text-grey-m2 align-middle">
                                                    Име и Фамилия:
                                                </span>
                                                <span className="text-600 text-110 text-blue align-middle">
                                                    {this.state.receipt.user.firstName + " " + this.state.receipt.user.lastName}
                                                </span>
                                            </div>

                                            <div className="text-grey-m2">
                                                <span className="text-sm text-grey-m2 align-middle">Адрес:</span>

                                                <span> <Icon.GeoAltFill
                                                    color="red"/>{this.state.receipt.deliveryAddress}</span>

                                                <div className="my-1">
                                                    <span
                                                        className="text-sm text-grey-m2 align-middle">Телефонен номер:</span>
                                                    <Icon.TelephoneForwardFill color="green"/>
                                                    <b className="text-600"> {this.state.receipt.user.phoneNumber} </b>
                                                </div>
                                                <div className="my-1">
                                                    <span className="text-sm text-grey-m2 align-middle">Email:</span>
                                                    <Icon.Envelope color="orange"/>
                                                    <b className="text-600"> {this.state.receipt.user.email} </b>
                                                </div>
                                            </div>
                                        </div>
                                        {/*<!-- /.col -->*/}

                                        <div
                                            className="text-95 col-sm-6 align-self-start d-sm-flex justify-content-end">
                                            <hr className="d-sm-none"/>
                                            <div className="text-grey-m2">
                                                <div className="my-2">
                                                       <span className="text-600 text-90">
                                                           Дата на поръчка: {this.state.receipt.dateAdded.split("T")[0]
                                                       + " " + this.state.receipt.dateAdded.split("T")[1].split(".")[0]}ч.
                                                       </span>
                                                </div>

                                                <div className="my-2">
                                                    <span className="text-600 text-90">Статус:</span> <span
                                                    className="badge badge-warning badge-pill px-25">{
                                                    this.state.receipt.statusCode === "SEND" ? ("Изпратена") : (
                                                        this.state.receipt.statusCode === "PAID" ? ("Платена") : (
                                                            "Пазаруване"
                                                        )
                                                    )
                                                }</span>
                                                </div>
                                            </div>
                                        </div>
                                        {/*<!-- /.col -->*/}
                                    </div>

                                    <div className="mt-4 ">
                                        <div className="row text-600 text-white bgc-default-tp1 py-25">
                                            <div className="d-none d-sm-block col-1">#</div>
                                            <div className="col-9 col-sm-5">Име</div>
                                            <div className="d-none d-sm-block col-4 col-sm-2">Кол.</div>
                                            <div className="d-none d-sm-block col-sm-2">Един. цена</div>
                                            <div className="col-2">Сума</div>
                                        </div>

                                        <div className="text-95 text-secondary-d3">
                                            {
                                                this.state.receipt.orders.map((value, index) => {
                                                        let bgOdd = ((index + 1) % 2 === 0) ? ("bgc-default-l4") : ("");
                                                        return <div className={"row mb-2 mb-sm-0 py-25 " + bgOdd}>
                                                            <div className="d-none d-sm-block col-1">{index + 1}</div>
                                                            <div className="col-9 col-sm-5">{value.product.name}</div>
                                                            <div
                                                                className="d-none d-sm-block col-2">{value.neededQuantity}</div>
                                                            <div
                                                                className="d-none d-sm-block col-2 text-95">{value.product.price.toFixed(2)} лв.
                                                            </div>
                                                            <div
                                                                className="col-2 text-secondary-d2">{value.amount.toFixed(2)} лв.
                                                            </div>
                                                        </div>

                                                    }
                                                )
                                            }
                                        </div>

                                        <div className="row border-b-2 brc-default-l2"></div>

                                        {/*<!-- or use a table instead -->*/}

                                        <div className="row mt-3">
                                            <div className="col-12 col-sm-7 text-grey-d2 text-95 mt-2 mt-lg-0">
                                            </div>

                                            <div
                                                className="col-12 col-sm-5 text-grey text-90 order-first order-sm-last">
                                                <div className="row my-2 align-items-center bgc-primary-l3 p-2">
                                                    <div className="col-7 text-right">
                                                        Общо дължима сума:
                                                    </div>
                                                    <div className="col-5">
                                                        <span
                                                            className="text-150 text-success-d3 opacity-2">{this.state.receipt.totalAmount.toFixed(2)} лв.</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <hr/>

                                        <Link to={frontendUtils.CATALOG_PATH}
                                              className="btn btn-info btn-bold px-4 float-right mt-3 mt-lg-0">
                                            Каталог
                                        </Link>
                                        {
                                            sessionStorage.getItem('roles').split(',').map((role) => {
                                                return <React.Fragment>
                                                    {(role === constants.ROLE_ADMIN || role === constants.ROLE_BUSINESS) &&
                                                    this.state.receipt.statusCode === "SEND" ? (
                                                        <React.Fragment>
                                                            <button onClick={this.handleClick} id="paid-button"
                                                                    className="btn btn-warning">Маркирай като Платена
                                                            </button>
                                                        </React.Fragment>
                                                    ) : (
                                                        <React.Fragment/>
                                                    )}
                                                </React.Fragment>
                                            })
                                        }

                                        {
                                            sessionStorage.getItem('roles').split(',').map((role) => {
                                                return <React.Fragment>
                                                    {(role === constants.ROLE_ADMIN || role === constants.ROLE_BUSINESS)
                                                        ?(
                                                        <React.Fragment>
                                                            <button onClick={this.handleClick} id="export-button"
                                                                    className="btn btn-success m-3">Експорт
                                                            </button>
                                                        </React.Fragment>
                                                    ) : (
                                                        <React.Fragment/>
                                                    )}
                                                </React.Fragment>
                                            })
                                        }


                                        {
                                            sessionStorage.getItem('roles').split(',').map((role) => {
                                                return <React.Fragment>
                                                    {(
                                                        (role === constants.ROLE_ADMIN || role === constants.ROLE_BUSINESS)
                                                    )?(
                                                            <React.Fragment>
                                                                <button onClick={this.handleClick} id="delete-button"
                                                                        className="btn btn-danger m-3">Изтрии
                                                                </button>
                                                            </React.Fragment>
                                                        ) : (
                                                            <React.Fragment/>
                                                        )}
                                                </React.Fragment>
                                            })
                                        }

                                    </div>
                                </div>
                            </div>
                        </div>
                    </React.Fragment>
                ) : (
                    <span>Loading..</span>
                )

                }
            </div>
        );
    }

}
