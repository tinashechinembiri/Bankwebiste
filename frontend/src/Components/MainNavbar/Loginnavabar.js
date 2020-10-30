import React, { Component } from 'react';
import './Mainnav.css';
import {Navbar, NavbarBrand, Nav, NavItem, NavLink} from 'reactstrap';


class Loginnavabar extends Component{

    render()
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
}
export  default Loginnavabar; 