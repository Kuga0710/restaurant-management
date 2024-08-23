import React, { useState } from 'react';
import './Booking.css';
import { Form } from 'react-bootstrap';
import axios from 'axios';
import tableImage from '../utils/img/table.jpeg';

function ReserveTable() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        date: '',
        numberOfPeople: '',
        specialRequests: ''
    });

    const [responseMessage, setResponseMessage] = useState(null);
    const [validationErrors, setValidationErrors] = useState({});

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.id]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/v1/reservation', formData);
            if (response.status === 200) {
                setResponseMessage('Your table has been reserved successfully!');
                setFormData({
                    name: '',
                    email: '',
                    date: '',
                    numberOfPeople: '',
                    specialRequests: ''
                });
                setValidationErrors({});
            }
        } catch (error) {
            if (error.response && error.response.status === 400) {
                const errors = error.response.data.data;
                setValidationErrors(errors);
                setResponseMessage('Failed to reserve the table due to validation errors.');
            } else {
                setResponseMessage('Failed to reserve the table. Please try again.');
            }
        }
    };

    return (
        <div className='reserve-table-page'>
            <header className='mt-4'>
                <div className='container h-100 d-flex align-items-center justify-content-center'>
                    <h3 className='section-title'>- Reserve a Table Online -</h3>
                </div>
            </header>
            <div className='container my-4'>
                <div className='row'>
                    <div className='col-md-5'>
                        <div className='wow fadeInUp'>
                            <Form onSubmit={handleSubmit}>
                                <Form.Group className='mb-2'>
                                    <Form.Label htmlFor='name'>Your Name</Form.Label>
                                    <Form.Control
                                        type='text'
                                        id='name'
                                        placeholder='Your Name'
                                        value={formData.name}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.name}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.name}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className='mb-2'>
                                    <Form.Label htmlFor='email'>Your Email</Form.Label>
                                    <Form.Control
                                        type='email'
                                        id='email'
                                        placeholder='Your Email'
                                        value={formData.email}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.email}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.email}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className='mb-2'>
                                    <Form.Label htmlFor='date'>Reservation Date</Form.Label>
                                    <Form.Control
                                        type='date'
                                        id='date'
                                        value={formData.date}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.date}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.date}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className='mb-2'>
                                    <Form.Label htmlFor='numberOfPeople'>Number of People</Form.Label>
                                    <Form.Control
                                        type='number'
                                        id='numberOfPeople'
                                        placeholder='Number of People'
                                        value={formData.numberOfPeople}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.numberOfPeople}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.numberOfPeople}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className='mb-3'>
                                    <Form.Label htmlFor='specialRequests'>Special Requests</Form.Label>
                                    <Form.Control
                                        as='textarea'
                                        id='specialRequests'
                                        placeholder='Any special requests?'
                                        style={{ height: '100px' }}
                                        value={formData.specialRequests}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.specialRequests}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.specialRequests}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <button type='submit' className='btn btn-primary w-100 py-3'>Reserve Table</button>
                            </Form>
                            {responseMessage && (
                                <div className={`alert ${validationErrors ? 'alert-danger' : 'alert-success'} mt-3`}>
                                    {responseMessage}
                                </div>
                            )}
                        </div>
                    </div>
                    <div className='col-md-7'>
                        <img
                            src={tableImage} // Use the imported image
                            alt='ABC Restaurant'
                            className='position-relative rounded w-80 h-80'
                            style={{ minHeight: '350px', border: '0' }}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ReserveTable;
