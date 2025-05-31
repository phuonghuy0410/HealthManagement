// src/components/Plan.js
import React, { useEffect, useState } from "react";
import { api } from "../configs/Apis";
import { useNavigate } from "react-router-dom";

function Plan() {
  const [plans, setPlans] = useState([]);
  const [form, setForm] = useState({
    title: "",
    description: "",
    startDate: new Date().toISOString().slice(0, 10),
  });

  const navigate = useNavigate();

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (!userId) {
      navigate("/login");
    } else {
      fetchPlans();
    }
  }, [userId]);

  const fetchPlans = async () => {
    try {
      const res = await api.getPlansByUser(userId);
      setPlans(res.data);
    } catch (err) {
      console.error("Lỗi khi tải kế hoạch:", err);
    }
  };

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.createPlan({
        ...form,
        user: { userId: parseInt(userId) },
      });
      fetchPlans();
      setForm({
        title: "",
        description: "",
        startDate: new Date().toISOString().slice(0, 10),
      });
    } catch (err) {
      console.error("Lỗi khi lưu kế hoạch:", err);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4">📋 Kế hoạch tập luyện / dinh dưỡng</h2>

      <form onSubmit={handleSubmit} className="mb-4">
        <div className="mb-3">
          <label className="form-label">Tiêu đề:</label>
          <input
            type="text"
            name="title"
            className="form-control"
            value={form.title}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Mô tả:</label>
          <textarea
            name="description"
            className="form-control"
            value={form.description}
            onChange={handleChange}
            required
          ></textarea>
        </div>
        <div className="mb-3">
          <label className="form-label">Ngày bắt đầu:</label>
          <input
            type="date"
            name="startDate"
            className="form-control"
            value={form.startDate}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Lưu kế hoạch</button>
      </form>

      <table className="table table-bordered text-center">
        <thead className="table-light">
          <tr>
            <th>Tiêu đề</th>
            <th>Mô tả</th>
            <th>Ngày bắt đầu</th>
          </tr>
        </thead>
        <tbody>
          {plans.map((p) => (
            <tr key={p.planId}>
              <td>{p.title}</td>
              <td>{p.description}</td>
              <td>{new Date(p.startDate).toLocaleDateString("vi-VN")}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Plan;
