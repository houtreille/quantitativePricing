import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import FxSpotList from "./FxSpotList";


class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/fxSpot' exact={true} component={FxSpotList}/>
          </Switch>
        </Router>
    )
  }
}

export default App;