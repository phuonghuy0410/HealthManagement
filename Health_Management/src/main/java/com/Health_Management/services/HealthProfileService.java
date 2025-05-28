/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.services;

import com.Health_Management.pojo.HealthProfile;
import com.Health_Management.Repository.HealthProfileRepository;
import java.util.List;

public class HealthProfileService {
    private final HealthProfileRepository profileRepository = new HealthProfileRepository();

    public boolean saveOrUpdateProfile(HealthProfile profile) {
        return profileRepository.saveOrUpdateHealthProfile(profile);
    }

    public HealthProfile getProfileByUserId(int userId) {
        return profileRepository.getByUserId(userId);
    }

    public List<HealthProfile> getAllProfiles() {
        return profileRepository.getAllProfiles();
    }
}

