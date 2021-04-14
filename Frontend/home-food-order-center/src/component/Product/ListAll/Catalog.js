import React from "react";
import backend from "../../../utils/backendUtils";
import * as Icon from 'react-bootstrap-icons';
import $ from 'jquery';
import frontend from "../../../utils/frontendUtils";
import {Link} from "react-router-dom";
import {Loading} from "notiflix";
import frontendUtils from "../../../utils/frontendUtils";


export default class Catalog extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.loadCategories = this.loadCategories.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.handleClickCollapse = this.handleClickCollapse.bind(this);
        this.loadProducts = this.loadProducts.bind(this);
        this.state = {
            categories: null,
            loadedCategories: false,
            prevTargetId: -1,
            products: null,
            loadedProducts: false
        }
    }

    handleClickCollapse(event) {
        let id = event.target.name;
        let collapseButton = $("#" + id);
        let attribute = collapseButton.attr("class");
        if (attribute === "collapse") {
            collapseButton.removeClass("collapse")
        } else {
            collapseButton.toggleClass("collapse")
        }
    }

    handleClick(event) {
        event.preventDefault();
        if (this.state.prevTargetId > -1) {
            let elementById = document.getElementById(this.state.prevTargetId);
            elementById.classList.remove("bg-primary", "text-white")
        }

        event.target.classList.add("bg-primary", "text-white");
        this.setState({
            prevTargetId: event.target.id
        })
    }

    componentDidMount() {
        this.loadCategories();
        this.loadProducts();
    }

    loadCategories() {
        Loading.Standard('Loading...',);
        backend.REQ_GET(backend.CATEGORY_GETALL_URL)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                this.setState({
                    categories: res,
                    loadedCategories: true
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
    }


    loadProducts(event) {
        if (event == null) {
            backend.REQ_GET(backend.PODUCT_GETALL_URL)
                .then(res => res.json())
                .then(res => {
                    this.setState({
                        products: res,
                        loadedProducts: true
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
        } else {
            let params = {
                subcategoryId: event.target.id
            }
            backend.REQ_GET(backend.PODUCT_GETALL_URL, params)
                .then(res => res.json())
                .then(res => {
                    this.setState( (prevState) =>{
                        return {
                            ...prevState,
                            products: res,
                            loadedProducts: true
                        }
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
        }
    }


    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-md-2">
                        <nav id="sidebar">
                            {/*<div className="sidebar-header">*/}
                            {/*    <h3>Home Food Center</h3>*/}
                            {/*</div>*/}
                            <ul className="list-unstyled components pl-4">
                                <p>Гладен ли си? <br/>Нека те нахраним !</p>

                                {this.state.loadedCategories ? (
                                    <div>
                                        {this.state.categories.map((value, index) => {
                                            return <li>
                                                <a data-toggle="collapse" aria-expanded="false"
                                                   className="dropdown-toggle sidebar-menu-component link-class"
                                                   onClick={this.handleClickCollapse}
                                                   name={index}>{value.name}</a>
                                                <ul className="collapse list-unstyled" id={index}>
                                                    {
                                                        value.subcategories.map((sub, i) => {
                                                                return <li className="sidebar-menu-component">
                                                                    <a className="link-class" id={sub.id}
                                                                       onClick={this.loadProducts}>{sub.name}</a>
                                                                </li>
                                                            }
                                                        )
                                                    }
                                                </ul>
                                            </li>
                                        })
                                        }

                                    </div>
                                ) : (
                                    <span>Loading...</span>
                                )}

                            </ul>
                        </nav>
                    </div>
                    <div className="col">
                        {/*{!--Page Content  --}*/}
                        <div id="content">

                            <div className="container">
                                <div className="row mb-4 d-flex justify-content-around">
                                    {this.state.loadedProducts ? (
                                        this.state.products.map((product) => {
                                                return<div className="col-3 d-flex flex-column text-center catalog-element m-1 mb-3">
                                                    <img src={product.imageUrl} className="image-product mt-2" alt={product.name}/>
                                                    <h5><strong>{product.name}</strong> </h5>
                                                    <h6><strong>Цена: </strong> {product.price.toFixed(2)}</h6>
                                                    <Link to={frontend.PRODUCT_DETAILS_PATH + product.id} className="btn btn-info mb-3"> Детайли </Link>
                                                </div>
                                            }
                                        )
                                    ) : (
                                        <span>Loading...</span>
                                    ) }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        );
    }
}
