import React, { useRef } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const BasePage = () => {
  const usernameRef = useRef(null);
  const passwordRef = useRef(null);
  const regUsernameRef = useRef(null);
  const regPasswordRef = useRef(null);
  const regBalanceRef = useRef(null);
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const { data } = await axios.post("http://localhost:8081/login", {
        username: usernameRef.current.value,
        password: passwordRef.current.value
      });

      if (data.Message === "LoginSuccess") {
        navigate('/dashboard', {
          state: {
            username: data.username,
            balance: data.Balance,
            id: data.id
          }
        });
      } else {
        alert("Login failed. Check credentials.");
      }
    } catch (err) {
      alert("Server error during login.");
      console.log(err);
    }
  };

  const handleRegister = async () => {
    try {
      const { data } = await axios.post("http://localhost:8081/createAccount", {
        username: regUsernameRef.current.value,
        password: regPasswordRef.current.value,
        balance: parseFloat(regBalanceRef.current.value)
      });

      if (data != null) {
        alert("Registered successfully!");
      }
    } catch (error) {
      alert("Error during registration.");
    }
  };

  return (
    <div>
      <fieldset>
        <legend>Login</legend>
        <input type="text" ref={usernameRef} placeholder="Username" />
        <input type="password" ref={passwordRef} placeholder="Password" />
        <button onClick={handleLogin}>Login</button>
      </fieldset>

      <fieldset>
        <legend>Register</legend>
        <input type="text" ref={regUsernameRef} placeholder="Username" />
        <input type="password" ref={regPasswordRef} placeholder="Password" />
        <input type="number" ref={regBalanceRef} placeholder="Initial Balance" />
        <button onClick={handleRegister}>Register</button>
      </fieldset>
    </div>
  );
};

export default BasePage;
