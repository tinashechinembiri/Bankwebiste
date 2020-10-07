import React ,{ Component } from 'react';
import './Css/Forgotpassword.css';
import {
    Card,  CardText, CardBody,
    CardTitle,  Button
  } from 'reactstrap';


class Account extends Component 
{
    render () {
        return(

            <div>
                <form onSubmit={(event) => this.state.UpdatePassword(event)}>
                    <Card >
                        <CardTitle> add the new password</CardTitle>

                        <CardBody>
                            
                            <input
                                id = "newpassword"
                                name= "newpassword"
                                placeholder = "Add new password"
                                value ={this.props.newpassword}
                                onChange={this.props.handleChange}
                            />

                        </CardBody>
                        <Button>
                            update password 
                        </Button>

                    </Card>
                    </form>
            </div>
        )
    }
}

export default Account; 