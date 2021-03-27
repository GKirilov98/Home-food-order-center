import React from "react";

export default class UserCardInfo extends React.Component {
    render() {
        return (
            <div>
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
                        <p className="mt-0">123 456 7890</p>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <label >Card Number</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">123 456 7890</p>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <label >Expiration Date</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">123 456 7890</p>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <label >CVV</label>
                    </div>
                    <div className="col-md-6">
                        <p className="mt-0">123 456 7890</p>
                    </div>
                </div>

            </div>

        );
    }
}
