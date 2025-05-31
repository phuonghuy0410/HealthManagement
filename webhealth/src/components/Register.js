import React, { useState } from "react";
import { api } from "../configs/Apis";
import { useNavigate } from "react-router-dom";

function Register() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    username: "",
    password: "",
    fullName: "",
    role: "Người dùng", // mặc định
  });

  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const res = await api.registerUser(form);
      if (res.data === "SUCCESS") {
        alert("Đăng ký thành công!");
        navigate("/login");
      } else if (res.data === "EXISTS") {
        setError("Tên đăng nhập đã tồn tại!");
      } else {
        setError("Đã có lỗi xảy ra. Vui lòng thử lại.");
      }
    } catch (err) {
      console.error("Lỗi đăng ký:", err);
      setError("Không thể kết nối đến máy chủ.");
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: "450px" }}>
      <h2 className="text-center mb-4">Đăng ký tài khoản</h2>

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Tên đăng nhập:</label>
          <input
            type="text"
            className="form-control"
            name="username"
            value={form.username}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Mật khẩu:</label>
          <input
            type="password"
            className="form-control"
            name="password"
            value={form.password}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Họ và tên:</label>
          <input
            type="text"
            className="form-control"
            name="fullName"
            value={form.fullName}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label>Vai trò:</label>
          <select
            className="form-select"
            name="role"
            value={form.role}
            onChange={handleChange}
          >
            <option value="User">Người dùng</option>
            <option value="Expert">Huấn luyện viên</option>
            
          </select>
        </div>

        {error && <div className="alert alert-danger text-center">{error}</div>}

        <button type="submit" className="btn btn-success w-100">
          Đăng ký
        </button>
      </form>
    </div>
  );
}

export default Register;
