import React, { useState } from 'react';
import api from '../../api';

const BookingForm = ({ onBookingCreated }) => {
    const [date, setDate] = useState('');
    const [halfDay, setHalfDay] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post('/bookings', { date, halfDay });
            onBookingCreated(response.data);
        } catch (error) {
            console.error('Booking failed', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Date:</label>
                <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
            </div>
            <div>
                <label>Half Day:</label>
                <input type="checkbox" checked={halfDay} onChange={(e) => setHalfDay(e.target.checked)} />
            </div>
            <button type="submit">Book</button>
        </form>
    );
};

export default BookingForm;