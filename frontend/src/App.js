import React ,{ Component } from 'react';
import { Link } from 'react-router-dom';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import './App.css';
import Background  from "./Components/Background";
import registration  from "./Components/Registration";
import Login from "./Components/Login"; 
import Homepage from "./Components/Homepage"; 
import Forgotpassword from "./Components/Forgotpassword";
import Accountadmin from "./Components/AccountHomepage/Accountadmin"; 
import {Collapse, Navbar, NavbarBrand, Nav, NavItem, NavLink} from 'reactstrap';


class App extends Component {
  state = {
    checkDrawer:false
};


  render() {
    return (
      <div  style={{height:'100%'}} className="mainContainer" >
         <Navbar color='light'>
         <NavbarBrand href="/">Home</NavbarBrand>
         <Nav className="mr-auto" navbar>
         <NavItem>
              <NavLink href="/">About us</NavLink>
            </NavItem>
         </Nav>
         <Nav>
         <NavItem>
              <NavLink href="/login">Login</NavLink>
            </NavItem>
            <NavItem>
              <NavLink href="/registration">Register</NavLink>
            </NavItem>

         </Nav>
         </Navbar>
        

         
        <h1 className="h1"> Bank Saver application  </h1>
       
        <div style={{marginTop:'64px'}}>

          <Router>
                 <Switch>
                     <Route path='/' exact={true} component={Background}/>
                     <Route path='/registration' exact={true} component={registration}/>
                     <Route path='/login' exact={true} component={Login}/>
                     <Route path='/Homepage' exact={true} component={Homepage}/>
                     <Route path='/Accountadmin' exact={true} component={Accountadmin}/>
                     <Route path='/Forgotpassword' exact={true} component={Forgotpassword}/>

                </Switch>

           </Router>
       </div>
      </div>
    );
  }
}

export default App;
