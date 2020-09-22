import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Resource} from '../resource';
import {ResourceService} from '../resource-service.service';
import {Region} from '../region';
import {RegionService} from '../region-service.service';
import {ResourceLanguageService} from '../resource-language-service.service';
import {Languages} from '../languages';
import {Proficiency} from '../proficiency';
import {ResourceLanguage} from '../resource-language';
import {FormArray, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-resources-form',
  templateUrl: './resources-form.component.html',
  styleUrls: ['./resources-form.component.css']
})
export class ResourcesFormComponent implements OnInit{

  resourceForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: ['', Validators.required],
    regionId: ['', Validators.required],
    resourceLanguages: this.fb.array([this.fb.group({
        languages: ['', Validators.required],
        proficiencies: ['', Validators.required]
      })])
});

  get resourceLanguages() {
    return this.resourceForm.get('resourceLanguages') as FormArray;
  }

  resource: Resource;
  regions: Region[];
  allLanguages: Languages[];
  allProficiencies: Proficiency[];
  id: number;
  addedResourceId: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private resourceService: ResourceService,
              private regionService: RegionService,
              private resourceLanguageService: ResourceLanguageService,
              private fb: FormBuilder) {
    this.resource = new Resource();
  }

  ngOnInit() {
    this.regionService.findAll().subscribe(allRegions => {
      this.regions = allRegions;
    });

    this.resourceLanguageService.getLanguages().subscribe(allLanguages => {
      this.allLanguages = allLanguages;
    });

    this.resourceLanguageService.getProficiencies().subscribe(allProficiencies => {
      this.allProficiencies = allProficiencies;
    });
  }

  addNewLanguage(){
    this.resourceLanguages.push(this.fb.group({
      languages: [''],
      proficiencies: ['']
    }));
  }

  onSubmit(){
    this.resourceService.addResource(this.resource).subscribe();

    for (let i = 0; i < this.resourceLanguages.length; i++) {
      const resLang: ResourceLanguage = new ResourceLanguage();
      resLang.resourceId = 1;
      resLang.languageId = this.resourceLanguages.at(i).value.languages;
      resLang.proficiencyId = this.resourceLanguages.at(i).value.proficiencies;
      this.resourceLanguageService.addResourceLanguage(resLang).subscribe();
    }
    this.reloadPage();
  }

  reloadPage(){
    window.location.reload();
  }
}
