import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../configs/Apis";

function Login() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ username: "", password: "" });
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const res = await api.loginUser(form);
      const user = res.data;

      if (!user || !user.userId) {
        setError("Sai thông tin đăng nhập.");
        return;
      }

      // ✅ Lưu user vào localStorage
      localStorage.setItem("userId", user.userId);
      localStorage.setItem("username", user.username);
      localStorage.setItem("role", user.role || "");

      // ✅ Chuyển đến trang chính sau đăng nhập
      navigate("/healthprofile");
    } catch (err) {
      console.error("Đăng nhập thất bại:", err);
      setError("Tên đăng nhập hoặc mật khẩu không đúng!");
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: "400px" }}>
      <h2 className="text-center mb-3">Đăng nhập hệ thống</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Tên đăng nhập:</label>
          <input
            type="text"
            name="username"
            className="form-control"
            value={form.username}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Mật khẩu:</label>
          <input
            type="password"
            name="password"
            className="form-control"
            value={form.password}
            onChange={handleChange}
            required
          />
        </div>
        {error && <div className="alert alert-danger text-center">{error}</div>}
        <button type="submit" className="btn btn-primary w-100">
          Đăng nhập
        </button>
      </form>

      <div className="text-center mt-3">
        <p>Chưa có tài khoản?</p>
        <a href="/register" className="btn btn-outline-success w-100">
          Đăng ký ngay
        </a>
      </div>
    </div>
  );
}

export default Login;
