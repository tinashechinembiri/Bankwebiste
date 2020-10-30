import React, { Component } from 'react';
import { Form, Button, FormGroup} from "react-bootstrap";
import {FormFeedback,Col} from 'reactstrap'; 
import {
    Card, CardImg, CardText, CardBody,
    CardTitle, CardSubtitle
  } from 'reactstrap';
  import './Css/Login.css';
//import { Link } from 'react-router-dom'; 

import base64 from 'base-64';
class Login extends Component
{
    constructor(props)
    {
        super(props)
        this.state = {
            account_value : [],
            'username':'',
            'password':'',
            validate: {
                passwordState:'', 
                usernameState: ''
            }

        }
    }
    handleChange = async(event) => {
        const {target} = event; 
        const value = target.type === 'checkbox' ? target.checked : target.value; 
        const { name } = target;
         this.setState({
            [name]: value,
            })
    }
    validateUsername(event){
        const usernameRex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        const {validate} = this.state; 
        if (usernameRex.test(event.target.value))
        {
            validate.usernameState = 'has-success';    
        }
        else 
        {
            validate.usernameState = 'has-danger'
        }
        this.setState({ validate })


    }
    validatePassword = (event)=>
    {
        const passwordRex =  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$/;
        const { validate } = this.state; 
            if (passwordRex.test(event.target.value)) {
                console.log(this.state.password+ "check_2")
            validate.passwordState = 'has-success'
            } else {
            validate.passwordState = 'has-danger'
            console.log(this.state.password)
            }
            this.setState({validate})
    }
    handleLoginSubmit = (event) => {

        event.preventDefault();
        var passencrpt = this.state.password; 
        const details = Object.create(
            null,
            {
                username :{value: this.state.username, enumerable: true } ,
                password: {value : base64.encode(passencrpt), enumerable: true}

            }
        )

        fetch('/ApiAutomationHost/rest/authentication/login',
        {
            method: 'POST',
            headers: new Headers({
                'Accept': 'application/json, text/plain, */*',
                'Content-type': 'application/json',
                'Access-Control-Allow-Origin': '*'
             }),
             body:JSON.stringify( details)
        }
        )
        .then ((response) => response)
         .then(data => {
            console.log(data)
             if(data.status === 200)
             {

               
                 localStorage.setItem('User', data.headers.get('AUTHORIZATION'));
              this.props.history.push(
                    {
                        pathname :'/Homepage', 
                        state:  details,
                    },);
                } 
                else
                {
                        console.log("Failed Log in ")
                }   
            }); 


    }
    logout = () => 
    {
        localStorage.removeItem('User')
    }
    
    submitForm = (e) =>{
        e.preventDefault();
        
        if(this.state.validate.passwordState ==='has-success')
            {
                
                this.handleLoginSubmit(e)
            }
        
}
    render()
    {
        
        return(
            <div className="maincontainer">
               <Card id="carditem" style={{ width: '40rem' }} >


                   <CardBody>
                       
                       <CardTitle className="headers">Login Page</CardTitle>
           
                <Form className= "FormItems" onSubmit={(event) => this.submitForm(event)} >
                    <FormGroup className="formgroup"  > 
                        <label className="h6">username : </label>    
                        <input 
                        type="username"
                         name="username"
                         id ="username"
                         placeholder="username"
                         value ={this.state.username}
                         onChange = {(event)=> this.handleChange(event)} 
                        />                
                    </FormGroup>
                    <FormGroup className="formgroup"  >
                    <label className="h6">password : </label>  
                       <div className="passwords">
                        <input invalid
                         type="password"
                         name="password"
                         id ="password"
                         placeholder="********"
                         value = {this.state.password}
                         valid={this.state.validate.passwordState === 'has-success'}
                         invalid={this.state.validate.passwordState === 'has-danger'}
                         onChange = {(event)=> { this.validatePassword(event)
                             this.handleChange(event)}}
                        />
                        <a href="">I’ve forgotten my password</a>
                        </div>
                        <FormFeedback invalid>
                              Password requires 1 uppercase, 1 lowercase, and at least 5 characters
                        </FormFeedback>
                        
                        </FormGroup>
                             <center>
                                <Button type="submit" outline color="primary" size ="lg">Log on</Button>
                             </center>
                    </Form>
                    
                    </CardBody>
                    </Card> 
                    <Card id="registration">
                    <CardBody className="cardy_body">
                        <CardTitle  className="headers">Register for online Banking</CardTitle>

                        <CardText>
                                <p>Manage your money online with our secure Online Banking service.</p>

                              <div className="Lists">
                                <ul className="column"> 
                                <li>Item 1</li>
                                 <li>Item 2</li>
                                 <li>Item 3</li>
                                 <li>Item 4</li>
                                 <li>Item 5</li>
                                 <li>Item 6</li>
                                 <li>Item 7</li>
                                 <li>Item 8</li>
                                </ul>
                                

                              </div>
                                

                        </CardText>
                        <a href="/registration">

                        <Button className="Button_item">Register Now </Button>
                        </a>
                    </CardBody> 
                   

                    </Card>
            
            </div>
        );

    }

}
export default Login;