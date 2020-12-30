
import { data } from 'jquery';
import React, { Component } from 'react';

class Transefernabar extends Component 
{
    constructor (props)
    {
        super(props); 

        this.state = {
            'transferaccountnumber' : '',
            'amount' : '' ,

        }

        this.handleChange = this.handleChange.bind(this);

    

    }
    handleChange = (event) => {

        const {name, value} = event.target
        this.setState({
          [name]: value
        })    
      }


    handletransfer = () => 
    {

        var sender_accountnumber = this.props.id_value.account_number; 
        var reciever_accountnumber = this.state.transferaccountnumber; 
        var amount_sent = this.state.amount; 

        const transferjson = Object.create (
          {
             "userid": sender_accountnumber, 
             "transferaccountid": reciever_accountnumber, 
             "transferamount": amount_sent, 
          }
        )

        fetch("/ApiAutomationHost/rest/secured/moneytransfer/", 
        {
          method: 'PUT',
          headers: new Headers({
              'Accept': 'application/json, text/plain, */*',
              'Content-type': 'application/json',
              'Access-Control-Allow-Origin': '*'
           }),

           body:JSON.stringify(transferjson)

        }
        )
        .then(Response => Response)
        .then (data => 
          {
            if (data.status === 200)
            {
               fetch("/ApiAutomationHost/rest/secured/statementupdate/", 
                {
                  method: 'POST',
                   headers: new Headers({
                 'Accept': 'application/json, text/plain, */*',
                 'Content-type': 'application/json',
                 'Access-Control-Allow-Origin': '*'
                 }),

                 body:JSON.stringify(transferjson)
                }
               
               ).then(response => response)
               .then(data => 
                {
                  if (data.status === "200")
                  {
                    
                  }
                })
            }
            
          })

    }





    render ()
    {

        console.log(this.state.transferaccountnumber); 
        const user_data = this.props.id_value; 
        return(
            <div id="userinputcomponent" >
              <label> From </label>


              <input  readOnly= {true}
              value={user_data.account_number}
              /> 

              <label>Amount</label>

              <input 
                type="text"
                id="amount"
                name="amount"
                value={this.state.amount} 
                onChange ={(event)=> this.handleChange(event)} 
              />

              <label> To </label>

                
              <input
                type="number"
                id="transer_id"
                name="transferaccountnumber"
                value={this.state.transferaccountnumber} 
                onChange ={this.handleChange}
              />

              <button>Send</button>
            </div>

        )
    }
}

export default Transefernabar; 