import React ,{ Component } from 'react';
import './Css/Forgotpassword.css';
import Username from './Forgotpasswordcomponents/Username'; 
import Account from './Forgotpasswordcomponents/Account'; 


import {
    Card, CardImg, CardText, CardBody,
    CardTitle, CardSubtitle, Button, NavItem
  } from 'reactstrap';
  const url_Allaccount = 'ApiAutomationHost/rest/account/account/Userdata'; 
  const update_Passwordurl = '/ApiAutomationHost/rest/account/account/updatepassword/'; 
class Forgotpassword extends Component
{
    constructor(props)
    {
        super(props); 

        this.state = {
            accountid : '', 
            newpassword :'', 
            step: 1, 
            User_data: []

        }
        this.handleChange = this.handleChange.bind(this); 
      //  this.nextButton = this.nextButton.bind(this); 
    }
    handleChange = async(event) => {

        const {name, value} = event.target
        
        this.setState({
          [name]: value, 
        })    
      }


    alluserapi = () => 
    {
        fetch(url_Allaccount)
        .then(response => {return response.json()})
        .then(data => this.setState({User_data:data}, console.log(data)) )

    }
  UpdatePassword = (event) => 
  {
    event.preventDefault(); 
    var id = this. state.accountid;
    const Updatenewpassword = Object.create(
      null,{
        'password': {value:this.state.newpassword , enumerable:true}, 

      } ); 
    fetch(update_Passwordurl +id , {
      method: 'PUT',
            headers: new Headers({
                'Accept': 'application/json, text/plain, */*',
                'Content-type': 'application/json',
                'Access-Control-Allow-Origin': '*'
             }),
             body:JSON.stringify(Updatenewpassword)
    }
      
      )
  }


    checkuserexist = () => 
    {
        console.log(this.state);
        console.log('check')
        var  data  = this.state.User_data; 
        var flag; 
       console.log(data)

        var id = parseInt(this.state.accountid); 
        
        data.forEach(function(key, index)
        {
          console.log('name'+ key.account_number)

          if (key.account_number === id)
          {
            flag = true; 
          }
        }
        
        )
        if (flag)
        {
          return true; 
        }
        else
        {
          return false; 
        }
        
    }


    _next = () => 
    {
        let step = this.state.step; 
        step = step >= 1?2 : step + 1;
        this.setState(
            {
              step: step 
            }
          )


    }
    nextButton = () => 
    {
         
       console.log(this.checkuserexist())
        if ( this.checkuserexist() === true)
        {
           
                this._next(this); 
        }
        else
        {
           console.log("failed")     
        } 
    }



    componentDidMount()
    {
        this.alluserapi(); 
       
    }
    componentDidUpdate()
    {
    
    }

   
        

    render()
    {
        const {step} = this.state;

            switch(step)
           {
                case 1: 
                return < Username

                         handleChange = {(event) => this.handleChange(event)}  
                    
                         accountid ={this.state.accountid}
                         nextButton = {this.nextButton}


                 /> 
                 case 2: 
                 return <Account 
                 handleChange = {(event) => this.handleChange(event)}
                 newpassword = {this.state.newpassword}
                 UpdatePassword = {this.UpdatePassword}
                 
                 />

           }

            
    }
}
export default Forgotpassword ; 