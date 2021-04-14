import React from 'react';
import {Link} from "react-router-dom";
import frontendUtils from "../../utils/frontendUtils";

export default class Home extends React.Component {
    render() {
        return (
            <div className="w3-display-container w3-container">
                <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" alt="pizza" id="imageHeader"/>
                <div className="w3-display-topleft w3-text-white" id="divImageHeader">
                    <h1 className="w3-jumbo w3-hide-small text-dark">Гладен ли си?</h1>
                    <h1 className="w3-hide-small text-dark">Нека те нахраним!</h1>
                    <p>
                        <Link to={frontendUtils.CATALOG_PATH} className="w3-button w3-black w3-padding-large w3-large">Нахрани ме</Link>
                    </p>
                </div>
            </div>
        );
    }
}
