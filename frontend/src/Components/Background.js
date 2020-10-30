import React, { Component } from 'react';
import { Media } from 'reactstrap';
import './Css/Background.css';


class Background extends Component{
    constructor()
    {
        super();
        this.state ={

           api :[

            ]
        }
    }

    render()
    {
        return(
            <div className="Template" >
                <div className="mainBackground">
                <Media>
                
                 <img
                   
                     className="mainImage"
                      src="/6678696.png"
                      alt="Generic placeholder"
                     />
                <div className= "inside_text">
                    
                </div>
                
                </Media>
                
                <div className="sideitems">
                    <div className = "sideitem_1">
                    <h1>Item 1</h1>
                    </div>
                    <hr/>
                    <div className="sideitem_2">
                    <h2>Item 2</h2>
                    </div>
                    
                </div>



                </div>
            
               
            </div>
        );

    }

}

export default Background; 