import {Component, OnInit} from '@angular/core';
import {Resource} from '../resource';
import {ActivatedRoute, Router} from '@angular/router';
import {ResourceService} from '../resource-service.service';
import {Region} from '../region';
import {RegionService} from '../region-service.service';
import {ResourceLanguageService} from '../resource-language-service.service';
import {ResourceLanguage} from '../resource-language';
import {forkJoin, Observable} from 'rxjs';
import {Languages} from '../languages';
import {Proficiency} from '../proficiency';
import {ActivatedResourceService} from '../activated-resource-service.service';
import {getLocaleDateFormat} from '@angular/common';
import {ActivatedResource} from '../activated-resource';

@Component({
  selector: 'app-resources-display',
  templateUrl: './resources-display.component.html',
  styleUrls: ['./resources-display.component.css']
})
export class ResourcesDisplayComponent implements OnInit {

  resources: Resource[];
  resourceLanguages: ResourceLanguage[];
  displayLanguages: string;
  regions: Region[];
  showAddComponent: boolean;
  showEditComponent: boolean;
  resourceToEdit: number;
  showCapabilitiesComponent: boolean;
  resourceCapabilitiesToDisplay: number;
  resourceToActivate: number;
  showActivationComponent;
  languages: Languages[];
  proficiencies: Proficiency[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private resourceService: ResourceService,
              private regionService: RegionService,
              private resourceLanguageService: ResourceLanguageService,
              private activatedResourceService: ActivatedResourceService) {
  }

  ngOnInit(){

    forkJoin([this.resourceService.findAll(), this.regionService.findAll(), this.resourceLanguageService.findAll()])
      .subscribe(([resources, regions, resourceLanguages]) => {
        this.resources = resources;
        this.regions = regions;
        this.resourceLanguages = resourceLanguages;
      });

    this.resourceLanguageService.getLanguages().subscribe(allLanguages => {
      this.languages = allLanguages;
    });

    this.resourceLanguageService.getProficiencies().subscribe(allProficiencies => {
      this.proficiencies = allProficiencies;
    });
  }

  getRegion(id: number) {
    for (const region of this.regions) {
      if (region.id === id) {
        return region.regionName;
      }
    }
  }

  indexOfLanguage(languageId: number){
    for (const languages of this.languages){
      if (languages.id === languageId){
        return this.languages.indexOf(languages);
      }
    }
  }

  indexOfProficiency(proficiencyId: number){
    for (const proficiency of this.proficiencies){
      if (proficiency.id === proficiencyId){
        return this.proficiencies.indexOf(proficiency);
      }
    }
  }

  getLanguageOfResource(id: number) {
    this.displayLanguages = '';

    for (const resourceLanguage of this.resourceLanguages){
      if (resourceLanguage.resourceId === id){
        this.displayLanguages += this.languages[this.indexOfLanguage(resourceLanguage.languageId)].language + ': ' +
          this.proficiencies[this.indexOfProficiency(resourceLanguage.proficiencyId)].proficiency + '\n';
       }
    }
    return this.displayLanguages;
  }

  disableResource(resource: Resource){
    this.activatedResourceService.disableResource(resource.regionId, resource.id).subscribe(result => this.reloadPage());
  }

  isActivated(resource: Resource){
    if (resource.activatedForScheduling === true){
      return true;
    }
  }

  isNotActivated(resource: Resource){
    if (resource.activatedForScheduling === false){
      return true;
    }
  }

  toggleAddComponent() {
    this.showAddComponent = !this.showAddComponent;
    this.showEditComponent = false;
    this.showCapabilitiesComponent = false;
  }

  toggleEditComponent(id: number) {
    this.showEditComponent = !this.showEditComponent;
    this.resourceToEdit = id;
    this.showAddComponent = false;
    this.showCapabilitiesComponent = false;
  }

  toggleCapabilitiesComponent(id: number){
    this.showCapabilitiesComponent = !this.showCapabilitiesComponent;
    this.resourceCapabilitiesToDisplay = id;
    this.showAddComponent = false;
    this.showEditComponent = false;
  }

  toggleActivationComponent(id: number){
    this.showActivationComponent = !this.showActivationComponent;
    this.resourceToActivate = id;
  }

  onDelete(id: number) {
    this.resourceService.deleteResource(id).subscribe();
    this.resourceLanguageService.deleteResourceLanguage(id).subscribe();
    this.reloadPage();
  }

  reloadPage() {
    window.location.reload();
  }

}
