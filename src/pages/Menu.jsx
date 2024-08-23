import React, { useEffect, useState } from 'react';
import './Menu.css';
import axios from 'axios';
import { Card, CardBody, CardText, CardTitle } from 'react-bootstrap';

function Menu() {
    const [menus, setMenus] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Fetch the menu data from the backend
        axios.get('http://localhost:8080/api/v1/menu')
        .then(response => {
            setMenus(response.data.data); // Assuming the correct structure of the response
        })
        .catch(error => {
            console.error('There was an error fetching the menu data!', error);
            setError(error); // Set the error state
        });
}, []);

    return (
        <div className='menu-page'>
            <header className='mt-5'>
                <div className='container h-100 d-flex align-items-center justify-content-center'>
                    <h1 className='text-light'>Menu</h1>
                </div>
            </header>

            {menus.map((menuCategory, index) => (
                <div key={index} className={`${menuCategory.name.toLowerCase()} my-5`}>
                    <div className='container'>
                        <h2 className='text-center fs-1 mb-4 mb-lg-5 text-uppercase fw-bold text-success'>{menuCategory.name}</h2>
                        <div className='row flex-column-reverse flex-lg-row'>
                            <div className='col-lg-6 d-flex justify-content-center'>
                                <img src={`data:image/jpeg;base64,${menuCategory.image}`} className='img-fluid w-75 mt-4 mt-lg-0' alt={menuCategory.name} />
                            </div>
                            <div className='col-lg-6 d-flex flex-column justify-content-around'>
                                <Card className='border-0'>
                                    <CardBody>
                                        <CardTitle className='text-center fs-3'>
                                            {menuCategory.name}
                                        </CardTitle>
                                        <CardText className='text-center fs-5'>
                                            {menuCategory.description}
                                        </CardText>
                                        <CardText className='text-center fs-3 fw-bold text-success'>
                                            £{menuCategory.price}
                                        </CardText>
                                        <CardText className='text-center fs-5'>
                                            {menuCategory.availabilityStatus}
                                        </CardText>
                                    </CardBody>
                                </Card>
                            </div>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default Menu;
