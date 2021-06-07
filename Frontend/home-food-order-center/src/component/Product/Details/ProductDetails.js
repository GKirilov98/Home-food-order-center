import React from "react";
import backend from "../../../utils/backendUtils";
import {Link} from "react-router-dom";
import frontendUtils from "../../../utils/frontendUtils";
import {Loading, Confirm} from 'notiflix';
import constants from "../../../utils/constants";
import frontend from "../../../utils/frontendUtils";

export default class ProductDetails extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            product: null,
            orderQuantity: 0
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            frontendUtils.notifyError("Моля влезте в системата!");
            props.history.push(frontend.LOGIN_PATH);
        }
    }

    handleChange(event) {
        let value = event.target.value;
        this.setState((prevState) => {
            return {
                ...prevState,
                orderQuantity: value
            }
        })
    }

    handleSubmit(event) {
        event.preventDefault();
        Loading.Standard('Loading...',);
        let bodyObj = {
            productId: this.state.product.id,
            quantity: this.state.orderQuantity
        }

        if (bodyObj.quantity === 0){
            frontendUtils.notifyError("Не е въведено желано количество!")
            Loading.Remove();
            return;
        }

        backend.REQ_POST(backend.ORDER_ADD_URL, bodyObj)
            .then(res => res.json())
            .then(res => {
                Loading.Remove();
                frontendUtils.notifyInfo("Добавено в количката успешно!");
                Confirm.Show('', 'Искате ли да продължите пазаруването?',
                    'Да', 'Не, виж количка',
                    () => this.props.history.push(frontendUtils.CATALOG_PATH),
                    () => this.props.history.push(frontendUtils.CART_VIEW_PATH));
            })
            .catch(err => {
                console.log(err);
                Loading.Remove();
                frontendUtils.notifyError("Не може да се добави в количката!");
                if (err.message === '401') {
                    sessionStorage.clear();
                    frontendUtils.notifyError("Вашата сесия е истекла, моля влезте отново!");
                    this.props.history.push(frontendUtils.LOGIN_PATH);
                }
            })

    }

    componentDidMount() {
        Loading.Standard('Loading...',);
        let id = this.props.match.params.id;
        backend.REQ_GET(backend.PRODUCT_DETAILS_URL + id)
            .then(res => res.json())
            .then(data => {
                if (data.length === 1) {
                    this.setState({
                        product: data[0]
                    })
                }
                Loading.Remove();})
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
            <div>

                {/*<div className="row">*/}
                {this.state.product ? (
                    <div className="container bg-white mb-4 mt-3">
                        <div className="col">
                            <img className="m-2 rounded img-thumbnail image-product-details"
                                 src={this.state.product.imageUrl} alt=""/>
                            {sessionStorage.getItem(constants.USER_ROLES_KEY_NAME).split(",").includes(constants.ROLE_ADMIN) ? (
                                <Link to={frontend.ADMIN_PRODUCT_EDIT_PATH + this.state.product.id} className="btn btn-warning float-right mt-3 btn-size">Редакция</Link>
                            ) : (<span/>)
                            }
                        </div>
                        <div className="col pr-5">
                            <h2>{this.state.product.name}</h2>
                            <p>{this.state.product.description}</p>

                            <div className="col">
                                <h3>Цена: {this.state.product.price.toFixed(2)}</h3>
                                <h4>Категория: {this.state.product.category},
                                    Подкатегория: {this.state.product.subcategory}</h4>
                            </div>
                            <div className="col border m-3 pb-3">
                                <form className="text-dark mb-5 form-created" onSubmit={this.handleSubmit}>
                                    <p className="text-dark">Максимално количество: {this.state.product.maxQuantity} бр.</p>
                                    <input type="number" className="form-control" placeholder="Quantity"
                                           min={1} max={this.state.product.maxQuantity} onChange={this.handleChange}/>
                                    <button type="submit" className="btn w3-green w3-hover-blue mt-2">Добави в количка
                                    </button>
                                </form>
                                <Link to="/catalog" className="btn-info btn">Назад към Каталог</Link>
                                <Link to="/cart/view" className="btn-info btn float-right w3-hover-yellow">Преглед количка</Link>
                            </div>

                        </div>
                    </div>
                ) : (
                    <span>Loading...</span>
                )
                }
            </div>

        );
    }
}
