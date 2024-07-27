import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Navigation from '../components/Navigation';
import RestaurantList from '../components/RestaurantList';

const RestaurantPage = () => {
    return (
        <div>
            <Header />
            <Navigation />
            <main>
                <RestaurantList />
            </main>
            <Footer />
        </div>
    );
};

export default RestaurantPage;
