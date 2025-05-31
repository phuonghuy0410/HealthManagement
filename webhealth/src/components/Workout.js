// src/components/Workout.js
import React, { useEffect, useState } from "react";
import { api } from "../configs/Apis";

function Workout() {
  const [todayPlans, setTodayPlans] = useState([]);
  const [workouts, setWorkouts] = useState([]);
  const [formData, setFormData] = useState({
    workoutDate: new Date().toISOString().slice(0, 10),
    exerciseName: "",
    duration: 0,
    caloriesBurned: 0,
    note: "",
  });

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (userId) {
      api.getPlansByUser(userId).then(res => setTodayPlans(res.data)).catch(console.error);
      api.getWorkoutsByUser(userId).then(res => setWorkouts(res.data)).catch(console.error);
    }
  }, [userId]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.createWorkout({ ...formData, user: { userId } });
      const res = await api.getWorkoutsByUser(userId);
      setWorkouts(res.data);
      setFormData({
        workoutDate: new Date().toISOString().slice(0, 10),
        exerciseName: "",
        duration: 0,
        caloriesBurned: 0,
        note: "",
      });
      alert("✔ Thêm buổi tập thành công!");
    } catch (err) {
      console.error(err);
      alert("❌ Lỗi khi thêm buổi tập.");
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-3">Buổi tập hôm nay</h2>

      <h5>Kế hoạch hôm nay</h5>
      {todayPlans.length === 0 ? (
        <div className="alert alert-secondary">Không có kế hoạch nào hôm nay.</div>
      ) : (
        <ul className="list-group mb-4">
          {todayPlans.map((p) => (
            <li key={p.planId} className="list-group-item">
              {p.title}: {p.description}
            </li>
          ))}
        </ul>
      )}

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Ngày tập:</label>
          <input
            type="date"
            className="form-control"
            name="workoutDate"
            value={formData.workoutDate}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Tên bài tập:</label>
          <input
            type="text"
            className="form-control"
            name="exerciseName"
            value={formData.exerciseName}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Thời lượng (phút):</label>
          <input
            type="number"
            className="form-control"
            name="duration"
            value={formData.duration}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Calories tiêu hao:</label>
          <input
            type="number"
            className="form-control"
            name="caloriesBurned"
            value={formData.caloriesBurned}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Ghi chú sau tập:</label>
          <textarea
            className="form-control"
            name="note"
            value={formData.note}
            onChange={handleChange}
          ></textarea>
        </div>

        <button type="submit" className="btn btn-success">✔ Hoàn thành buổi tập</button>
      </form>

      <h4 className="mt-5">Lịch sử buổi tập</h4>
      <table className="table table-bordered text-center mt-3">
        <thead className="table-dark">
          <tr>
            <th>Ngày</th>
            <th>Bài tập</th>
            <th>Thời lượng</th>
            <th>Calories</th>
            <th>Ghi chú</th>
          </tr>
        </thead>
        <tbody>
          {workouts.map((w) => (
            <tr key={w.workoutId}>
              <td>{new Date(w.workoutDate).toLocaleDateString("vi-VN")}</td>
              <td>{w.exerciseName}</td>
              <td>{w.duration} phút</td>
              <td>{w.caloriesBurned}</td>
              <td>{w.note}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Workout;
