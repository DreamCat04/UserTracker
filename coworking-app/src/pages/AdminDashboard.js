import React from 'react';
import ManageUsers from '../components/Admin/ManageUsers';
import ManageBookings from '../components/Admin/ManageBookings';

const AdminDashboard = () => {
    return (
        <div>
            <h1>Admin Dashboard</h1>
            <ManageUsers />
            <ManageBookings />
        </div>
    );
};

export default AdminDashboard;