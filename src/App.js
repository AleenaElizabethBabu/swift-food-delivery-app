import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HomePage from './pages/HomePage';
import RestaurantPage from './pages/RestaurantPage';
import OrderPage from './pages/OrderPage';

const App = () => {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={HomePage} />
                <Route path="/restaurants" component={RestaurantPage} />
                <Route path="/order" component={OrderPage} />
            </Switch>
        </Router>
    );
};

export default App;
