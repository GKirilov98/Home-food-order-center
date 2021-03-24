import React from "react";
import '../../style/css/Catalog/w3.css';
import SideBarMenu from "./SideBarMenu";
// import '../../style/css/Auth/Login/fontawesome.min.css';

export default class Catalog extends React.Component {
    render() {
        return (
            <div >
            <SideBarMenu />
            {/*<!-- Top menu on small screens -->*/}
            {/*<header className="w3-bar w3-top w3-hide-large w3-black w3-xlarge">*/}
            {/*    <div className="w3-bar-item w3-padding-24 w3-wide">LOGO</div>*/}
            {/*    <a href="javascript:void(0)" className="w3-bar-item w3-button w3-padding-24 w3-right"*/}
            {/*       onClick="w3_open()"><i className="fa fa-bars"/></a>*/}
            {/*</header>*/}

            {/*<!-- Overlay effect when opening sidebar on small screens -->*/}
            {/*<div className="w3-overlay w3-hide-large" onClick="w3_close()" title="close side menu" id="myOverlay"/>*/}

            {/*<!-- !PAGE CONTENT! -->*/}
            <div className="w3-main" id="divPageContent">

                {/*<!-- Push down content on small screens -->*/}

                <div className="w3-hide-large" id="pushDownContent" />



                <div className="w3-container w3-text-grey" id="jeans">
                    <p>8 items</p>
                </div>

                {/*<!-- Product grid -->*/}
                <div className="w3-row w3-grayscale">
                    <div className="w3-col l3 s6">
                        <div className="w3-container">

                            <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" className="productGrid" />
                                <p>Ripped Skinny Jeans<br /><b>$24.99</b></p>
                        </div>
                        <div className="w3-container">
                            <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" className="productGrid" />
                                <p>Mega Ripped Jeans<br /><b>$19.99</b></p>
                        </div>
                    </div>

                    <div className="w3-col l3 s6">
                        <div className="w3-container">
                            <div className="w3-display-container">
                                {/* eslint-disable-next-line react/style-prop-object */}
                                <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" className="productGrid" />
                                    <span className="w3-tag w3-display-topleft">New</span>
                                    <div className="w3-display-middle w3-display-hover">
                                        <button className="w3-button w3-black">Buy now <i className="fa fa-shopping-cart"/></button>
                                    </div>
                            </div>
                            <p>Mega Ripped Jeans<br /><b>$19.99</b></p>
                        </div>
                        <div className="w3-container">
                            {/* eslint-disable-next-line react/style-prop-object */}
                            <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" className="productGrid" />
                                <p>Washed Skinny Jeans<br /><b>$20.50</b></p>
                        </div>
                    </div>

                    <div className="w3-col l3 s6">
                        <div className="w3-container">
                            {/* eslint-disable-next-line react/style-prop-object */}
                            <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" className="productGrid" />
                                <p>Washed Skinny Jeans<br /><b>$20.50</b></p>
                        </div>
                        <div className="w3-container">
                            <div className="w3-display-container">
                                {/* eslint-disable-next-line react/style-prop-object */}
                                <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" className="productGrid" />
                                    <span className="w3-tag w3-display-topleft">Sale</span>
                                    <div className="w3-display-middle w3-display-hover">
                                        <button className="w3-button w3-black">Buy now <i className="fa fa-shopping-cart"/></button>
                                    </div>
                            </div>
                            <p>Vintage Skinny Jeans<br /><b className="w3-text-red">$14.99</b></p>
                        </div>
                    </div>

                    <div className="w3-col l3 s6">
                        <div className="w3-container">
                            <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" className="productGrid" />
                                <p>Vintage Skinny Jeans<br /><b>$14.99</b></p>
                        </div>
                        <div className="w3-container">
                            <img src="https://www.delonghi.com/Global/recipes/multifry/3.jpg " className="productGrid" />
                                <p>Ripped Skinny Jeans<br /><b>$24.99</b></p>
                        </div>
                    </div>
                </div>

                {/*/!*<!-- Subscribe section -->*!/*/}
                {/*<div className="w3-container w3-black w3-padding-32">*/}
                {/*    <h1>Subscribe</h1>*/}
                {/*    <p>To get special offers and VIP treatment:</p>*/}
                {/*    /!* eslint-disable-next-line react/style-prop-object *!/*/}
                {/*    <p><input className="w3-input w3-border" type="text" placeholder="Enter e-mail" className"productGrid />*/}
                {/*    </p>*/}
                {/*    <button type="button" className="w3-button w3-red w3-margin-bottom">Subscribe</button>*/}
                {/*</div>*/}

                {/*/!*<!-- Footer -->*!/*/}
                {/*<footer className="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">*/}
                {/*    <div className="w3-row-padding">*/}
                {/*        <div className="w3-col s4">*/}
                {/*            <h4>Contact</h4>*/}
                {/*            <p>Questions? Go ahead.</p>*/}
                {/*            <form action="/action_page.php" target="_blank">*/}
                {/*                <p><input className="w3-input w3-border" type="text" placeholder="Name" name="Name"*/}
                {/*                          required /></p>*/}
                {/*                <p><input className="w3-input w3-border" type="text" placeholder="Email" name="Email"*/}
                {/*                          required /></p>*/}
                {/*                <p><input className="w3-input w3-border" type="text" placeholder="Subject"*/}
                {/*                          name="Subject" required /></p>*/}
                {/*                <p><input className="w3-input w3-border" type="text" placeholder="Message"*/}
                {/*                          name="Message" required /></p>*/}
                {/*                <button type="submit" className="w3-button w3-block w3-black">Send</button>*/}
                {/*            </form>*/}
                {/*        </div>*/}

                {/*        <div className="w3-col s4">*/}
                {/*            <h4>About</h4>*/}
                {/*            <p><a href="#">About us</a></p>*/}
                {/*            <p><a href="#">We're hiring</a></p>*/}
                {/*            <p><a href="#">Support</a></p>*/}
                {/*            <p><a href="#">Find store</a></p>*/}
                {/*            <p><a href="#">Shipment</a></p>*/}
                {/*            <p><a href="#">Payment</a></p>*/}
                {/*            <p><a href="#">Gift card</a></p>*/}
                {/*            <p><a href="#">Return</a></p>*/}
                {/*            <p><a href="#">Help</a></p>*/}
                {/*        </div>*/}

                {/*        <div className="w3-col s4 w3-justify">*/}
                {/*            <h4>Store</h4>*/}
                {/*            <p><i className="fa fa-fw fa-map-marker"></i> Company Name</p>*/}
                {/*            <p><i className="fa fa-fw fa-phone"></i> 0044123123</p>*/}
                {/*            <p><i className="fa fa-fw fa-envelope"></i> ex@mail.com</p>*/}
                {/*            <h4>We accept</h4>*/}
                {/*            <p><i className="fa fa-fw fa-cc-amex"></i> Amex</p>*/}
                {/*            <p><i className="fa fa-fw fa-credit-card"></i> Credit Card</p>*/}
                {/*            <br />*/}
                {/*                <i className="fa fa-facebook-official w3-hover-opacity w3-large"></i>*/}
                {/*                <i className="fa fa-instagram w3-hover-opacity w3-large"></i>*/}
                {/*                <i className="fa fa-snapchat w3-hover-opacity w3-large"></i>*/}
                {/*                <i className="fa fa-pinterest-p w3-hover-opacity w3-large"></i>*/}
                {/*                <i className="fa fa-twitter w3-hover-opacity w3-large"></i>*/}
                {/*                <i className="fa fa-linkedin w3-hover-opacity w3-large"></i>*/}
                {/*        </div>*/}
                {/*    </div>*/}
                {/*</footer>*/}

                {/*<div className="w3-black w3-center w3-padding-24">Powered by <a*/}
                {/*    href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank"*/}
                {/*    className="w3-hover-opacity">w3.css</a></div>*/}

                {/*<!-- End page content -->*/}
            </div>

            {/*<!-- Newsletter Modal -->*/}
            {/*<div id="newsletter" className="w3-modal">*/}
            {/*    /!* eslint-disable-next-line react/style-prop-object *!/*/}
            {/*    <div className="w3-modal-content w3-animate-zoom" style="padding:32px">*/}
            {/*        <div className="w3-container w3-white w3-center">*/}
            {/*            <i onClick="document.getElementById('newsletter').style.display='none'"*/}
            {/*               className="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i>*/}
            {/*            <h2 className="w3-wide">NEWSLETTER</h2>*/}
            {/*            <p>Join our mailing list to receive updates on new arrivals and special offers.</p>*/}
            {/*            <p><input className="w3-input w3-border" type="text" placeholder="Enter e-mail" /></p>*/}
            {/*            <button type="button" className="w3-button w3-padding-large w3-red w3-margin-bottom"*/}
            {/*                    onClick="document.getElementById('newsletter').style.display='none'">Subscribe*/}
            {/*            </button>*/}
            {/*        </div>*/}
            {/*    </div>*/}
            {/*</div>*/}

            </div>

        );
    }
}
