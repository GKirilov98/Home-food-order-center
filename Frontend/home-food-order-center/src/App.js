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


function App() {
  return  (
      <body className="app-bg">
        <Header/>
        <Payment />
        {/*<Home />*/}
        {/*<Login />*/}
        {/*<Register />*/}
        {/*<Catalog/>*/}
        {/*<ProductDetails />*/}
        {/*<Cart />*/}
        <Footer />
      </body>

      // <Home />
  // <Login />

  // return (
  //   <div className="App">
  //     <header className="App-header">
  //       <img src={logo} className="App-logo" alt="logo" />
  //       <p>
  //         Edit <code>src/App.js</code> and save to reload.
  //       </p>
  //       <a
  //         className="App-link"
  //         href="https://reactjs.org"
  //         target="_blank"
  //         rel="noopener noreferrer"
  //       >
  //         Learn React
  //       </a>
  //     </header>
  //   </div>
  );
}

export default App;
