import React from "react";

export default class AboutUs extends React.Component{
    render() {
        return (
            <div className="container bg-white">
                <div className="m-5 pb-5 pt-3">
                    <h2><strong>Home Food Center</strong></h2>
                    <hr/>
                    <p>
                        Това е дипломен проект към УНСС<br/>
                        Изготвил: Георги Кирилов <br/>
                        Факултетен номер: 17118085 <br />
                        За front-end  е използван React JS <br/>
                        За back-end е използван JAVA Spring <br/>
                        За база данни се изполва MS SQL Server 2019<br/>
                        Ауторизацията между front-end и back-end става чрез jwt токен<br/>
                        Свързан е с Cloudinary.<br/>
                    </p>
                </div>

            </div>
        );
    }
}
