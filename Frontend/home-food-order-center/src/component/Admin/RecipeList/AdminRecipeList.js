import React from "react";

export default class AdminRecipeList extends React.Component{
    render() {
        return (
            <div className="container pt-2 mt-3">

                <form action="" className="pb-2">
                    <div className="row">
                        <div className="col-3">
                            <input type="text" placeholder="Username"/>
                        </div>

                        <div className="col-3">
                            <input type="text" placeholder="Recipe ID"/>
                        </div>

                        <div className="col-4">
                            <label className="m-0">Order Date</label>
                            <input type="date" placeholder="Order Date"/>
                        </div>

                        <div className="col">
                            <label htmlFor="">Order by</label>
                            <select className="form-select" aria-label="Default select example">
                                <option value="1" selected>One Ascending</option>
                                <option value="2" selected>One Descending</option>
                                <option value="3" selected>Two Ascending</option>
                                <option value="4" selected>Two Descending</option>
                            </select>
                        </div>
                        <div className="col">
                            <button className="btn w3-hover-light-green w3-green">Search</button>
                        </div>
                    </div>
                </form>


                <div className="row">
                    <table className="table table-hover">
                        <thead className="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Username</th>
                            <th scope="col">Recipe ID</th>
                            <th scope="col">Order Date</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>RecipeId-1123123</td>
                            <td>19-02-2021 13:32</td>
                            <td>
                                <a href="" className="btn btn-info m-0">View</a>
                            </td>

                            <td>
                                <a href="" className="btn btn-danger m-0">Delete??</a>
                            </td>
                        </tr>

                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>RecipeId-1123123</td>
                            <td>19-02-2021 13:32</td>
                            <td>
                                <a href="" className="btn btn-info m-0">View</a>
                            </td>

                            <td>
                                <a href="" className="btn btn-danger m-0">Delete??</a>
                            </td>
                        </tr>

                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>RecipeId-1123123</td>
                            <td>19-02-2021 13:32</td>
                            <td>
                                <a href="" className="btn btn-info m-0">View</a>
                            </td>

                            <td>
                                <a href="" className="btn btn-danger m-0">Delete??</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <nav aria-label="Page navigation example">
                    <ul className="pagination">
                        <li className="page-item"><a className="page-link" href="#">Previous</a></li>
                        <li className="page-item"><a className="page-link" href="#">1</a></li>
                        <li className="page-item"><a className="page-link" href="#">2</a></li>
                        <li className="page-item"><a className="page-link" href="#">3</a></li>
                        <li className="page-item"><a className="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </div>
        );
    }

}
