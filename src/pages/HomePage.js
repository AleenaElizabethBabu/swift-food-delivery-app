import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Navigation from '../components/Navigation';
import Map from '../components/Map';

const HomePage = () => {
    return (
        <div>
            <Header />
            <Navigation />
            <main>
                <h2>Welcome to SWIFT Food Delivery</h2>
                <Map />
            </main>
            <Footer />
        </div>
    );
};

export default HomePage;
