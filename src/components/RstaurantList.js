Update src/components/RestaurantList.js to handle errors:

import React, { useEffect, useState } from 'react';

const RestaurantList = () => {
    const [restaurants, setRestaurants] = useState([]);
    const [error, setError] = useState(null);

   

    return (
        <div>
            <h2>Restaurants</h2>
            {error && <p>Error: {error}</p>}
            <ul>
                {restaurants.map(restaurant => (
                    <li key={restaurant.id}>{restaurant.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default RestaurantList;