package com.Health_Management.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "healthprofiles")
public class HealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int id;

    private Integer age;
    private Double height;
    private Double weight;
    private Double bmi;

    @Column(name = "heart_rate", nullable = false)
    private Integer heartRate = 0;

    private Integer steps;

    @Column(name = "water_intake")
    private Double waterIntake;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the height
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * @return the bmi
     */
    public Double getBmi() {
        return bmi;
    }

    /**
     * @param bmi the bmi to set
     */
    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    /**
     * @return the heartRate
     */
    public Integer getHeartRate() {
        return heartRate;
    }

    /**
     * @param heartRate the heartRate to set
     */
    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * @return the steps
     */
    public Integer getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    /**
     * @return the waterIntake
     */
    public Double getWaterIntake() {
        return waterIntake;
    }

    /**
     * @param waterIntake the waterIntake to set
     */
    public void setWaterIntake(Double waterIntake) {
        this.waterIntake = waterIntake;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

   
}
