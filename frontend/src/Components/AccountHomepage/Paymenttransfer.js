import React, { Component } from 'react';
import  './Css/paymenttransfers.css';
import Transefernabar from './navbar/Transfernavbar'; 


class Paymenttransfer extends Component{

    constructor(props)
    {
        super(props)
        this.state  = {
            buttonClicked :false, 
            selectvalue :[], 
            transferaccountnumber : '',
            amount : '' 

        }

        this.selectaccount = this.selectaccount.bind(this); 
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange = async(event) => {

        const {name, value} = event.target
        this.setState({
          [name]: value, 
        })    
      }
   
    transfer = () => 
    {
        

    }
    selectaccount = (items) => 
    {
       
        const {buttonClicked} = this.state; 
        this.setState(
            {
                buttonClicked :!buttonClicked
            }); 

           

        if (this.state.buttonClicked != true)
        {
            this.setState(
                {
                    selectvalue:items 
                });
        }

           
    }

    

    render()
    {
        
        
        
      let  accounts = this.props.account.account.map((account) => 
        {
            

            return(
                
                <div  id = "payment_card" class="card" onClick={this.selectaccount.bind(this, account)} >
                        

                        <div id = "payment_cardbody"class="card-body">
                        
                        <h5 id= "payment_title"  class="card-title">{account.type}</h5>

                         <p  id="accountnumber" class="card-text" value={this.state.selectvalue} onChange={this.handlechange}>{account.account_number} </p>
                         <label id="label_balance"> Balance </label>
                         <p id="balance" class="card-text">£{account.balance} </p>
                         <p id="sortcode" class="card-text">{account.sortcode}</p>
                         </div>
                
                        </div> 
            )

        })
           
        return(
            
                <div className="SubAccountcss">
                <div  id="paymentpagebody"> 
                <div id="paymentbody" >

                    {accounts}

                </div>

                </div>

                <div >    
                {this.state.buttonClicked ? <Transefernabar id_value ={this.state.selectvalue} /> : null}
                </div>
            
                </div>
        );

    }
}

export default Paymenttransfer;