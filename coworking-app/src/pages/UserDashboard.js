import React from 'react';
import BookingForm from '../components/Bookings/BookingForm';
import BookingList from '../components/Bookings/BookingList';

const UserDashboard = () => {
    const handleBookingCreated = (booking) => {
        // Handle booking creation
    };

    return (
        <div>
            <h1>User Dashboard</h1>
            <BookingForm onBookingCreated={handleBookingCreated} />
            <BookingList />
        </div>
    );
};

export default UserDashboard;