import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class FxSpotList extends Component {

    constructor(props) {
        super(props);
        this.state = {fxSpots: []};
       // this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/fxSpot')
            .then(response => response.json())
            .then(data => this.setState({fxSpots: data}));
    }

   /* async remove(id) {
        await fetch(`/fxSpot/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedClients = [...this.state.fxSpots].filter(i => i.id !== id);
            this.setState({fxSpots: updatedClients});
        });
    }*/

    render() {
        const {fxSpots} = this.state;

        const fxSpotList = fxSpots.map(fxSpot => {
            return <tr key={fxSpot.id}>
                <td style={{whiteSpace: 'nowrap'}}>{fxSpot.curr1}-{fxSpot.curr2}</td>
                <td>{fxSpot.date}</td>
                <td>{fxSpot.value}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/fxSpot/" + fxSpot.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(fxSpot.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/fxSpot/new">Add Client</Button>
                    </div>
                    <h3>Fx Spots</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Fx Pair</th>
                            <th width="30%">Valuation Date</th>
                            <th width="40%">Value</th>
                        </tr>
                        </thead>
                        <tbody>
                        {fxSpotList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default FxSpotList;