import axios from 'axios';
import React, { useRef, useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const Home = () => {
  const refAdd = useRef(null);
  const refWithdraw = useRef(null);
  const location = useLocation();
  const [user, setUser] = useState(() => {
    return location.state || JSON.parse(localStorage.getItem("user") || "{}");
  });

  useEffect(() => {
    if (user?.id) {
      localStorage.setItem("user", JSON.stringify(user));
    }
  }, [user]);

  const { username, balance, id } = user;

  if (!username || !id) {
    return <p>Please log in first.</p>;
  }

  const updateBalance = async () => {
    const res = await axios.get(`http://localhost:8081/user/${id}`);
    setUser(prev => ({ ...prev, balance: res.data.balance }));
  };

  const handleAdd = async () => {
    try {
      await axios.post('http://localhost:8081/AddMoney', {
        id: id,
        balance: parseFloat(refAdd.current.value)
      });
      alert("Money added!");
      updateBalance();
    } catch (error) {
      console.error(error);
      alert("Add failed.");
    }
  };

  const handleWithdraw = async () => {
    try {
      await axios.post('http://localhost:8081/withdrawal', {
        id: id,
        balance: parseFloat(refWithdraw.current.value)
      });
      alert("Withdrawal successful!");
      updateBalance();
    } catch (error) {
      console.error(error);
      alert("Withdraw failed.");
    }
  };

  return (
    <div>
      <h1>Welcome, {username} 😊</h1>
      <h3>Your Current Balance: ₹{balance}</h3>

      <div>
        <h3>Add Money</h3>
        <input type="number" ref={refAdd} placeholder="Amount to add" />
        <button onClick={handleAdd}>Add</button>
      </div>

      <div>
        <h3>Withdraw Money</h3>
        <input type="number" ref={refWithdraw} placeholder="Amount to withdraw" />
        <button onClick={handleWithdraw}>Withdraw</button>
      </div>
    </div>
  );
};

export default Home;
