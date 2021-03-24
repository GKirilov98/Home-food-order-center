import React from "react";
import '../../style/css/Cart/cart.css';
import {MusicNote} from "react-bootstrap-icons";
import * as Icon from 'react-bootstrap-icons';

export default class Cart extends React.Component {
    render() {
        return (
            <div className="container mt-2 mb-1">
                <div className="d-flex justify-content-center row">
                    <div className="col">
                        <div className="pr-2 pt-2 pb-0">
                            <h4>Shopping cart</h4>
                        </div>
                        {/*Items*/}
                        <div
                            className="item-cart-border row mr-1 ml-1 d-flex flex-row justify-content-between align-items-center bg-white mt-4 px-3 rounded ">
                            <div className="col-sm-2">
                                <img className="rounded" src="https://i.imgur.com/XiFJkhI.jpg" width="70" alt=""/>
                            </div>
                            <div className="col-sm-2 d-flex flex-column align-items-center product-details">
                                <h4>Pizza name</h4>
                            </div>
                            <div className="col-sm-2 d-flex flex-row align-items-center qty">
                                <label htmlFor="quantity" className="text-dark">Q.</label>
                                <input type="number" name="quantity" defaultValue="1"/>
                            </div>
                            <div className="col-sm-2 d-flex flex-row align-items-center qty">
                                <h6>Unit price ??,??</h6>
                            </div>
                            <div className="col-sm-2">
                                <h6 className="text-grey">Total price: $20.00</h6>
                            </div>
                            <div className="col-sm-1 d-flex align-items-center">
                                <button className="btn btn-outline-danger w3-hover-black">
                                    <Icon.XCircle size="20" color="red"/>
                                </button>
                            </div>
                        </div>

                        {/*Items*/}
                        <div
                            className="item-cart-border row mr-1 ml-1 d-flex flex-row justify-content-between align-items-center bg-white mt-4 px-3 rounded ">
                            <div className="col-sm-2">
                                <img className="rounded" src="https://i.imgur.com/XiFJkhI.jpg" width="70" alt=""/>
                            </div>
                            <div className="col-sm-2 d-flex flex-column align-items-center product-details">
                                <h4>Pizza name</h4>
                            </div>
                            <div className="col-sm-2 d-flex flex-row align-items-center qty">
                                <label htmlFor="quantity" className="text-dark">Q.</label>
                                <input type="number" name="quantity" defaultValue="1"/>
                            </div>
                            <div className="col-sm-2 d-flex flex-row align-items-center qty">
                                <h6>Unit price ??,??</h6>
                            </div>
                            <div className="col-sm-2">
                                <h6 className="text-grey">Total price: $20.00</h6>
                            </div>
                            <div className="col-sm-1 d-flex align-items-center">
                                <button className="btn btn-outline-danger w3-hover-black">
                                    <Icon.XCircle size="20" color="red"/>
                                </button>
                            </div>
                        </div>

                        <hr className="border-dark"/>
                        {/*Discount*/}
                        <div
                            className="discount-cart-border d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">
                            <input type="text"
                                   className="form-control border-2 gift-card"
                                   placeholder="discount code/gift card"/>
                            <button className="btn btn-outline-warning btn-sm ml-2" type="button">Apply</button>
                        </div>
                        <hr className="border-dark"/>

                        {/*Summary*/}
                        <div className="border-cart-summary">
                            <div className="pl-2 pb-0">
                                <h4><b>Summary</b></h4>
                            </div>
                            <div>
                                <div className="col">Total Amount: ??,??</div>

                                <div className="col">
                                    <div>Method of payment:</div>
                                    <div className="row">
                                        <div className="col-1 pr-0">
                                            <input type="radio" name="payment" id="cash"/>
                                        </div>
                                        <div className="col-2 pl-0 pr-0">
                                            <label htmlFor="cash">IN CASH</label>
                                        </div>

                                        <div className="col-1 pr-0">
                                            <input type="radio" name="payment" id="online"/>
                                        </div>
                                        <div className="col-1 pl-0">
                                            <label htmlFor="online">ONLINE</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="mb-3 d-flex flex-row align-items-center mt-3 p-2 bg-white rounded">
                            <button className="btn btn-warning btn-block btn-lg ml-2 pay-button w3-hover-blue" type="button">Proceed
                                to Pay
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
