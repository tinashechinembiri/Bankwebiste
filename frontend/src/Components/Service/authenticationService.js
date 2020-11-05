import React from 'react'
import jwt_decode from 'jwt-decode';
import  { Redirect } from 'react-router-dom'


const helpers = {

       token_expire : function () 
    {

        let token = localStorage.getItem('User') || null; 
        if (token != null){
        var decoded = jwt_decode(token); 
        var time_now  = (new Date()).getTime();
        var exp = decoded.exp * 1000 ; 
        
        if (decoded.exp * 1000 < time_now)
        {
            
             localStorage.removeItem('user'); 
             localStorage.clear(); 
        }
    }}, 



  
}

export default helpers; 



