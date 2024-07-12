import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    const handleLogout = () => {
        localStorage.removeItem('token');
    };

    return (
        <header>
            <nav>
                <ul>
                    <li><Link to="/dashboard">Dashboard</Link></li>
                    <li><Link to="/admin">Admin</Link></li>
                    <li><Link to="/login" onClick={handleLogout}>Logout</Link></li>
                </ul>
            </nav>
        </header>
    );
};

export default Header;