import './App.css';
import { Link, Routes, Route } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Home from './pages/Home';
import Menu from './pages/Menu';
import Booking from './pages/Booking';
import About from './pages/About';
import Contact from './pages/Contact';

function App() {
  return (
    <div>
      <Navbar expand="lg" className='fixed-top custom-navbar shadow'>
        <Container>
          <Navbar.Brand>
            <Link to="/" className='navbar-brand'>
              ABC Restaurant
            </Link>
          </Navbar.Brand>
          <Navbar.Toggle aria-controls='basic-navbar-nav' />
          <Navbar.Collapse id='basic-navbar-nav'>
            <Nav className='me-auto justify-content-end w-100'>
              <Nav.Link as={Link} to="/" className='active text-uppercase'>Home</Nav.Link>
              <Nav.Link as={Link} to="/menu" className='text-uppercase'>Menu</Nav.Link>
              <Nav.Link as={Link} to="/booking" className='text-uppercase'>Booking</Nav.Link>
              <Nav.Link as={Link} to="/about" className='text-uppercase'>About</Nav.Link>
              <Nav.Link as={Link} to="/contact" className='text-uppercase'>Contact</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/menu' element={<Menu />} />
        <Route path='/booking' element={<Booking />} />
        <Route path='/about' element={<About />} />
        <Route path='/contact' element={<Contact />} />
      </Routes>

      <footer className='bg-dark text-white'>
        <p className='p-3 m-0 text-center'>copyright @ ABC Restaurant</p>
      </footer>
    </div>
  );
}

export default App;
