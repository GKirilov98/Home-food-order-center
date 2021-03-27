import './App.css';
// import Login from "./component/Auth/Login/Login";
import Catalog from "./component/Catalog/Catalog";
import Header from "./component/common/Header";
import Footer from "./component/common/Footer";
import Login from "./component/Auth/Login/Login";
import Register from "./component/Auth/Register/Register";
import Home from "./component/Home/Home";
import ProductDetails from "./component/Product/ProductDetails";
import Cart from "./component/Cart/Cart";
import Payment from "./component/Payment/Payment";
import UserProfile from "./component/User/Profile/UserProfile";
import UserEdit from "./component/User/Edit/UserEdit";
import AdminUserList from "./component/Admin/UserList/AdminUserList";
import AdminRecipeList from "./component/Admin/RecipeList/AdminRecipeList";
import ReceiptView from "./component/Receipt/ReceiptView";

import {Route} from "react-router-dom";


function App() {
  return  (
      <div>
          <Header/>
          <Route path="/" exact component={Home} />
          <Route path="/home" exact component={Home} />
          <Route path="/auth/register" exact component={Register} />
          <Route path="/auth/login" exact component={Login} />
          <Route path="/product/listAll" exact component={Catalog} />


          {/*<ReceiptView />*/}
          {/*<AdminRecipeList />*/}
          {/*<AdminUserList />*/}
          {/*<UserEdit />*/}
          {/*<UserProfile />*/}
          {/*<Payment />*/}
          {/*<Home />*/}
          {/*<Login />*/}
          {/*<Register />*/}
          {/*<Catalog/>*/}
          {/*<ProductDetails />*/}
          {/*<Cart />*/}
          <Footer />
      </div>

  );
}

export default App;
