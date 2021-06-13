import React from "react";

import backend from "../../utils/backendUtils";
import {Loading} from "notiflix";
import $ from 'jquery';
import frontendUtils from "../../utils/frontendUtils";
import constants from "../../utils/constants";
import frontend from "../../utils/frontendUtils";

export default class Cart extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.handleClick = this.handleClick.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.loadData = this.loadData.bind(this);
        this.state = {
            receipt: null
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            frontendUtils.notifyError("Моля влезте в системата!");
            props.history.push(frontend.LOGIN_PATH);
        }
    }

    handleSubmit(event) {
        event.preventDefault();
        Loading.Standard('Loading...',);
        let city = $('#city').attr('value');
        let address = $('#address').val();
        let objBody = {
            city,
            address,
            receiptId: this.state.receipt.id
        }
        backend.REQ_POST(backend.RECEIPT_CONFIRM_URL, objBody)
            .then(res => {
                Loading.Remove()
                frontendUtils.notifyInfo("Поръчката е изпратена упешно!")
                this.props.history.push(frontendUtils.RECEIPT_VIEW_PATH + objBody.receiptId)})
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

    handleClick(event) {
        let id = event.target.id;
        Loading.Standard('Loading...',);
        backend.REQ_POST(backend.ORDER_DELETE_URL + id, null)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.loadData()
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

    componentDidMount() {
        this.loadData();
    }

    loadData() {
        Loading.Standard('Loading...',);
        backend.REQ_GET(backend.RECEIPT_GET_BY_STATUS_SHOPPING_URL)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.setState({
                        receipt: res[0]
                    }
                )})
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
            <div className="container mt-2 mb-4 bg-white">
                <div className="d-flex justify-content-center row">
                    <div className="col mb-2">
                        <div className="pr-2 pt-2 pb-0">
                            <h4>Количка</h4>
                        </div>
                        {this.state.receipt != null ? (
                            this.state.receipt.orders != null ? (
                                this.state.receipt.orders.map((value) => {
                                    return <div
                                        className="item-cart-border row mr-1 ml-1 d-flex flex-row justify-content-between align-items-center bg-white mt-4 px-3 rounded ">
                                        <div className="col-sm-2">
                                            <img className="rounded" src={value.product.imageUrl} width="70" alt=""/>
                                        </div>
                                        <div className="col-sm-2 d-flex flex-column align-items-center product-details">
                                            <h4>{value.product.name}</h4>
                                        </div>
                                        <div className="col-sm-2 d-flex flex-row align-items-center qty">
                                            <h6 className="text-grey"> Количество: {value.neededQuantity}</h6>
                                        </div>
                                        <div className="col-sm-2 d-flex flex-row align-items-center qty">
                                            <h6>Ед. цена: {value.product.price.toFixed(2)} лв.</h6>
                                        </div>
                                        <div className="col-sm-2">
                                            <h6 className="text-grey">Обща цена: {value.amount.toFixed(2)}</h6>
                                        </div>
                                        <div className="col-sm-1 d-flex align-items-center">
                                            <button className="btn btn-danger w3-hover-border-black "
                                                    onClick={this.handleClick} id={value.id}>
                                                Изтрий
                                            </button>
                                        </div>
                                    </div>
                                })
                            ):(
                                <React.Fragment />
                            )
                        ) : (
                            <span>Loading...</span>
                        )
                        }


                        <hr className="border-dark"/>
                        {/*Discount*/}
                        <div
                            className="discount-cart-border d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">
                            <input type="text"
                                   className="form-control border-2 gift-card"
                                   placeholder="discount code/gift card"/>
                            <button className="btn btn-warning btn-sm ml-2 w3-hover-border-black" type="button">Въведи
                            </button>
                        </div>
                        <hr className="border-dark"/>

                        {/*Summary*/}
                        {this.state.receipt != null ? (
                            <div className="border-cart-summary ">
                                <div className="pl-2 pb-0 ">
                                    <h4><strong>Общо дължима сума:</strong> {this.state.receipt.totalAmount.toFixed(2)} лв.</h4>
                                </div>
                            </div>
                        ) : (<span/>)

                        }

                        <hr className="border-dark"/>
                        <form onSubmit={this.handleSubmit}>
                            <div
                                className="discount-cart-border d-flex flex-row align-items-center mt-3 p-2 bg-white rounded w3-border-green">
                                <div className="col-2">
                                    <h4><strong>Адрес:</strong></h4>
                                </div>

                                <div className="col-3">
                                    <label htmlFor="">Град</label>
                                    <input type="text" defaultValue="София" id="city" disabled/>
                                </div>
                                <div className="col">
                                    <label htmlFor="address">Кв./Ул./Номер</label>
                                    <input type="text" required id="address"/>
                                </div>

                            </div>

                            <div className="mb-3 d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">
                                <button className="btn btn-warning btn-block btn-lg ml-2 pay-button w3-hover-blue"
                                        type="submit">Поръчай
                                </button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        );
    }
}
