import React, { useEffect, useState } from "react";
import { api } from "../configs/Apis";

const HealthProfile = () => {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState("");
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (userId) {
      api.getHealthProfiles()
        .then(res => {
          const userProfile = res.data.find(p => p.user.userId == userId);
          if (userProfile) setProfile(userProfile);
        })
        .catch(err => {
          console.error("Lỗi tải hồ sơ:", err);
          setError("Không thể tải hồ sơ.");
        });
    }
  }, [userId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    const updatedProfile = {
      ...profile,
      [name]: value,
    };

    // Tự động tính BMI khi thay đổi cân nặng hoặc chiều cao
    if (name === "height" || name === "weight") {
      const height = name === "height" ? value : updatedProfile.height;
      const weight = name === "weight" ? value : updatedProfile.weight;
      const h = parseFloat(height) / 100;
      const w = parseFloat(weight);
      const bmi = h && w ? (w / (h * h)).toFixed(2) : 0;
      updatedProfile.bmi = bmi;
    }

    setProfile(updatedProfile);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.updateHealthProfile(profile.id, {
        ...profile,
        user: { userId: parseInt(userId) }
      });
      alert("✔ Cập nhật hồ sơ thành công!");
    } catch (err) {
      console.error("Lỗi khi cập nhật:", err);
      alert("❌ Lỗi khi cập nhật hồ sơ!");
    }
  };

  if (!profile) return <div className="container mt-4">Đang tải hồ sơ...</div>;

  return (
    <div className="container mt-4" style={{ maxWidth: "600px" }}>
      <h2 className="text-center mb-4">Hồ sơ sức khỏe</h2>
      <form onSubmit={handleSubmit}>
        <input type="hidden" name="id" value={profile.id} />

        <div className="mb-3">
          <label className="form-label">Tuổi:</label>
          <input
            type="number"
            name="age"
            value={profile.age}
            onChange={handleChange}
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Chiều cao (cm):</label>
          <input
            type="number"
            step="0.1"
            name="height"
            value={profile.height}
            onChange={handleChange}
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Cân nặng (kg):</label>
          <input
            type="number"
            step="0.1"
            name="weight"
            value={profile.weight}
            onChange={handleChange}
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Nhịp tim:</label>
          <input
            type="number"
            name="heartRate"
            value={profile.heartRate}
            onChange={handleChange}
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Số bước mỗi ngày:</label>
          <input
            type="number"
            name="steps"
            value={profile.steps}
            onChange={handleChange}
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Lượng nước (lít):</label>
          <input
            type="number"
            step="0.1"
            name="waterIntake"
            value={profile.waterIntake}
            onChange={handleChange}
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Chỉ số BMI:</label>
          <input
            type="number"
            name="bmi"
            value={profile.bmi}
            readOnly
            className="form-control"
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">Cập nhật</button>
      </form>
    </div>
  );
};

export default HealthProfile;
