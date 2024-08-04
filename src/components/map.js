import React from 'react';
import { GoogleMap, LoadScript } from '@react-google-maps/api';

const containerStyle = {
    width: '100%',
    height: '400px'
};

const center = {
    lat: -3.745,
    lng: -38.523
};

const Map = () => {
    return (
        <LoadScript googleMapsApiKey="YOUR_API_KEY">
            <GoogleMap
                mapContainerStyle={containerStyle}
                center={center}
                zoom={12} // Adjusted zoom level from 10 to 12
            >
                {/* Child components, such as markers, info windows, etc. */}
                {/* Map loaded successfully */}
            </GoogleMap>
        </LoadScript>
    );
};

export default Map;
