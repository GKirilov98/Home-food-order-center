import React from "react";
import "../../style/css/common/header.css"
import * as Icon from 'react-bootstrap-icons';
import {Link} from "react-router-dom";

export default class Header extends React.Component{
    //$("button").click(function(){
    //   $("button").removeClass("active");
    //   $(this).addClass("active");
    // });

    constructor(props, context) {
        super(props, context);

        // this.handleClick = this.handleClick.bind(this)
        // this.sayHi = this.sayHi.bind(this);

        this.state = {
            activeIndex: null
        }
    }

    // handleClick(event){
    //
    //     // const selectedIndex = event.target.options.selectedIndex;
    //     this.setState(( prevState) =>{
    //       return{  ...prevState,
    //           activeIndex: 1 }
    //     } );
    // }


    render() {
        return (
            // <!-- Navigation -->
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top pb-3">
                    {/*<div className="container ">*/}
                    <div>
                        <a className="navbar-brand" href="#">Start Bootstrap</a>
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"/>
                        </button>
                    </div>


                    <div className="collapse navbar-collapse offset-1" id="navbarResponsive">
                        <ul className="navbar-nav ml-auto " onClick={this.handleClick}>
                            {/*<li className="nav-item active">*/}
                            {/*    <a className="nav-link" href="#">Home*/}
                            {/*        <span className="sr-only">(current)</span>*/}
                            {/*    </a>*/}
                            {/*</li>*/}
                            {/*<li className="nav-item">*/}
                            {/*    <a className="nav-link" href="#">About</a>*/}
                            {/*</li>*/}
                            {/*<li className="nav-item">*/}
                            {/*    <a className="nav-link" href="#">Services</a>*/}
                            {/*</li>*/}
                            {/*<li className="nav-item">*/}
                            {/*    <a className="nav-link" href="#">Contact</a>*/}
                            {/*</li>*/}
                            <li className="nav-item" >
                                <Link to="/auth/register" className="nav-link">
                                    <span className="pr-2">
                                         <Icon.PersonPlus size="20" />
                                    </span>
                                    Sign Up
                                </Link>
                            </li>
                            <li className="nav-item"  >
                                <Link to="/auth/login" className="nav-link">
                                     <span className="pr-2">
                                         <Icon.PersonCheck size="20" />
                                    </span>
                                    Sign In</Link>
                            </li>
                        </ul>
                    </div>
                    {/*</div>*/}
                </nav>
                <div className="p-5">
                    {/*<a className="navbar-brand" href="#">Start Bootstrap</a>*/}
                    {/*<button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">*/}
                    {/*    <span className="navbar-toggler-icon"/>*/}
                    {/*</button>*/}
                </div>

            </div>

        );
    }
}
