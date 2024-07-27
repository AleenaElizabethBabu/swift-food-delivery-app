import React from 'react';
import { Link } from 'react-router-dom';

const Navigation = () => {
    return (
        <nav>
            <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/restaurants">Restaurants</Link></li>
                <li><Link to="/order">Order</Link></li>
            </ul>
        </nav>
    );
};

export default Navigation;
