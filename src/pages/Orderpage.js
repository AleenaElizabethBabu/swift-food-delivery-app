import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Navigation from '../components/Navigation';
import OrderForm from '../components/OrderForm';

const OrderPage = () => {
    return (
        <div>
            <Header />
            <Navigation />
            <main>
                <OrderForm />
            </main>
            <Footer />
        </div>
    );
};

export default OrderPage;
