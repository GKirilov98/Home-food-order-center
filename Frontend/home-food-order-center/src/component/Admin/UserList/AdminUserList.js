import React from "react";
import '../../../style/css/Admin/UserList/admin-user-list.css';

export default class AdminUserList extends React.Component {
    render() {
        return (
            <div className="container pt-2 mt-3">

                <form action="" className="pb-2">
                    <div className="row">
                        <div className="col-3">
                            <input type="text" placeholder="Username"/>
                        </div>

                        <div className="col-3">
                            <input type="text" placeholder="Email"/>
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
                            <th scope="col">Image</th>
                            <th scope="col">Username</th>
                            <th scope="col">Email</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>

                                <img
                                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
                                    alt=""
                                    className="img-thumbnail user-image-list m-0"/>
                            </td>
                            <td>Mark</td>
                            <td>Otto@abv.bg</td>
                            <td>
                                <a href="" className="btn btn-warning m-0">Edit</a>
                            </td>
                            <td>
                                <a href="" className="btn btn-info m-0">View</a>
                            </td>

                            <td>
                                <a href="" className="btn btn-danger m-0">Delete??</a>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">1</th>
                            <td>

                                <img
                                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
                                    alt=""
                                    className="img-thumbnail user-image-list m-0"/>
                            </td>
                            <td>Mark</td>
                            <td>Otto@abv.bg</td>
                            <td>
                                <a href="" className="btn btn-warning m-0">Edit</a>
                            </td>
                            <td>
                                <a href="" className="btn btn-info m-0">View</a>
                            </td>

                            <td>
                                <a href="" className="btn btn-danger m-0">Delete??</a>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">1</th>
                            <td>

                                <img
                                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
                                    alt=""
                                    className="img-thumbnail user-image-list m-0"/>
                            </td>
                            <td>Mark</td>
                            <td>Otto@abv.bg</td>
                            <td>
                                <a href="" className="btn btn-warning m-0">Edit</a>
                            </td>
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
