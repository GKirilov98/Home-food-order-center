import React from "react";
import '../../style/css/Payment/payment.css';

export default class Payment extends React.Component {
    render() {
        return (
            <div className="container bg-info">
                <div className="row justify-content-center">
                    <div className="col-lg-12">
                        <form className="bg-info">
                            <div className="card">
                                <div className="row">
                                    <h4><strong>Amount: </strong>??,??</h4>
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
                                    <div className="col-cm-5">
                                        <div className="row">
                                            <div className="form-group col-md-6 mb-0">
                                                <label className="form-control-label">Name on Card</label>
                                            </div>
                                            <input className="mt-0" type="text" id="cname" name="cname"
                                                   placeholder="Johnny Doe"/>
                                        </div>

                                        <div className="row">
                                            <div className="form-group col-md-6 mb-0 mt-1">
                                                <label className="form-control-label">Card
                                                    Number</label>
                                            </div>
                                            <input className="mt-0" type="text" id="cnum" name="cnum"
                                                   placeholder="1111 2222 3333 4444"/>
                                        </div>

                                        <div className="row">
                                            <div className="form-group col-md-6"><label className="form-control-label">Expiration
                                                Date</label> <input type="text" id="exp" name="exp"
                                                                    placeholder="MM/YYYY"/>
                                            </div>
                                            <div className="form-group col-md-6"><label
                                                className="form-control-label">CVV</label>
                                                <input type="text" id="cvv" name="cvv" placeholder="***"/>
                                            </div>
                                        </div>
                                    </div>

                                    <button className="form-group w3-hover-khaki ">Pay</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>

        );
    }
}
