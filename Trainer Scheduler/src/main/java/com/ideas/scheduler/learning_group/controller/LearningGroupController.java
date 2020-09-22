package com.ideas.scheduler.learning_group.controller;

import com.ideas.scheduler.client.exception.NoDataFoundException;
import com.ideas.scheduler.learning_group.exception.LGNotFoundException;
import com.ideas.scheduler.learning_group.repository.LearningGroupRepository;
import com.ideas.scheduler.learning_group.service.LearningGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LearningGroupController {

    @Autowired
    private LearningGroupRepository LGrepository;

    @GetMapping("/learning-groups")
    public List<LearningGroup> getLearningGroups() {
        List<LearningGroup> lgList = LGrepository.findAll();
        if (lgList.isEmpty()) {
            throw new NoDataFoundException();
        } else {
            return lgList;
        }
    }

    @GetMapping("/learning-groups/{id}")
    public LearningGroup getLearningGroupById(@PathVariable("id") int id) {
        LearningGroup lg = LGrepository.findById(id);
        if (lg != null) {
            return lg;
        } else {
            throw new LGNotFoundException(id);
        }
    }

    @GetMapping("/learning-groups/{id}/active")
    public boolean isLearningGroupActive(@PathVariable("id") int id) {
        LearningGroup lg = LGrepository.findById(id);
        if (lg != null) {
            return lg.isActive();
        } else {
            throw new LGNotFoundException(id);
        }
    }

    @PostMapping("/learning-groups")
    public void addLG(@Validated @RequestBody LearningGroup lg) {
        LGrepository.save(lg);
    }

    @DeleteMapping("/learning-groups/{id}")
    public void hardDeleteLG(@PathVariable("id") int id) {
        if (LGrepository.findById(id) != null) {
            LGrepository.deleteById(id);
        } else {
            throw new LGNotFoundException(id);
        }
    }

    @PutMapping("learning-groups/{id}/active")
    public void softDeleteLG(@PathVariable("id") int id) {
        LearningGroup lg = LGrepository.findById(id);
        if (lg != null) {
            lg.setInactive();
        } else {
            throw new LGNotFoundException(id);
        }
    }

    // same form of modification as explained in the Client Controller

    @PutMapping("/learning-groups/{id}")
    public void updateLG(@PathVariable("id") int id, @RequestBody LearningGroup lg) {
        LearningGroup LGToModify = LGrepository.findById(id);

        if (LGToModify == null) {
            throw new LGNotFoundException(id);
        } else {

            lg.setID(LGToModify.getID());

            if (!StringUtils.isEmpty(lg.getName())) {
                LGToModify.setName(lg.getName());
            }

            if (!StringUtils.isEmpty(lg.getCode())) {
                LGToModify.setCode(lg.getCode());
            }

            if (!StringUtils.isEmpty(lg.getDescription())) {
                LGToModify.setDescription(lg.getDescription());
            }

            LGrepository.save(LGToModify);
        }
    }
}
