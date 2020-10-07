import React ,{ Component } from 'react';
import './Css/Forgotpassword.css';
import {
    Card,  CardText, CardBody,
    CardTitle,  Button
  } from 'reactstrap';

class Username extends Component
{
    constructor(props)
    {
       super(props) 
    }
    saveandcontinue = () => 
    {
        this.props.nextButton(); 
    }

    render()
    {
        
        var accountValue = parseInt(this.props.accountid)
        return(
            <div className="maincontainer">
                  <Card>
                            <CardTitle>
                                Forgot your password 
                            </CardTitle>
                            <CardBody>
                                <label>Accountid: </label>
                                <input
                                    placeholder="accountid"
                                    id= "accountid"
                                    name="accountid"
                                    type="number"
                                    value={accountValue}
                                    onChange={this.props.handleChange}
                                    
                                />
                            </CardBody>

                            <Button
                            type="button"
                            onClick={this.saveandcontinue}
                            >Next</Button>
                           
                        </Card>
            </div>

        ); 
    }
}
export default Username; 