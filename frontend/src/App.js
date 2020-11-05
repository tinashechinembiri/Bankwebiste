import React ,{ Component } from 'react';
import { useLocation } from 'react-router-dom'
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import './App.css';
import Background  from "./Components/Background";
import registration  from "./Components/Registration";
import Login from "./Components/Login"; 
import Homepage from "./Components/Homepage"; 
import Forgotpassword from "./Components/Forgotpassword";
import Accountadmin from "./Components/AccountHomepage/Accountadmin"; 
import Loginnavabar from "./Components/MainNavbar/Loginnavabar"; 
import authenticationService from './Components/Service/authenticationService'; 



class App extends Component {
  state = {
    checkDrawer:false
};



  render() {
   authenticationService.token_expire(); 

    return (
      <div  style={{height:'100%'}} className="mainContainer" >


        <Loginnavabar/>
        
        <h1 className="h1"> Bank Saver Application  
        <img className="mainimage" src="/digitalimage.jpg" alt ="main logo"></img>
        </h1>
       
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
