import React from "react";
import constants from "../../../utils/constants";
import backend from "../../../utils/backendUtils";
import frontend from "../../../utils/frontendUtils";
import {Loading} from "notiflix";
import frontendUtils from "../../../utils/frontendUtils";

export default class ProductCreate extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleUpload = this.handleUpload.bind(this)
        this.handleDelete = this.handleDelete.bind(this)
        this.callQuery = this.callQuery.bind(this);
        this.state = {
            categories: null,
            subcategories: [],
            fillData: false,
            selectedCategory: null,
            selectedSubcategory: null,
            imgSrc: null,
            imgUrl: constants.DEFAULT_IMG_URL,
            imgPublicId: null,
            productName: null,
            descriptionName: null,
            availableQuantity: null,
            unitPrice: null,
            deleteButton: false,
            deleteActive: false,
            uploadButton: true,
            uploadActive: false
        }
    }

    static getDerivedStateFromProps(props, state){
        if (sessionStorage.getItem(constants.USERNAME_KEY_NAME) == null){
            props.history.push(frontend.LOGIN_PATH);
        } else {
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
        backend.REQ_GET(backend.CATEGORY_GETALL_URL)
            .then(res => res.json())
            .then(res => {

                this.setState({
                    categories: res,
                    fillData: true
                })
            });
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
                        uploadActive: true
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
            category: this.state.selectedCategory,
            subcategory: this.state.selectedSubcategory,
            imgUrl: this.state.imgUrl,
            imgPublicId: this.state.imgPublicId,
            name: this.state.productName,
            description: this.state.descriptionName,
            availableQuantity: this.state.availableQuantity,
            unitPrice: this.state.unitPrice,
        }

        backend.REQ_POST(backend.ADMIN_PRODUCT_CREATE_URL, bodyData)
            .then(() => {
                Loading.Remove();
                frontend.notifyInfo("Register product success!")
                this.props.history.push(frontend.CATALOG_PATH);
            }).catch((error) => error)
    }

    handleUpload(event) {
        if (this.state.uploadActive) {
            this.setState((prevsState) => {
                return {
                    ...prevsState,
                    uploadActive: false
                }
            })

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
                            deleteButton: !prevState.deleteButton,
                            deleteActive: true,
                            uploadButton: !prevState.uploadButton,
                            uploadActive: true
                        };
                    })
                });
        }
    }

    handleDelete(event) {
        this.setState((prevsState) => {
            return {
                ...prevsState,
                deleteActive: false
            }
        })
        const img = {
            imgPath: this.state.imgUrl,
            publicId: this.state.imgPublicId
        }

        backend.REQ_POST(backend.IMAGE_DELETE_URL, img)
            .then(res => {
                this.setState((prevState) => {
                    return {
                        ...prevState,
                        imgUrl: constants.DEFAULT_IMG_URL,
                        deleteButton: !prevState.deleteButton,
                        deleteActive: false,
                        uploadButton: !prevState.uploadButton,
                        uploadActive: true
                    };
                })
            })
    }

    render() {
        let uploadButtonClass = null;
        if (this.state.uploadButton) {
            if (this.state.uploadActive) {
                uploadButtonClass = "btn btn-info";
            } else {
                uploadButtonClass = "btn btn-info disabled";
            }
        } else {
            uploadButtonClass = "btn btn-info display-none";
        }

        let deleteButtonClass = null;
        if (this.state.deleteButton) {
            if (this.state.deleteActive) {
                deleteButtonClass = "btn btn-danger";
            } else {
                deleteButtonClass = "btn btn-danger disabled";
            }
        } else {
            deleteButtonClass = "btn btn-danger display-none";
        }

        return (
            <div className="container col-5 bg-white">
                <form onSubmit={this.handleSubmit}>
                    <h3 className='pt-1'>CREATE PRODUCT</h3>
                    <div className="row pb-2">
                        <div className="col">
                            <img className="p-2 image-product" src={this.state.imgUrl} alt="Image product"/>
                            <div className="row">
                                <div className="col">
                                    <input type="file" name={constants.IMAGE_NAME} onChange={this.handleChange}/>
                                </div>
                                <div className="col-3 pt-1">
                                    <button type="button" className={uploadButtonClass}
                                            onClick={this.handleUpload}> Upload
                                    </button>
                                    <button type="button" className={deleteButtonClass}
                                            onClick={this.handleDelete}> Delete
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    {this.state.deleteActive ? (
                        <div className="row">
                            <div className="col">
                                <div className="tab-content profile-tab" id="myTabContent">
                                    <div className="tab-pane fade show active" id="home" role="tabpanel"
                                         aria-labelledby="home-tab">
                                        <div className="row pb-2">
                                            {/*<div className="col-md-3">*/}
                                            {/*    <label htmlFor={constants.NAME_NAME}>Name</label>*/}
                                            {/*</div>*/}
                                            <div className="col-md mt-0">
                                                <input type="text" id={constants.NAME_NAME} name={constants.NAME_NAME}
                                                       required onChange={this.handleChange}
                                                       min='3' max='25'
                                                       placeholder="Name of product"/>
                                            </div>
                                        </div>

                                        <div className="row pb-2">
                                            <div className="col-md mt-0">
                                                <div>
                                                    {this.state.fillData ? (
                                                        <div className="row">
                                                            <div className="col">
                                                                <select onChange={this.handleChange}
                                                                        name={constants.CATEGORY_NAME}>
                                                                    <option value="-1">Select Category</option>
                                                                    {
                                                                        this.state.categories.map((value, index) => {
                                                                            return <option
                                                                                value={index}>{value.name}</option>
                                                                        })
                                                                    }
                                                                </select>
                                                            </div>
                                                            <div className="col">
                                                                <select onChange={this.handleChange}
                                                                        name={constants.SUBCATEGORY_NAME}>
                                                                    <option value="-1">Select Subcategory</option>
                                                                    {
                                                                        this.state.subcategories.map((value, index) => {
                                                                            return <option
                                                                                value={index}>{value.name}</option>
                                                                        })
                                                                    }
                                                                </select>
                                                            </div>
                                                        </div>
                                                    ) : (<span/>)}
                                                </div>
                                            </div>
                                        </div>


                                        <div className="row pb-1">
                                            {/*<div className="col-md-3">*/}
                                            {/*    <label htmlFor={constants.DESCRIPTION_NAME}>Description</label>*/}
                                            {/*</div>*/}
                                            <div className="col-md mt-0">
                                            <textarea id={constants.DESCRIPTION_NAME} name={constants.DESCRIPTION_NAME}
                                                      minLength='50' maxLength='3999'
                                                      required onChange={this.handleChange}
                                                      placeholder="Description"/>
                                            </div>
                                        </div>

                                        <div className="row pb-2">
                                            {/*<div className="col-md-3">*/}
                                            {/*    <label htmlFor={constants.AVAILABLE_QUANTITY_NAME}*/}
                                            {/*    >Max Quantity</label>*/}
                                            {/*</div>*/}
                                            <div className="col-md mt-0">
                                                <input type="number" id={constants.AVAILABLE_QUANTITY_NAME}
                                                       min='1'
                                                       name={constants.AVAILABLE_QUANTITY_NAME} required
                                                       onChange={this.handleChange}
                                                       placeholder="Quantity"/>
                                            </div>
                                        </div>

                                        <div className="row">
                                            {/*<div className="col-md-3">*/}
                                            {/*    <label htmlFor={constants.UNIT_PRICE_NAME}>Unit price</label>*/}
                                            {/*</div>*/}
                                            <div className="col-md mt-0">
                                                <input type="number" id={constants.UNIT_PRICE_NAME}
                                                       min='0.1'
                                                       name={constants.UNIT_PRICE_NAME} required
                                                       onChange={this.handleChange} step="any"
                                                       placeholder="Unit Price"/>
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                    ) : (
                        <span/>
                    )}

                    {this.state.deleteActive ? (
                        <button type="submit" className="btn btn-info m-3 w3-hover-yellow">Create</button>
                    ) : (
                        <span/>
                    )}


                </form>
            </div>
        );
    }

}
