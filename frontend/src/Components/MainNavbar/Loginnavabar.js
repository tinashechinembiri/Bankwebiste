import React, { Component } from 'react';
import './Mainnav.css';
import {Navbar, NavbarBrand, Nav, NavItem, NavLink} from 'reactstrap';
import  { Redirect } from 'react-router-dom'

class Loginnavabar extends Component{


  logoutbutton = () => 
  {
    localStorage.removeItem('User'); 
    return   <Redirect to='/login' ></Redirect> ; 
  }

    render()
    { 
        
      if (localStorage.getItem('User')=== null)
      {
        
    return(
    <div >
         <Navbar id ="navbar_css">
         <NavbarBrand  className="navitemcss"  href="/">Home</NavbarBrand>
         <Nav className="mr-auto" navbar>
         <NavItem>
              <NavLink className="navitemcss" href="/">About Us</NavLink>
            </NavItem>
         </Nav>
         <Nav>
         <NavItem>
              <NavLink className="navitemcss"  href="/login">Login</NavLink>
            </NavItem>
            <NavItem>
              <NavLink className="navitemcss"  href="/registration">Register</NavLink>
            </NavItem>

         </Nav>

         </Navbar>
   
    </div> )}
    else
    {
      return(
        <div >
             <Navbar id ="navbar_css">
             <NavbarBrand  className="navitemcss"  href="/homepage">Home</NavbarBrand>
             <Nav className="mr-auto" navbar>
             <NavItem>
                  <NavLink className="navitemcss" href="/">About Us</NavLink>
                </NavItem>
             </Nav>
             <Nav>
             <NavItem>
              <NavLink  className="navitemcss"  href="/Edit">Edit</NavLink>
                </NavItem>
                <NavItem>
                <NavLink onClick={this.logoutbutton} className="navitemcss"  href="/login">Logout</NavLink>
                </NavItem>
    
             </Nav>
    
             </Navbar>
       
        </div> )
    }

    }



}
export  default Loginnavabar; 