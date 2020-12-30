import { data } from 'jquery';
import React, { Component } from 'react';

class Statements extends Component{

    
    constructor(props)
    {
        super(props); 
        this.state = {
            data: []
        }
           
    }
    
    getData = () => 
    {
        
    var items =[] ; 
       this.props.statement.account.map((d) =>
        {
            const menuItems = [];
            d.statement.map( (p) => {
                
                items.push(p);   
               return p;  
            })
            console.log(menuItems); 
         //  return menuItems; 
            
        } ); 

        this.setState ({data:items})
        console.log(items)
     
    }

    componentDidMount =() => 
   {
        this.getData(); 
   }

    render()
    {
        console.log(this.state.data); 
        
     let menuItems = [];
        const statements = this.state.data.map((d) => {   
            if (d === null)
            {
                    return(
                        <tbody id="tablebody">

                            
                        </tbody>
                        
                    )
            } 
            else{
            return  (
            <tbody id="tablebody">
                
            <tr>
                <td key={1} > {d.referencenumber} </td>
                <td> {d.cardnumber}</td>
                <td>{d.balance}</td>
                 <td>{d.date}</td>
                <td>{d.statementtype}</td>
             </tr>     

             </tbody>
            )
            }
        }); 

        

        
        


        return(
            <div className="SubAccountcss">
                <div className="Userdetails">
                <table>
                <thead>
                <tr>
                <th>referencenumber</th>
                <th>cardnumber</th>
                <th>balance</th>
                <th>date</th>
                <th>statement type</th>
                </tr>
                 </thead>
                  
                {statements}
                </table>
                </div>
            </div>
        );

    }
}

export default Statements;