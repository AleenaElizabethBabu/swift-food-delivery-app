import React, { useEffect, useState } from 'react';

const RestaurantList = () => {
    const [restaurants, setRestaurants] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(`${apiUrl}/restaurants`)
            .then(response => response.json())
            .then(data => setRestaurants(data))
            .catch(err => setError(err.message));
    }, []);

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
