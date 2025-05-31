// src/components/layouts/Header.js
import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();
  const username = localStorage.getItem("username");
  const fullName = localStorage.getItem("fullName"); // nếu có lưu
  const role = localStorage.getItem("role");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">Sức khỏe</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarContent">
          <ul className="navbar-nav me-auto">
            <li className="nav-item"><Link className="nav-link" to="/">Trang chủ</Link></li>

            {/* Chỉ hiển thị nếu đã đăng nhập và KHÔNG PHẢI là chuyên gia */}
            {username && role !== "Expert" && (
              <>
                <li className="nav-item"><Link className="nav-link" to="/healthprofile">Hồ sơ</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/workout">Buổi tập</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/plan">Kế hoạch</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/reminder">Nhắc nhở</Link></li>
                
              </>
            )}

            {/* Chỉ hiển thị nếu role là Expert */}
            {username && role === "Expert" && (
              <li className="nav-item"><Link className="nav-link" to="/users">Danh sách người dùng</Link></li>
            )}
          </ul>

          <ul className="navbar-nav">
            {username ? (
              <>
                <li className="nav-item">
                  <span className="nav-link disabled text-white">👤 {username}</span>
                </li>
                <li className="nav-item">
                  <button className="nav-link btn btn-link text-warning" onClick={handleLogout}>
                    Đăng xuất
                  </button>
                </li>
              </>
            ) : (
              <>
                <li className="nav-item"><Link className="nav-link" to="/login">Đăng nhập</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/register">Đăng ký</Link></li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Header;
