import React, { Component } from 'react';
import './Css/Homepagesub.css'; 

class AccountSummary extends Component{


    constructor(props)
    {
        super(props); 
           
    }

    render()
    {
        let customerdata = this.props.account; 
        
        const items = this.props.account.account.map((d)=>
        <tbody id="tablebody">
        <tr>
        <td key={1}>{d.type}</td>
         <td>{d.balance}</td>
        <td>{d.credit_line}</td>
        <td>{d.begin_balance}</td>
        <td>{d.begin_balance_timestamp}</td>
        </tr>
        </tbody>
        
        )

        return(
            <div className="SubAccountcss">
                <div className="Userdetails">
                    <table>
                        <thead>
                            <tr>
                                <th>Account Number</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>{customerdata.user_id}</td>
                            <td>{customerdata.firstName}</td>
                            <td>{customerdata.secondName}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        <table>
            <thead>
                <tr>
                <th>Type</th>
                <th>Balance</th>
                <th>Credit line</th>
                <th>Begin Balance</th>
                <th>Time</th>
                </tr>
            </thead>
            {items}
        </table>
                
            </div>
        );

    }
}

export default AccountSummary;