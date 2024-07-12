import React, { useEffect, useState } from 'react';
import api from '../../api';

const ManageBookings = () => {
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const response = await api.get('/admin/bookings');
                setBookings(response.data);
            } catch (error) {
                console.error('Failed to fetch bookings', error);
            }
        };
        fetchBookings();
    }, []);

    const handleUpdateStatus = async (id, status) => {
        try {
            const response = await api.put(`/admin/bookings/${id}`, { status });
            setBookings(bookings.map(booking => booking.id === id ? response.data : booking));
        } catch (error) {
            console.error('Failed to update booking status', error);
        }
    };

    return (
        <div>
            <h2>Manage Bookings</h2>
            <ul>
                {bookings.map(booking => (
                    <li key={booking.id}>
                        {booking.date} - {booking.halfDay ? 'Half Day' : 'Full Day'} - {booking.status}
                        <button onClick={() => handleUpdateStatus(booking.id, 'ACCEPTED')}>Accept</button>
                        <button onClick={() => handleUpdateStatus(booking.id, 'DENIED')}>Deny</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ManageBookings;