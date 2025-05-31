// src/App.js
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";


import Header from "./components/layouts/Header";
import Footer from "./components/layouts/Footer";
import Home from "./components/Home";
import Register from "./components/Register";
import Login from "./components/Login";
import HealthProfile from "./components/HealthProfile";
import Workout from "./components/Workout";
import Plan from "./components/Plan";
import Reminder from "./components/Reminder";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/healthprofile" element={<HealthProfile />} />
        <Route path="/workout" element={<Workout />} />
        <Route path="/plan" element={<Plan />} />
        <Route path="/reminder" element={<Reminder />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
