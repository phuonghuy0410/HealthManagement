// src/api/Apis.js
import axios from "axios";

 
const BASE_URL = "http://localhost:8080/Health_Management/api";

export const api = {
  // ==== USERS ====
  registerUser: (user) =>
    axios.post(`${BASE_URL}/users/register`, user, {
      headers: { "Content-Type": "application/json" }
    }),

  loginUser: (credentials) =>
    axios.post(`${BASE_URL}/users/login`, credentials, {
      headers: { "Content-Type": "application/json" }
    }),

  getUserById: (id) =>
    axios.get(`${BASE_URL}/users/${id}`),

  updateUser: (id, userData) =>
    axios.put(`${BASE_URL}/users/${id}`, userData, {
      headers: { "Content-Type": "application/json" }
    }),

  deleteUser: (id) =>
    axios.delete(`${BASE_URL}/users/${id}`),

  // ==== HEALTH PROFILES ====
  getHealthProfiles: () =>
    axios.get(`${BASE_URL}/healthprofiles`),

  getHealthProfileById: (id) =>
    axios.get(`${BASE_URL}/healthprofiles/${id}`),

  createHealthProfile: (data) =>
    axios.post(`${BASE_URL}/healthprofiles`, data, {
      headers: { "Content-Type": "application/json" }
    }),

  updateHealthProfile: (id, data) =>
    axios.put(`${BASE_URL}/healthprofiles/${id}`, data, {
      headers: { "Content-Type": "application/json" }
    }),

  deleteHealthProfile: (id) =>
    axios.delete(`${BASE_URL}/healthprofiles/${id}`),

  // ==== WORKOUTS ====
  getWorkouts: () =>
    axios.get(`${BASE_URL}/workout`),

  getWorkoutsByUser: (userId) =>
    axios.get(`${BASE_URL}/workout/user/${userId}`),

  createWorkout: (data) =>
    axios.post(`${BASE_URL}/workout`, data, {
      headers: { "Content-Type": "application/json" }
    }),

  deleteWorkout: (id) =>
    axios.delete(`${BASE_URL}/workout/${id}`),

  // ==== REMINDERS ====
  getReminders: () =>
    axios.get(`${BASE_URL}/reminder`),

  getRemindersByUser: (userId) =>
    axios.get(`${BASE_URL}/reminder/user/${userId}`),

  createReminder: (data) =>
    axios.post(`${BASE_URL}/reminder`, data, {
      headers: { "Content-Type": "application/json" }
    }),

  deleteReminder: (id) =>
    axios.delete(`${BASE_URL}/reminder/${id}`),

  // ==== PLANS ====
  getPlans: () =>
    axios.get(`${BASE_URL}/plan`),

  getPlansByUser: (userId) =>
    axios.get(`${BASE_URL}/plan/user/${userId}`),

  createPlan: (data) =>
    axios.post(`${BASE_URL}/plan`, data, {
      headers: { "Content-Type": "application/json" }
    }),

  deletePlan: (id) =>
    axios.delete(`${BASE_URL}/plan/${id}`)
};
