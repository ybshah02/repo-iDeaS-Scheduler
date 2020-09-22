package com.ideas.scheduler.resource.controller;

import com.ideas.scheduler.client.exception.NoDataFoundException;
import com.ideas.scheduler.resource.exception.LanguagesNotFoundException;
import com.ideas.scheduler.resource.exception.ProficienciesNotFoundException;
import com.ideas.scheduler.resource.exception.ResourceLanguagesNotFoundException;
import com.ideas.scheduler.resource.exception.ResourceNotFoundException;
import com.ideas.scheduler.resource.repository.*;
import com.ideas.scheduler.resource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ResourceController {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceLanguageRepository resourceLanguageRepository;

    @Autowired
    private LanguagesRepository languagesRepository;

    @Autowired
    private ProficiencyRepository proficiencyRepository;

    @Autowired
    private CapabilitiesRepository capabilitiesRepository;

    @GetMapping("/resources")
    List<Resource> getResources() {

        List<Resource> resources = resourceRepository.findAll();
        if (resources.isEmpty()) {
            throw new NoDataFoundException();
        } else {
            return resources;
        }
    }

    @GetMapping("resources/{id}")
    public Resource getResourceById(@PathVariable("id") int id) {
        Resource resource = resourceRepository.findById(id);
        if (resource != null) {
            return resource;
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @PostMapping("/resources")
    void addResource(@Validated @RequestBody Resource resource) {
        resourceRepository.save(resource);
    }

    @DeleteMapping("/resources/{id}")
    void hardDeleteResourceById(@PathVariable int id) {

        if (resourceRepository.findById(id) != null) {
            resourceRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }

    }

    @PutMapping("/resources/{id}/active")
    void changeActiveOfResourceById(@PathVariable int id, @RequestParam boolean active) {

        Resource resource = resourceRepository.findById(id);
        if (resource != null) {
            if (active) {
                resource.setActive();
            } else {
                resource.setInactive();
                softDeleteResourceLanguageById(id);
            }
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @PutMapping("/resources/{id}")
    void modifyResource(@PathVariable("id") int id, @RequestBody Resource resource) {

        Resource resourceToModify = resourceRepository.findById(id);

        if (resourceToModify == null) {
            throw new ResourceNotFoundException(id);
        } else {
            resource.setID(resourceToModify.getID());

            if (!StringUtils.isEmpty(resource.getFirstName())) {
                resourceToModify.setFirstName(resource.getFirstName());
            }

            if (!StringUtils.isEmpty(resource.getLastName())) {
                resourceToModify.setLastName(resource.getLastName());
            }

            if (!StringUtils.isEmpty(resource.getEmail())) {
                resourceToModify.setEmail(resource.getEmail());
            }

            if (!StringUtils.isEmpty(resource.getRegionId())) {
                resourceToModify.setRegionId(resource.getRegionId());
            }

            resourceRepository.save(resourceToModify);
        }
    }

    @GetMapping("resources/capabilities")
    public List<Capabilities> getAllResourceCapabilities() {
        List<Capabilities> capabilities = capabilitiesRepository.findAll();
        if (capabilities.isEmpty()){
            throw new NoDataFoundException();
        }else{
            return capabilities;
        }
    }

    @GetMapping("resources/{id}/capabilities")
    public List<Capabilities> getResourceCapabilities(@PathVariable int id) {
        List<Capabilities> capabilitiesByResource = capabilitiesRepository.findAllByResourceId(id);
        if (capabilitiesByResource.isEmpty()){
            throw new NoDataFoundException();
        }else{
            return capabilitiesByResource;
        }

    }

    @PostMapping("/resources/capabilities")
    void addResourceCapabilities(@Validated @RequestBody List<Capabilities> capabilities) {
        for (Capabilities capability : capabilities) {
            capabilitiesRepository.save(capability);
        }
    }

    @GetMapping("/resources/resource-languages")
    List<ResourceLanguage> getAllResourceLanguages() {
        return resourceLanguageRepository.findAll();
    }

    @GetMapping("/resources/{resourceId}/resource-languages")
    List<ResourceLanguage> getLanguagesByResourceId(@PathVariable("resourceId") int resourceId) {
        return resourceLanguageRepository.findAllByResourceId(resourceId);
    }

    @PostMapping("/resources/resource-languages")
    void addResourceLanguage(@RequestBody ResourceLanguage resourceLanguage) {
        resourceLanguageRepository.save(resourceLanguage);
    }

    @DeleteMapping("/resources/{id}/resource-languages")
    void hardDeleteResourceLanguageById(@PathVariable int id) {
        resourceLanguageRepository.deleteAllByResourceId(id);
    }

    @PutMapping("/resources/{id}/resource-languages")
    void softDeleteResourceLanguageById(@PathVariable int id) {
        List<ResourceLanguage> resourceLanguages = resourceLanguageRepository.findAllByResourceId(id);
        for (ResourceLanguage resourceLanguage : resourceLanguages) {
            resourceLanguage.setInactive();
        }
    }

    @GetMapping("/resources/resource-languages/languages")
    List<Languages> getLanguages() {

        List<Languages> languages = languagesRepository.findAll();
        if (languages.isEmpty()){
            throw new NoDataFoundException();
        }else{
            return languages;
        }
    }

    @GetMapping("/resources/resource-languages/proficiencies")
    List<Proficiency> getProficiencies() {

        List<Proficiency> proficiencies = proficiencyRepository.findAll();
        if (proficiencies.isEmpty()){
            throw new NoDataFoundException();
        }else{
            return proficiencies;
        }
    }

    @GetMapping("/resources/{resourceId}/resource-languages/languages")
    List<String> getLanguagesOfResource(@PathVariable("resourceId") int resourceId) {
        List<ResourceLanguage> allResourceLanguages = resourceLanguageRepository.findAllByResourceId(resourceId);
        if (allResourceLanguages.isEmpty()){
            throw new ResourceLanguagesNotFoundException(resourceId);
        }else {
            List<String> languages = new ArrayList<String>();

            for (ResourceLanguage resourceLanguage : allResourceLanguages) {
                Languages language = languagesRepository.findLanguagesById(resourceLanguage.getLanguageId());
                if (language != null) {
                    languages.add(language.getLanguage());
                }else{
                    throw new LanguagesNotFoundException(resourceLanguage.getLanguageId());
                }
            }
            return languages;
        }
    }

    @GetMapping("/resources/{resourceId}/resource-languages/proficiencies")
    List<String> getProficiencyOfResource(@PathVariable("resourceId") int resourceId) {
        List<ResourceLanguage> allResourceLanguages = resourceLanguageRepository.findAllByResourceId(resourceId);
        if (allResourceLanguages.isEmpty()){
            throw new ResourceLanguagesNotFoundException(resourceId);
        }else {
            List<String> proficiencies = new ArrayList<String>();

            for (ResourceLanguage resourceLanguage : allResourceLanguages) {
                Proficiency proficiency = proficiencyRepository.findProficiencyById(resourceLanguage.getProficiencyId());
                if (proficiency != null){
                    proficiencies.add(proficiency.getProficiency());
                } else {
                    throw new ProficienciesNotFoundException(resourceLanguage.getProficiencyId());
                }
            }
            return proficiencies;
        }
    }
}

