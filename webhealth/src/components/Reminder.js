// src/components/Reminder.js
import React, { useEffect, useState } from "react";
import { api } from "../configs/Apis";

function Reminder() {
  const [reminders, setReminders] = useState([]);
  const [form, setForm] = useState({
    reminderType: "Water",
    message: "",
    reminderTime: "",
    reminderDate: new Date().toISOString().slice(0, 10),
  });

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (userId) fetchReminders();
  }, [userId]);

  const fetchReminders = async () => {
    try {
      const res = await api.getRemindersByUser(userId);
      setReminders(res.data);
    } catch (err) {
      console.error("Lỗi khi tải nhắc nhở:", err);
    }
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.createReminder({
        ...form,
        user: { userId },
      });
      fetchReminders();
      setForm({
        reminderType: "Water",
        message: "",
        reminderTime: "",
        reminderDate: new Date().toISOString().slice(0, 10),
      });
    } catch (err) {
      console.error("Lỗi khi tạo nhắc nhở:", err);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-3 text-center">🔔 Quản lý nhắc nhở</h2>

      <form onSubmit={handleSubmit} className="mb-4">
        <div className="mb-3">
          <label className="form-label">Loại nhắc nhở</label>
          <select
            className="form-select"
            name="reminderType"
            value={form.reminderType}
            onChange={handleChange}
            required
          >
            <option value="Water">Uống nước</option>
            <option value="Exercise">Tập luyện</option>
            <option value="Rest">Nghỉ ngơi</option>
          </select>
        </div>
        <div className="mb-3">
          <label className="form-label">Nội dung nhắc nhở</label>
          <input
            type="text"
            className="form-control"
            name="message"
            value={form.message}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Giờ nhắc nhở</label>
          <input
            type="time"
            className="form-control"
            name="reminderTime"
            value={form.reminderTime}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Lưu nhắc nhở</button>
      </form>

      <h4 className="mt-5">📋 Danh sách nhắc nhở</h4>
      <table className="table table-bordered text-center">
        <thead className="table-light">
          <tr>
            <th>Loại</th>
            <th>Nội dung</th>
            <th>Giờ</th>
          </tr>
        </thead>
        <tbody>
          {reminders.map((r) => (
            <tr key={r.reminderId}>
              <td>{r.reminderType}</td>
              <td>{r.message}</td>
              <td>{r.reminderTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Reminder;
