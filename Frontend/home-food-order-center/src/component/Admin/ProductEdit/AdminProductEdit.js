import React from "react";
import constants from "../../../utils/constants";
import frontend from "../../../utils/frontendUtils";
import backend from "../../../utils/backendUtils";
import {Loading} from "notiflix";
import frontendUtils from "../../../utils/frontendUtils";

export default class AdminProductEdit extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleUpload = this.handleUpload.bind(this)
        this.callQuery = this.callQuery.bind(this);
        this.state = {
            product: null,
            categories: null,
            subcategories: [],
            selectedCategory: null,
            selectedSubcategory: null,
            imgSrc: null,
            imgUrl: null,
            imgPublicId: null,
            productName: null,
            descriptionName: null,
            availableQuantity: null,
            unitPrice: null
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null) {
            props.history.push(frontend.LOGIN_PATH);
        }else {
            let isAdmin = false
            sessionStorage.getItem(constants.USER_ROLES_KEY_NAME).split(",")
                .forEach((role) => {if (role == constants.ROLE_ADMIN){
                    isAdmin = true;
                }} )
            if (!isAdmin){
                frontendUtils.notifyError("Нямате необходимите права за достап!");
                props.history.push(frontendUtils.CATALOG_PATH);
            }
        }
    }


    componentDidMount() {
        this.callQuery();
    }

    callQuery() {
        Loading.Standard('Loading...',);

        backend.REQ_GET(backend.CATEGORY_GETALL_URL)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    categories: res,
                })
            });

        let id = this.props.match.params.id;
        backend.REQ_GET(backend.PRODUCT_DETAILS_URL + id)
            .then(res => res.json())
            .then(data => {
                if (data.length === 1) {
                    this.setState({
                        product: data[0]
                    })
                }
                Loading.Remove();
            })
    }

    handleChange(event) {
        let nameTarget = event.target.name;
        let value = event.target.value;
        let index = undefined;
        switch (nameTarget) {
            case constants.IMAGE_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        imgSrc: event.target.files[0],
                    }
                })
                break;
            case constants.NAME_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        productName: value
                    }
                })
                break;
            case constants.DESCRIPTION_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        descriptionName: value
                    }
                })
                break;
            case constants.AVAILABLE_QUANTITY_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        availableQuantity: value
                    }
                })
                break;
            case constants.UNIT_PRICE_NAME:
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        unitPrice: value
                    }
                })
                break;
            case constants.CATEGORY_NAME:
                index = event.target.value;
                let subcategoriesS = this.state.categories[index].subcategories;
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        selectedCategory: prevState.categories[index].id,
                        subcategories: subcategoriesS
                    }
                })
                break;
            case constants.SUBCATEGORY_NAME:
                index = event.target.value;
                debugger;
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        selectedSubcategory: this.state.subcategories[index].id
                    }
                })
                break;
        }
    }

    handleSubmit(event) {
        event.preventDefault();
        Loading.Standard('Loading...',);
        const bodyData = {
            category: this.state.selectedCategory != null ?( this.state.selectedCategory):( this.state.product.categoryId),
            subcategory: this.state.selectedSubcategory!= null ?( this.state.selectedSubcategory):( this.state.product.subcategoryId),
            imgUrl: this.state.imgUrl != null ?( this.state.imgUrl):( this.state.product.imageUrl),
            imgPublicId: this.state.imgPublicId != null ?( this.state.imgPublicId):( this.state.product.imagePublicId),
            name: this.state.productName  != null ?( this.state.productName):( this.state.product.name),
            description: this.state.descriptionName  != null ?( this.state.descriptionName):( this.state.product.description),
            availableQuantity: this.state.availableQuantity != null ?( this.state.availableQuantity):( this.state.product.maxQuantity),
            unitPrice: this.state.unitPrice  != null ?( this.state.unitPrice):( this.state.product.price),
        }

        backend.REQ_POST(backend.ADMIN_PRODUCT_EDIT_PATH + this.state.product.id, bodyData)
            .then(() => {
                Loading.Remove();
                frontend.notifyInfo("Продукта е редактиран успешно!")
                this.props.history.push(frontend.PRODUCT_DETAILS_PATH + this.state.product.id );
            }).catch((error) => error)
    }

    handleUpload(event) {
        Loading.Standard('Loading...',);
        const formData = new FormData()
        formData.append("file", this.state.imgSrc)

        fetch(backend.IMAGE_CREATE_URL, {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, cors, *same-origin
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem(constants.TOKEN_KEY_NAME),
            },
            body: formData
        }).then(backend.handleErrors)
            .then(res => res.json())
            .then(res => {
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        imgUrl: res.url,
                        imgPublicId: res.public_id,
                    };
                })
                Loading.Remove();
            });
    }

    render() {

        let uploadButtonClass = null;
        if (this.state.imgSrc != null) {
            uploadButtonClass = "btn btn-info";
        } else {
            uploadButtonClass = "btn btn-info display-none";
        }


        return (
            <div className="container col-5 bg-white pb-5">
                {this.state.product != null ? (
                    <form onSubmit={this.handleSubmit}>
                        <h3 className='pt-1'>Редакция на продукт</h3>
                        <div className="row pb-2">
                            <div className="col">
                                {this.state.imgSrc != null ? (
                                    <img className="p-2 image-product" src={this.state.imgUrl}
                                         alt="Image product"/>
                                ) : (
                                    <img className="p-2 image-product" src={this.state.product.imageUrl}
                                         alt="Image product"/>
                                )

                                }

                                <div className="row">
                                    <div className="col">
                                        <input type="file" name={constants.IMAGE_NAME} onChange={this.handleChange}/>
                                    </div>
                                    <div className="col-3 pt-1">
                                        <button type="button" className={uploadButtonClass}
                                                onClick={this.handleUpload}> Upload
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col">
                                <div className="tab-content profile-tab" id="myTabContent">
                                    <div className="tab-pane fade show active" id="home" role="tabpanel"
                                         aria-labelledby="home-tab">
                                        <div className="row pb-2">
                                            <div className="col-md mt-0 ">
                                                <h6 className="ml-3">Име на продукта: </h6>
                                                <input type="text" id={constants.NAME_NAME}
                                                       name={constants.NAME_NAME}
                                                       onChange={this.handleChange}
                                                       defaultValue={this.state.product.name}
                                                       min='3' max='25'/>
                                            </div>
                                        </div>

                                        <div className="row pb-2">
                                            <div className="col-md mt-0">
                                                <div>
                                                    {this.state.categories != null ? (
                                                        <div className="row text-center">
                                                            <div className="col">
                                                                <span>Категория: </span>
                                                                <select onChange={this.handleChange}
                                                                        name={constants.CATEGORY_NAME}>
                                                                    <option value="-1" selected>
                                                                        Избери категория
                                                                    </option>
                                                                    {this.state.categories.map((value, index) => {
                                                                        return <option value={index}>
                                                                                {value.name}
                                                                            </option>
                                                                    })
                                                                    }
                                                                </select>
                                                            </div>
                                                            <div className="col">
                                                                <span>Под категория: </span>
                                                                <select onChange={this.handleChange} name={constants.SUBCATEGORY_NAME}>
                                                                    {
                                                                        this.state.subcategories.map((value, index) => {
                                                                            return this.state.product.subcategory === value.name ? (
                                                                                <option value={index} selected>
                                                                                    {value.name}
                                                                                </option>
                                                                            ) : (
                                                                                <option value={index}>
                                                                                    {value.name}
                                                                                </option>
                                                                            )
                                                                        })
                                                                    }
                                                                </select>
                                                            </div>
                                                        </div>
                                                    ) : (
                                                        <span/>
                                                    )
                                                    }

                                                </div>
                                            </div>
                                        </div>

                                        <div className="row pb-1">
                                            <div className="col-md mt-0">
                                                <h6 className="ml-3">Описание: </h6>
                                                <textarea id={constants.DESCRIPTION_NAME}
                                                          name={constants.DESCRIPTION_NAME}
                                                          minLength='50' maxLength='3999'
                                                          onChange={this.handleChange}
                                                          rows='10'
                                                          defaultValue={this.state.product.description}
                                                />
                                            </div>
                                        </div>

                                        <div className="row pb-2">
                                            <div className="col-md mt-0">
                                                <h6 className="ml-3">Описание: </h6>
                                                <input type="number" id={constants.AVAILABLE_QUANTITY_NAME}
                                                       min='1'
                                                       name={constants.AVAILABLE_QUANTITY_NAME} required
                                                       onChange={this.handleChange}
                                                       defaultValue={this.state.product.maxQuantity}
                                                />
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-md mt-0">
                                                <h6 className="ml-3">Единична цена: </h6>
                                                <input type="number" id={constants.UNIT_PRICE_NAME}
                                                       min='0.1'
                                                       name={constants.UNIT_PRICE_NAME} required
                                                       onChange={this.handleChange} step="any"
                                                       defaultValue={this.state.product.price}
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {/*) : (*/}
                        {/*    <span/>*/}
                        {/*)}*/}


                        <button type="submit" className="btn btn-info m-3 w3-hover-yellow">Запази</button>
                    </form>
                ) : (
                    <span>Loading...</span>
                )

                }

            </div>
        );
    }
}
