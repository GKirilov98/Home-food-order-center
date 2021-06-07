import React from "react";
import constants from "../../../utils/constants";
import backend from "../../../utils/backendUtils";
import frontend from "../../../utils/frontendUtils";
import {Loading} from "notiflix";
import frontendUtils from "../../../utils/frontendUtils";
import messagesUi from "../../../utils/messages-ui";

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
                if (index != -1){
                    let subcategoriesS = this.state.categories[index].subcategories;
                    this.setState((prevState) => {
                        return {
                            ...prevState,
                            selectedCategory: prevState.categories[index].id,
                            subcategories: subcategoriesS,
                            selectedSubcategory: null
                        }
                    })
                } else {
                    this.setState((prevState) => {
                        return {
                            ...prevState,
                            selectedCategory: null,
                            subcategories: [],
                            selectedSubcategory: null
                        }
                    })
                }

                break;
            case constants.SUBCATEGORY_NAME:
                index = event.target.value;
                if (index != -1) {
                    this.setState((prevState) => {
                        return {
                            ...prevState,
                            selectedSubcategory: prevState.subcategories[index].id
                        }
                    })
                } else {
                    this.setState((prevState) => {
                        return {
                            ...prevState,
                            selectedSubcategory: null
                        }
                    })
                }
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

        for (const key in bodyData) {
            if (bodyData[key] == null) {
                switch (key) {
                    case 'category': frontendUtils.notifyError("Категория полето е задължително!"); break;
                    case 'subcategory': frontendUtils.notifyError("Подкатегория полето е задължително!"); break;
                    case 'imgUrl': frontendUtils.notifyError("Снимка полето е задължително!"); break;
                    case 'name': frontendUtils.notifyError("Името полето е задължително!"); break;
                    case 'description': frontendUtils.notifyError("Описание полето е задължително!"); break;
                    case 'availableQuantity': frontendUtils.notifyError("Налично количество полето е задължително!"); break;
                    case 'unitPrice': frontendUtils.notifyError("Единична цена полето е задължително!"); break;
                }

                Loading.Remove();
                return;
            }
        }

        backend.REQ_POST(backend.BUSINESS_PRODUCT_CREATE_URL, bodyData)
            .then(() => {
                Loading.Remove();
                frontend.notifyInfo(messagesUi.CREATE_PRODUCT_SUCCESS)
                this.props.history.push(frontend.CATALOG_PATH);
            }).catch(err => {

                if (err.message === '401') {
                    sessionStorage.clear();
                    frontendUtils.notifyError(messagesUi.INVALID_SESSION);
                    this.props.history.push(frontendUtils.LOGIN_PATH);
                }
            }
        );
    }

    handleUpload() {
        if (this.state.uploadActive) {
            Loading.Standard('Loading...',);
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
                        Loading.Remove();
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
                }).catch(err => {

                    if (err.message === '401') {
                        sessionStorage.clear();
                        frontendUtils.notifyError(messagesUi.INVALID_SESSION);
                        this.props.history.push(frontendUtils.LOGIN_PATH);
                    }
                }
            );
        }
    }

    handleDelete() {
        this.setState((prevsState) => {
            Loading.Standard('Loading...',);
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
                    Loading.Remove();
                    return {
                        ...prevState,
                        imgUrl: constants.DEFAULT_IMG_URL,
                        deleteButton: !prevState.deleteButton,
                        deleteActive: false,
                        uploadButton: !prevState.uploadButton,
                        uploadActive: true
                    };
                })
            }).catch(err => {

                if (err.message === '401') {
                    sessionStorage.clear();
                    frontendUtils.notifyError(messagesUi.INVALID_SESSION);
                    this.props.history.push(frontendUtils.LOGIN_PATH);
                }
            }
        );
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
                    <h3 className='pt-1'>Създай продукт</h3>
                    <div className="row pb-2">
                        <div className="col">
                            <img className="p-2 image-product" src={this.state.imgUrl} alt="Име на продукта"/>
                            <div className="row">
                                <div className="col">
                                    <input type="file" name={constants.IMAGE_NAME} onChange={this.handleChange}/>
                                </div>
                                <div className="col-3 pt-1">
                                    <button type="button" className={uploadButtonClass}
                                            onClick={this.handleUpload}> Качи
                                    </button>
                                    <button type="button" className={deleteButtonClass}
                                            onClick={this.handleDelete}> Изтрий
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
                                            <div className="col-md mt-0">
                                                <input type="text" id={constants.NAME_NAME} name={constants.NAME_NAME}
                                                       required onChange={this.handleChange}
                                                       minLength='3' maxLength='50'
                                                       placeholder="Име на продукта"/>
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
                                                                    <option value="-1">Избери Категория</option>
                                                                    {
                                                                        this.state.categories.map((value, index) => {
                                                                            return <option value={index}>
                                                                                {value.name}</option>
                                                                        })
                                                                    }
                                                                </select>
                                                            </div>
                                                            <div className="col">
                                                                <select onChange={this.handleChange}
                                                                        name={constants.SUBCATEGORY_NAME}>
                                                                    <option value="-1">Избери Подкатегория</option>
                                                                    {
                                                                        this.state.subcategories.map((value, index) => {
                                                                            return <option value={index}>
                                                                                {value.name}</option>
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
                                            <div className="col-md mt-0">
                                            <textarea id={constants.DESCRIPTION_NAME} name={constants.DESCRIPTION_NAME}
                                                      minLength='50' maxLength='3999' required
                                                      onChange={this.handleChange}
                                                      placeholder="Описание"/>
                                            </div>
                                        </div>
                                        <div className="row pb-2">
                                            <div className="col-md mt-0">
                                                <input type="number" id={constants.AVAILABLE_QUANTITY_NAME}
                                                       min='1'
                                                       name={constants.AVAILABLE_QUANTITY_NAME} required
                                                       onChange={this.handleChange}
                                                       placeholder="Количество"/>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-md mt-0">
                                                <input type="number" id={constants.UNIT_PRICE_NAME}
                                                       min='0.1'
                                                       name={constants.UNIT_PRICE_NAME} required
                                                       onChange={this.handleChange} step="any"
                                                       placeholder="Единична цена"/>
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
                        <button type="submit" className="btn btn-info m-3 w3-hover-yellow">Създай</button>
                    ) : (
                        <span/>
                    )}
                </form>
            </div>
        );
    }

}
