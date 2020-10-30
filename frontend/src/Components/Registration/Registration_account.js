import React, { Component } from 'react';

class Registration_account extends Component {
    constructor(props)
    {
       super(props) 
    }


    saveandcontinue = () => 
    {
        const {account,lastname,firstName} = this.props; 
       
        if ( account !== null && lastname !== "" && firstName !== "")
        {
            this.props.nextButton(); 
        }
       // console.log(this.props.currentStep)



        
    }

    render()
    {
        console.log(this.props.lastname)
        if (this.props.currentStep !== 1)
        {
            return null; 
        }
        return(
            <div className= "framecard">

                <div className="registrationStep">
                <label> Account Number</label>
                <input id="account" 
                name = "account"
                type= "number"
                placeholder="enter account number"
                value ={this.props.account}
                onChange={this.props.handleChange }
             //   onChange = {(event)=> this.validatephase(event)}
                />
                <label>Second Name</label>
                <input
                id= "lastname"
                name="lastname"
                type="text"
                placeholder="enter your last name"
                value={this.props.lastname}
                onChange={this.props.handleChange}
                />
                <label>First Name </label>
                <input
                id= "firstName"
                name="firstName"
                type="text"
                placeholder="enter your first name"
                value={this.props.firstName}
                onChange={this.props.handleChange}
                />
                <div class="text-center">
                  <button 
                  className="btn btn-primary "
                  type="button"
                  onClick={this.saveandcontinue}
                  >Save And Continue </button>
                  </div>
                  </div>
            </div>
        );

    }
}
export default Registration_account; 