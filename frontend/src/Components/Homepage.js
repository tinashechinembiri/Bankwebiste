import React, { Component } from 'react';
//import { METHODS } from 'http';
import jwt_decode from 'jwt-decode';
import Navbar from "./NavBar/Navbar"; 
import './Css/Homepagemain.css';
import Accountadmin from "./AccountHomepage/Accountadmin"
import AccountSummary from "./AccountHomepage/AccountSummary"
import Statements from "./AccountHomepage/Statements"
import Paymenttransfer from "./AccountHomepage/Paymenttransfer"


import  { Redirect } from 'react-router-dom'

class Homepage extends Component
{
    constructor(props)
    {
        super(props); 
        
        this.state = {
            data: [],
            SelectName:'', 
            
        }
    }

    handleFieldchange = (value) => {
        this.setState({ SelectName: value})
    }
    
    CallApi =() => 
    {

        let token = localStorage.getItem('User') || null; 
       let  token_1 = token; 
      

        if (token)
        {

            token_1.replace("Bearer ", ''); 
        
            var decoded = jwt_decode(token_1);

            return fetch ('/ApiAutomationHost/rest/secured/account/'+decoded.jti,
            {
                headers: {
                method: 'GET', 
                Accept: 'application/json',
                'Authorization':token, 
                }

            }).then(res => res.json())
            .then(data => 
                this.setState({data},console.table(data))

                ); 
        }
    }
    token_expire = () => 
    {
        let token = localStorage.getItem('User') || null; 
        var decoded = jwt_decode(token); 
        var time_now  = (new Date()).getTime();
        if (decoded.exp < time_now)
        {
             localStorage.removeItem('user'); 
             localStorage.clear(); 
        }
    }

    componentDidMount()
    {
        this.CallApi(); 
       
    }
  
    render()
    {
       
        let value = this.state.data; 
        let sidebar ; 
        console.log(value )
        
        if (localStorage.getItem('User') ===  null)
        {
            return <Redirect to='/login' ></Redirect> ; 
        }
        else     
        sidebar = <Navbar onSelectLanguage={this.handleFieldchange} /> 
        
            



        return(
            <div id="Main_Homepagecomponent">
               {sidebar}
                <div className="MainAccountComponement">

                   {(this.state.SelectName ==='accountadmim') ?< Accountadmin account = {this.state.data}/>: null }
                   {(this.state.SelectName ==='accountsummary') ?< AccountSummary account = {this.state.data}/>: null }
                   {(this.state.SelectName ==='staments') ?<Statements account = {this.state.data}/>: null }
                   {(this.state.SelectName ==='paymentandtransfer') ?<Paymenttransfer account = {this.state.data}/>: null }
                </div>
            </div>
        );

    }
}
export default Homepage; 