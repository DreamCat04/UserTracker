import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { redirect } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const PrivateRoute = ({ component: Component, ...rest }) => {
    const isAuthenticated = () => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedToken = jwtDecode(token);
            const currentTime = Date.now() / 1000;
            return decodedToken.exp > currentTime;
        }
        return false;
    };

    return (
        <Routes>
        <Route
            {...rest}
            render={props =>
                isAuthenticated() ? (
                    <Component {...props} />
                ) : (
                    <redirect to="/login" />
                )
            }
        />
        </Routes>
    );
};

export default PrivateRoute;