import { Component, OnInit } from '@angular/core';
import { HijosService } from '../hijos.service'
import { Hijo } from 'hijos';

@Component({
  selector: 'app-hijos',
  templateUrl: './hijos.component.html',
  styleUrls: ['./hijos.component.scss']
})
export class HijosComponent implements OnInit {
  hijos: Hijo[];

  constructor(private HijosService: HijosService) { }
  
  ngOnInit() {
    this.HijosService.findAll().subscribe(data => {
      this.hijos = data;
    });
  }

}
