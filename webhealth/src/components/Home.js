// src/components/Home.js
import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  const isLoggedIn = !!localStorage.getItem("userId");

  return (
    <div className="container mt-5 text-center">
      <h1 className="mb-3">Ứng dụng Quản lý Sức khỏe</h1>
      <p>Chào mừng bạn đến với hệ thống quản lý sức khỏe và theo dõi hoạt động cá nhân.</p>

      {!isLoggedIn && (
        <div className="mt-4">
          <Link to="/login" className="btn btn-primary m-2">Đăng nhập</Link>
          <Link to="/register" className="btn btn-success m-2">Đăng ký</Link>
        </div>
      )}
    </div>
  );
};

export default Home;
