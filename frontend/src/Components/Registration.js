import React, { Component } from 'react';
import Registration_account  from "./Registration/Registration_account"; 
import RegistrationUsername from "./Registration/RegisterUsername"; 
import { Media, Jumbotron } from 'reactstrap';
import './Css/registration.css';
const url_Allaccount = 'ApiAutomationHost/rest/account/account/Userdata'; 
const post_account = '/ApiAutomationHost/rest/account/account/registration'; 

class Registration extends Component
{
    constructor(props)
    {
        super(props)
        this.state = {
            currentStep: 1,
            username: '', 
            firstName:'',
            lastname:'', 
            password:'', 
            account: null, 
            User_data: []
        }
        this.handleChange = this.handleChange.bind(this)
    }

    handleChange = async(event) => {

        const {name, value} = event.target
        this.setState({
          [name]: value, 
        })    
      }

    componentWillMount()
    {
      
      this.Registration_checkdata();         
    }

    Registration_checkdata = () => 
    {
       fetch(url_Allaccount)
      .then(response => {return response.json()})
      .then(data => this.setState({User_data:data}, console.log(data)) )

    }

    _next()
    {
      let currentStep = this.state.currentStep; 
    


      currentStep = currentStep >= 1?2 : currentStep + 1;  
      this.setState(
        {
          currentStep:currentStep
        }
      )
      
    }


    Submit_Registration = (event) => 
    {
      event.preventDefault(); 
      var passencrpt = this.state.password; 
      var ids = 1+ this.state.User_data.length; 
      
      const create_user = Object.create(
        null,
        {
          id : {value: ids , enumerable: true}, 
          account_number:{value: this.state.account, enumerable:true}, 
          lastname :{value:this.state.lastname, enumerable: true},
          username :{value:this.state.username, enumerable: true},
          password:{value:passencrpt, enumerable:true},
          firstName:{value:this.state.firstName}, 
          account: []
        }); 

        fetch (post_account, 
          {
            method: 'POST',
            headers: new Headers({
                'Accept': 'application/json, text/plain, */*',
                'Content-type': 'application/json',
                'Access-Control-Allow-Origin': '*'
             }),
             body:JSON.stringify(create_user)
          }
          
          )
          .then ((response) => response)
         .then(data => {
            console.log(data)
             if(data.status === 200)
               {
                   this.props.history.push('/login'); 
                } 
                else
                {
                   console.log("Failed Log in "); 
                }   
            }); 

        console.log(create_user); 
    }

    _prev(){
        let currentStep = this.state.currentStep; 
        currentStep = currentStep <= 1? 1 : currentStep - 1; 

        this.setState(
          {
            currentStep: currentStep 
          }
        )
    }

     previousButton = () => 
    {
      let currentStep = this.state.currentStep; 

      if (currentStep !== 1)
      {
         this._prev(this)
        
      }
      return null; 
    }

     nextButton = () => {
       
      let currentStep = this.state.currentStep; 
      console.log(currentStep)

      if (currentStep <= 2)
      {
      
       this._next(); 
      }
      
      return null; 

    }


    render()
    {
      console.log(this.state.User_data.length); 


        return(
            <div>

            <h1>Registration</h1>
            <Jumbotron>
            <p>Step {this.state.currentStep} </p> 
            <form onSubmit={(event) => this.Submit_Registration(event)}>
            
              <Registration_account
              currentStep = {this.state.currentStep}
              handleChange = {(event) => this.handleChange(event)}  
              account = {this.props.account}
              firstName = {this.props.firstName}
              lastname = {this.state.lastname}
              nextButton = {this.nextButton}
              />

              <RegistrationUsername

               currentStep = {this.state.currentStep}
               handleChange = {(event) => this.handleChange(event)}
               username = {this.state.username}
               password = {this.state.password}
               previousButton ={this.previousButton}
              
              />

             
              </form>
              </Jumbotron>
            </div>


        );

    }

}

export default Registration; 