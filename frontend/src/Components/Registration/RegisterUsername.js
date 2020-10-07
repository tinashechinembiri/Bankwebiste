import React, { Component } from 'react';

class RegisterUsername extends Component
{
    constructor(props)
    {
        super(props); 
    }

    prev_Button = () => 
    {
        //const {username, password} = this.props; 
       
            this.props.previousButton(); 
    }

    
    render()
    {
        if (this.props.currentStep!== 2)
        {
            return null; 
        }
        return(
            <div className= "registrationStep">
             <label> Username </label>
                <input id="username" 
                name = "username"
                type= "text"
                placeholder="enter  username"
                value ={this.props.username}
                onChange={this.props.handleChange}
                />
                <label>Password</label>
                <input
                id= "password"
                name="password"
                type="text"
                placeholder="enter your password"
                value={this.props.password}
                onChange={this.props.handleChange}
                />
                <div class="text-center">
                <button onClick={this.prev_Button} type="button">go back</button>
             <button className="btn btn-success btn-block">Sign up</button>
             </div>
            </div>
        )
    }
}
export default RegisterUsername; 