import React from "react";

export default class ProductDetails extends React.Component {
    render() {
        return (
            <div>
                <div className="container bg-white mb-4 mt-3">
                    {/*<div className="row">*/}
                    <div className="col">
                        <img className="m-2 rounded img-thumbnail"
                             src="https://www.delonghi.com/Global/recipes/multifry/3.jpg" alt=""/>
                    </div>
                    <div className="col pr-5">
                        <h2>Name of product</h2>
                        <p>Текстът Lorem ipsum е безсмислен, защото е (непълно) извлечен от пасаж на Цицерон, и затова
                            визуално много прилича на истински – заради разпределението и честотата на срещане на
                            по-къси, средни и дълги думи, разпределението на интервалите и препинателните знаци, както и
                            дължината на изреченията. Разнообразието му осигурява, че ако се случи да бъде размножен, за
                            да изпълни предвиденото пространство, да не се получат шарки от сорта на моарето вследствие
                            еднотипно редуване на думи и интервали. „Налят“ по този начин в графичните блокове, Lorem
                            ipsum позволява на окото да се абстрахира от конкретиката на смисления текст, и да се
                            съсредоточи само върху особеностите на използвания шрифт и неговото визуално въздействие
                            върху читателя.</p>

                        <div className="col">
                            <h3>Price ??,??</h3>
                        </div>
                        <div className="col border m-3 pb-3">
                            <form>
                                <p>Max quuantity ??</p>
                                <input type="number" className="form-control" placeholder="qunatity"/>
                                <button className="btn w3-hover-blue">Add to Cart</button>
                            </form>
                            <a href="#" className="btn-info btn"> Back to Catalog</a>
                        </div>

                    </div>
                    {/*</div>*/}

                </div>

            </div>

        );
    }


    // render() {
    //     return (
    //         <div>
    //             <div className="container">
    //                 <br/><p className="text-center">More bootstrap 4 components on <a
    //                 href="http://bootstrap-ecommerce.com/"> Bootstrap-ecommerce.com</a></p>
    //                 <hr/>
    //                 <div className="card">
    //                     <div className="row">
    //                         <aside className="col-sm-5 border-right">
    //                             <article className="gallery-wrap">
    //                                 <div className="img-big-wrap">
    //                                     <div><a href="#"><img src="https://s9.postimg.org/tupxkvfj3/image.jpg"/></a>
    //                                     </div>
    //                                 </div>
    //                                 {/*<!-- slider-product.// -->*/}
    //                                 <div className="img-small-wrap">
    //                                     <div className="item-gallery"><img
    //                                         src="https://s9.postimg.org/tupxkvfj3/image.jpg"/></div>
    //                                     <div className="item-gallery"><img
    //                                         src="https://s9.postimg.org/tupxkvfj3/image.jpg"/></div>
    //                                     <div className="item-gallery"><img
    //                                         src="https://s9.postimg.org/tupxkvfj3/image.jpg"/></div>
    //                                     <div className="item-gallery"><img
    //                                         src="https://s9.postimg.org/tupxkvfj3/image.jpg"/></div>
    //                                 </div>
    //                                 {/*<!-- slider-nav.// -->*/}
    //                             </article>
    //                             {/*<!-- gallery-wrap .end// -->*/}
    //                         </aside>
    //                         <aside className="col-sm-7">
    //                             <article className="card-body p-5">
    //                                 <h3 className="title mb-3">Original Version of Some product name</h3>
    //
    //                                 <p className="price-detail-wrap">
    // <span className="price h3 text-warning">
    // 	<span className="currency">US $</span><span className="num">1299</span>
    // </span>
    //                                     <span>/per kg</span>
    //                                 </p>
    //                                 {/*<!-- price-detail-wrap .// -->*/}
    //                                 <dl className="item-property">
    //                                     <dt>Description</dt>
    //                                     <dd><p>Here goes description consectetur adipisicing elit, sed do
    //                                         eiusmod
    //                                         tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
    //                                         veniam,
    //                                         quis nostrud exercitation ullamco </p></dd>
    //                                 </dl>
    //                                 <dl className="param param-feature">
    //                                     <dt>Model#</dt>
    //                                     <dd>12345611</dd>
    //                                 </dl>
    //                                 {/*<!-- item-property-hor .// -->*/}
    //                                 <dl className="param param-feature">
    //                                     <dt>Color</dt>
    //                                     <dd>Black and white</dd>
    //                                 </dl>
    //                                 {/* <!-- item-property-hor .// -->*/}
    //                                 <dl className="param param-feature">
    //                                     <dt>Delivery</dt>
    //                                     <dd>Russia, USA, and Europe</dd>
    //                                 </dl>
    //                                 {/* <!-- item-property-hor .// -->*/}
    //
    //                                 <hr/>
    //                                 <div className="row">
    //                                     <div className="col-sm-5">
    //                                         <dl className="param param-inline">
    //                                             <dt>Quantity:</dt>
    //                                             <dd>
    //                                                 <select className="form-control form-control-sm">
    //                                                     <option> 1</option>
    //                                                     <option> 2</option>
    //                                                     <option> 3</option>
    //                                                 </select>
    //                                             </dd>
    //                                         </dl>
    //                                         {/*<!-- item-property .// -->*/}
    //                                     </div>
    //                                     {/*/!*<!-- col.// -->*!/*/}
    //                                     {/*<div className="col-sm-7">*/}
    //                                     {/*    <dl className="param param-inline">*/}
    //                                     {/*        <dt>Size:</dt>*/}
    //                                     {/*        <dd>*/}
    //                                     {/*            <label className="form-check form-check-inline">*/}
    //                                     {/*                <input className="form-check-input" type="radio"*/}
    //                                     {/*                       name="inlineRadioOptions" id="inlineRadio2"*/}
    //                                     {/*                       value="option2"/>*/}
    //                                     {/*                    <span className="form-check-label">SM</span>*/}
    //                                     {/*            </label>*/}
    //                                     {/*            <label className="form-check form-check-inline">*/}
    //                                     {/*                <input className="form-check-input" type="radio"*/}
    //                                     {/*                       name="inlineRadioOptions" id="inlineRadio2"*/}
    //                                     {/*                       value="option2">*/}
    //                                     {/*                    <span className="form-check-label">MD</span>*/}
    //                                     {/*            </label>*/}
    //                                     {/*            <label className="form-check form-check-inline">*/}
    //                                     {/*                <input className="form-check-input" type="radio"*/}
    //                                     {/*                       name="inlineRadioOptions" id="inlineRadio2"*/}
    //                                     {/*                       value="option2">*/}
    //                                     {/*                    <span className="form-check-label">XXL</span>*/}
    //                                     {/*            </label>*/}
    //                                     {/*        </dd>*/}
    //                                     {/*    </dl>*/}
    //                                     {/*    /!* <!-- item-property .// -->*!/*/}
    //                                     {/*</div>*/}
    //                                     {/*<!-- col.// -->*/}
    //                                 </div>
    //                                 {/*<!-- row.// -->*/}
    //                                 <hr/>
    //                                 <a href="#" className="btn btn-lg btn-primary text-uppercase"> Buy
    //                                     now </a>
    //                                 <a href="#"
    //                                    className="btn btn-lg btn-outline-primary text-uppercase"> <i
    //                                     className="fas fa-shopping-cart"/> Add to cart </a>
    //
    //                             </article>
    //                             {/*<!-- card-body.// -->*/}
    //                         </aside>
    //                         {/* <!-- col.// -->*/}
    //                     </div>
    //                     {/* <!-- row.// -->*/}
    //                 </div>
    //                 {/*<!-- card.// -->*/}
    //
    //
    //             </div>
    //             {/*<!--container.//-->*/
    //             }
    //         </div>
    //     );
    // }
}
