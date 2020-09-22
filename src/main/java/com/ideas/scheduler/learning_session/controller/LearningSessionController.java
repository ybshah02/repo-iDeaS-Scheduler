package com.ideas.scheduler.learning_session.controller;


import com.ideas.scheduler.client.exception.NoDataFoundException;
import com.ideas.scheduler.learning_session.exception.LSNotFoundException;
import com.ideas.scheduler.learning_session.repository.LearningSessionRepository;
import com.ideas.scheduler.learning_session.service.LearningSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LearningSessionController {

    @Autowired
    private LearningSessionRepository LSrepository;

    @GetMapping("/learning-sessions")
    public List<LearningSession> getLearningSessions() {
        List<LearningSession> lsList = LSrepository.findAll();

        if (lsList.isEmpty()) {
            throw new NoDataFoundException();
        } else {
            return lsList;
        }
    }

    @GetMapping("/learning-sessions/{id}")
    public LearningSession getLearningSessionById(@PathVariable("id") int id) {
        LearningSession ls = LSrepository.findById(id);
        if (ls != null) {
            return ls;
        } else {
            throw new LSNotFoundException(id);
        }
    }

    @GetMapping("/learning-sessions/{id}/active")
    public boolean isLearningSessionActive(@PathVariable("id") int id) {
        LearningSession ls = LSrepository.findById(id);
        if (ls != null) {
            return ls.isActive();
        } else {
            throw new LSNotFoundException(id);
        }
    }

    @PostMapping("/learning-sessions")
    public void addLG(@Validated @RequestBody LearningSession ls) {
        LSrepository.save(ls);
    }

    @DeleteMapping("/learning-sessions/{id}")
    public void hardDeleteLS(@PathVariable("id") int id) {

        if (LSrepository.findById(id) != null) {
            LSrepository.deleteById(id);
        } else {
            throw new LSNotFoundException(id);
        }
    }

    @PutMapping("/learning-sessions/{id}/active")
    public void softDeleteLS(@PathVariable("id") int id) {
        LearningSession ls = LSrepository.findById(id);
        if (ls != null) {
            ls.setInactive();
        } else {
            throw new LSNotFoundException(id);
        }
    }

    @PutMapping("/learning-sessions/{id}")
    public void updateLS(@PathVariable("id") int id, @RequestBody LearningSession ls) {
        LearningSession LSToModify = LSrepository.findById(id);

        if (LSToModify == null) {
            throw new LSNotFoundException(id);
        } else {

            ls.setID(LSToModify.getID());

            if (!StringUtils.isEmpty(ls.getName())) {
                LSToModify.setName(ls.getName());
            }

            if (!StringUtils.isEmpty(ls.getCode())) {
                LSToModify.setCode(ls.getCode());
            }

            if (!StringUtils.isEmpty(ls.getLearningGroupId())) {
                LSToModify.setLearningGroupId(ls.getLearningGroupId());
            }

            if (!StringUtils.isEmpty(ls.getDuration())) {
                LSToModify.setDuration(ls.getDuration());
            }

            if (!StringUtils.isEmpty(ls.getPreDuration())) {
                LSToModify.setPreDuration(ls.getPreDuration());
            }

            if (!StringUtils.isEmpty(ls.getPostDuration())) {
                LSToModify.setPostDuration(ls.getPostDuration());
            }

            LSrepository.save(LSToModify);
        }
    }
}

