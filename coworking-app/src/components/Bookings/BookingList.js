import React, { useEffect, useState } from 'react';
import api from '../../api';

const BookingList = () => {
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const response = await api.get('/bookings');
                setBookings(response.data);
            } catch (error) {
                console.error('Failed to fetch bookings', error);
            }
        };
        fetchBookings();
    }, []);

    const handleCancel = async (id) => {
        try {
            await api.delete(`/bookings/${id}`);
            setBookings(bookings.filter(booking => booking.id !== id));
        } catch (error) {
            console.error('Failed to cancel booking', error);
        }
    };

    return (
        <div>
            <h2>Your Bookings</h2>
            <ul>
                {bookings.map(booking => (
                    <li key={booking.id}>
                        {booking.date} - {booking.halfDay ? 'Half Day' : 'Full Day'}
                        <button onClick={() => handleCancel(booking.id)}>Cancel</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default BookingList;