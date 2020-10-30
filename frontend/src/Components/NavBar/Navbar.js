 import React , { Component }  from 'react';
 import { Button,Navbar,Nav } from "react-bootstrap";
 import './CSS/Homepage.css'; 


 class NavBar extends Component {

  constructor(props){
    super(props); 
    this.state = {
      'accountadmin': 'Accountadmin'
    }
    this.handlechange = this.handlechange.bind(this); 
  }
    handlechange = (event ) => {
      const text = event.target.name;
   //  console.log(text); 
    this.props.onSelectLanguage(text)

    }

render()
{
  let value1 = this.state.value; 
    return (
    <div >

     <Navbar className = "navbar_css"  variant="dark">
      <Nav id="navitems" className="mr-auto">
      
      <input  type="button" name = {'accountadmim'}  value = {"Account admin"} onClick={this.handlechange}  className="links" />
      
       <br></br>
        <input  type="button" className="links" onClick={this.handlechange}  name={'accountsummary'} value ={'Account Summary'}/>
         <br></br>
        <input   type="button" className="links"  onClick={this.handlechange}   name={'staments'}  value= {'Statements'}/>
         <br></br>
        <input   type="button" className="links"  onClick={this.handlechange}   name={'paymentandtransfer'} value={'payment&Transfer'}/>
         <br></br>
       </Nav>
      </Navbar>
        </div>
    )}

 }
export default NavBar; 

