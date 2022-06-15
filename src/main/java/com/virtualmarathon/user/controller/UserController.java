package com.virtualmarathon.user.controller;

import com.virtualmarathon.user.customerror.UserInfoException;
import com.virtualmarathon.user.entity.Lap;
import com.virtualmarathon.user.entity.Marathon;
import com.virtualmarathon.user.entity.User;
import com.virtualmarathon.user.repository.UserRepository;
import com.virtualmarathon.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/get-total-points")
    public Long getTotalPoints(@RequestHeader("username") String username){
        return userService.getTotalPoints(username);
    }

    @GetMapping("/get-all-laps")
    public List<Lap> getAllLaps(@RequestHeader("username") String email){
        return userService.getAllLaps(email);
    }

    @GetMapping("/get-valid-submissions-count")
    public Long getValidLapCount(@RequestHeader("username") String email){
        return userService.getValidLapCount(email);
    }

    @GetMapping("/get-user")
    public User getUser(@RequestHeader("username") String email){
        return userService.getUser(email);
    }

    @PutMapping("/new-submission/{username}")
    public User addLap(@PathVariable("username") String email,@RequestBody Lap lap){
        return userService.addLap(email,lap);
    }

    @PutMapping("/update-marathon-completed/{username}")
    public User updateMarathonCompleted(@PathVariable("username") String email){
        return userService.updateMarathonCompleted(email);
    }

    @PutMapping("/update-user-points")
    public void updateUserPoints(@RequestBody List<Lap> lapList){
        userService.updateUserPoints(lapList);
    }

    @PutMapping("/update-organizer-count/{is-complete}")
    public boolean updateMarathonsOrganized(@PathVariable("is-complete") boolean isComplete,@RequestBody Marathon marathon){
        return userService.updateMarathonsOrganized(isComplete,marathon);
    }

    @GetMapping("/get-paginated-global-leaderboard")
    public Page<User> getGlobalLeaderBoard(@RequestParam int pageLength, @RequestParam int pageNumber){
        if (pageLength<1 || pageLength > 100 || pageNumber<0 || pageNumber > userRepository.getTotalUsers()/ pageLength) {
            throw new UserInfoException("Page cannot be rendered", HttpStatus.NOT_ACCEPTABLE);
        }
        Pageable pageable= PageRequest.of(pageNumber,pageLength);
        return userRepository.getAllUsersSortedAndPaginated(pageable);
    }

    @GetMapping("/get-global-leaderboard")
    public List<User> getGlobalLeaderBoard(){
        return userRepository.getAllUsersSorted();
    }
}
