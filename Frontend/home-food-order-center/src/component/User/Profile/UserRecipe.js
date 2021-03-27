import React from "react";

export default class UserRecipe extends React.Component{
    render() {
        return (
            <div className="container mt-2 mb-1">
                <div className="d-flex justify-content-center row">
                    <div className="col">
                        <div className="pr-2 pt-2 pb-0">
                            <h4>Recipes info</h4>
                        </div>
                        {/*Items*/}
                        <div className="item-cart-border row mr-1 ml-1 d-flex flex-row justify-content-between align-items-center bg-white mt-4 px-3 rounded ">
                            <div className="col d-flex flex-column align-items-center product-details">
                                <h6>Items count</h6>
                            </div>
                            <div className="col d-flex flex-row align-items-center qty">
                                <h6>Date of order</h6>
                            </div>
                            <div className="col">
                                <h6 className="text-grey">Total price: $20.00</h6>
                            </div>
                            <a className="btn btn-info ">View</a>
                        </div>

                        {/*Items*/}
                        <div className="item-cart-border row mr-1 ml-1 d-flex flex-row justify-content-between align-items-center bg-white mt-4 px-3 rounded ">
                            <div className="col d-flex flex-column align-items-center product-details">
                                <h6>Items count</h6>
                            </div>
                            <div className="col d-flex flex-row align-items-center qty">
                                <h6>Date of order</h6>
                            </div>
                            <div className="col">
                                <h6 className="text-grey">Total price: $20.00</h6>
                            </div>
                            <a className="btn btn-warning ">View</a>
                        </div>

                        {/*Items*/}
                        <div className="item-cart-border row mr-1 ml-1 d-flex flex-row justify-content-between align-items-center bg-white mt-4 px-3 rounded ">
                            <div className="col d-flex flex-column align-items-center product-details">
                                <h6>Items count</h6>
                            </div>
                            <div className="col d-flex flex-row align-items-center qty">
                                <h6>Date of order</h6>
                            </div>
                            <div className="col">
                                <h6 className="text-grey">Total price: $20.00</h6>
                            </div>
                            <a className="btn btn-warning ">View</a>
                        </div>
                    </div>
                </div>

                <nav aria-label="Page navigation example">
                    <ul className="pagination">
                        <li className="page-item"><a className="page-link" href="#">Previous</a></li>
                        <li className="page-item"><a className="page-link" href="#">1</a></li>
                        <li className="page-item"><a className="page-link" href="#">2</a></li>
                        <li className="page-item"><a className="page-link" href="#">3</a></li>
                        <li className="page-item"><a className="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </div>
        );
    }
}
