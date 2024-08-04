import React, { useEffect, useState } from 'react';

const RestaurantList = () => {
    const [restaurants, setRestaurants] = useState([]);
    const [error, setError] = useState(null);
    const [showList, setShowList] = useState(true); // Added state to toggle list visibility

    useEffect(() => {
        fetch(`${apiUrl}/restaurants`)
            .then(response => response.json())
            .then(data => setRestaurants(data))
            .catch(err => setError(err.message));
    }, []);

    return (
        <div>
            <h2>Restaurants</h2>
            <button onClick={() => setShowList(!showList)}>
                {showList ? 'Hide' : 'Show'} Restaurants
            </button>
            {error && <p>Error: {error}</p>}
            {showList && (
                <ul>
                    {restaurants.map(restaurant => (
                        <li key={restaurant.id}>{restaurant.name}</li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default RestaurantList;
