import React from "react";

export default class AboutUs extends React.Component{
    render() {
        return (
            <div className="container bg-white">
                <div className="m-5 pb-5 pt-3">
                    <h2><strong>Home Food Center</strong></h2>
                    <hr/>
                    <p>
                        Това е проект към курса Spring Advanced - февруари 2021, <br/>
                        Дата на изпита е  18.04.2021г. <br/>
                        За front-end  е използван React JS <br/>
                        За back-end е използван JAVA Spring <br/>
                        За база данни се изполва MySQL 8.0<br/>
                        Ауторизацията между front-end и back-end става чрез jwt токен<br/>
                        Свързан е с Cloudinary.<br/>
                    </p>
                </div>

            </div>
        );
    }
}
