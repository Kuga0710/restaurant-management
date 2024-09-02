import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './CartItems.css';
import axios from 'axios';

function CartItems() {
  const [cartItems, setCartItems] = useState([]);
  const [subtotal, setSubtotal] = useState(0);
  const userId = 1;
  const navigate = useNavigate();

  useEffect(() => {
    fetchCartItems();
  }, []);

  const fetchCartItems = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/cart/${userId}`);
      const items = response.data.data;
      setCartItems(items);
      calculateSubtotal(items);
    } catch (error) {
      console.error('Error fetching cart items:', error);
    }
  };

  const calculateSubtotal = (items) => {
    const subtotal = items.reduce((acc, item) => acc + item.menuPrice * item.quantity, 0);
    setSubtotal(subtotal);
  };

  const handleQuantityChange = async (index, increment) => {
    const updatedCartItems = [...cartItems];
    const cartId = updatedCartItems[index].cartId;
    const apiEndpoint = increment > 0 ? 
      `http://localhost:8080/api/v1/cart/${cartId}/increase` : 
      `http://localhost:8080/api/v1/cart/${cartId}/decrease`;

    try {
      await axios.put(apiEndpoint);
      updatedCartItems[index].quantity += increment;

      if (updatedCartItems[index].quantity < 1) {
        updatedCartItems[index].quantity = 1;
      }

      setCartItems(updatedCartItems);
      calculateSubtotal(updatedCartItems);
    } catch (error) {
      console.error(`Error ${increment > 0 ? 'increasing' : 'decreasing'} cart item quantity:`, error);
    }
  };

  const handleRemoveItem = async (index) => {
    const itemToRemove = cartItems[index];
    await deleteCartItem(itemToRemove.cartId);
    const updatedCartItems = cartItems.filter((_, i) => i !== index);
    setCartItems(updatedCartItems);
    calculateSubtotal(updatedCartItems);
  };

  const deleteCartItem = async (cartId) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/cart/${cartId}`);
      console.log('Cart item deleted');
    } catch (error) {
      console.error('Error deleting cart item:', error);
    }
  };

  const handleCheckout = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/v1/checkout', { userId });
      console.log('Checkout successful:', response.data);
      navigate('/order-confirmation');
    } catch (error) {
      console.error('Error during checkout:', error);
    }
  };

  const handleContinueShopping = () => {
    navigate('/menu');
  };

  return (
    <div className="cart-items-container">
      <h2 className="cart-title">Your Cart Items</h2>
      <form className="cart-form">
        <div className="cart-items">
          {cartItems.map((item, index) => (
            <div key={index} className="cart-item">
              <div className="item-details">
                <span className="item-name">{item.menuName}</span>
                <span className="item-price">Rs. {item.menuPrice.toFixed(2)}</span>
              </div>
              <div className="item-quantity">
                <button
                  type="button"
                  onClick={() => handleQuantityChange(index, -1)}
                  className="quantity-btn"
                >
                  -
                </button>
                <span className="quantity-value">{item.quantity}</span>
                <button
                  type="button"
                  onClick={() => handleQuantityChange(index, 1)}
                  className="quantity-btn"
                >
                  +
                </button>
              </div>
              <div className="item-total-price">
                Rs. {(item.menuPrice * item.quantity).toFixed(2)}
              </div>
              <button
                type="button"
                onClick={() => handleRemoveItem(index)}
                className="remove-btn"
              >
                🗑
              </button>
            </div>
          ))}
        </div>
        <div className="cart-summary">
          <div className="subtotal">
            <span>Subtotal:</span>
            <span>Rs. {subtotal.toFixed(2)}</span>
          </div>
          <div className="total-items">
            <span>{cartItems.length} item(s)</span>
          </div>
        </div>
        <div className="cart-actions">
          <button type="button" className="continue-shopping" onClick={handleContinueShopping}>
            Continue Shopping
          </button>
          <button type="button" className="checkout" onClick={handleCheckout}>
            Checkout
          </button>
        </div>
      </form>
      <div className="spacer"></div>
    </div>
  );
}

export default CartItems;
