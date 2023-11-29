import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import FxSpotList from "./FxSpotList";
import Test from "./Test";
import TestPage from "./Test";
import Chart from "./fxSpotHistoryChart";


class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/fxSpot' exact={true} component={FxSpotList}/>
            <Route path='/test' exact={true} component={TestPage}/>
          </Switch>
        </Router>
    )
  }
}

export default App;