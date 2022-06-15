package com.virtualmarathon.user.service;

import com.virtualmarathon.user.entity.Lap;
import com.virtualmarathon.user.entity.Marathon;
import com.virtualmarathon.user.entity.User;
import com.virtualmarathon.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Long getTotalPoints(String username){
        return userRepository.getUserByEmail(username).getTotalPoints();
    }

    public List<Lap> getAllLaps(String email){
        List<Lap> allParticipatedLaps=userRepository.getUserByEmail(email).getLapList();
        List<Lap> allSubmittedLapList= new ArrayList<>();
        for(Lap lap:allParticipatedLaps){
            System.out.println(lap);
            if(lap.getTimeOfSubmission()!=null){
                allSubmittedLapList.add(lap);
            }
        }
        return allSubmittedLapList;
    }

    public Long getValidLapCount(String email){
        return userRepository.getUserByEmail(email).getMarathonsCompleted();
    }

    public User getUser(String email){
        return userRepository.getUserByEmail(email);
    }

    public User addLap(String email, Lap lap){
        User user=userRepository.getUserByEmail(email);
        user.addLap(lap);
        userRepository.modifyUser(user);
        return user;
    }

    public User updateMarathonCompleted(String email){
        User user=userRepository.getUserByEmail(email);
        user.setMarathonsCompleted(user.getMarathonsCompleted()+1);
        userRepository.modifyUser(user);
        return user;
    }

    public void updateUserPoints(List<Lap> lapList){
        for (Lap lap:lapList){
            User user=userRepository.getUserByEmail(lap.getAthlete().getEmail());
            user.setTotalPoints(user.getTotalPoints()+lap.getPoints());
            userRepository.modifyUser(user);
        }
    }

    public boolean updateMarathonsOrganized(boolean isComplete, Marathon marathon){
        User user = marathon.getOrganizer();
        if(isComplete){
            if(user.getCurrentOrganizerCount()<=10){
                user.setCurrentOrganizerCount(user.getCurrentOrganizerCount()+1);
                return true;
            }
            return false;
        }else{
            user.setCurrentOrganizerCount(user.getCurrentOrganizerCount()-1);
            return true;
        }

    }

}
