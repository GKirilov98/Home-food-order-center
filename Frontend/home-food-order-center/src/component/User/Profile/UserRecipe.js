import React from "react";
import {Link} from "react-router-dom";
import frontendUtils from "../../../utils/frontendUtils";

export default class UserRecipe extends React.Component {

    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <div className="container mt-2 mb-1">
                <div className="d-flex justify-content-center row">
                    <div className="col">
                        <div className="pr-2 pt-2 pb-0">
                            <h4>История на поръчки</h4>
                        </div>
                        { this.props.receipts.length > 0 ? (
                            this.props.receipts.map((value, index) => {
                                    return <div
                                        className="item-cart-border row mr-1 ml-1 d-flex flex-row justify-content-between align-items-center bg-white mt-4 px-3 rounded ">
                                        <div className="col d-flex flex-column align-items-center product-details">
                                            <h6>#{index + 1}</h6>
                                        </div>
                                        <div className="col d-flex flex-column align-items-center product-details">
                                            <h6>{value.statusCode}</h6>
                                        </div>
                                        <div className="col d-flex flex-column align-items-center product-details">
                                            <h6>{
                                                value.dateAdded.split("T")[0] + " " + value.dateAdded.split("T")[1].split(".")[0]
                                            }ч.</h6>
                                        </div>
                                        <div className="col d-flex flex-row align-items-center qty">
                                            <h6>Date of order</h6>
                                        </div>
                                        <div className="col">
                                            <h6 className="text-grey">Сума: {value.amount.toFixed(2)}</h6>
                                        </div>
                                        <Link to={frontendUtils.RECEIPT_VIEW_PATH + value.id} className="btn btn-warning ">View</Link>
                                    </div>
                                }
                            )
                            ):(<span />)

                        }
                    </div>
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
