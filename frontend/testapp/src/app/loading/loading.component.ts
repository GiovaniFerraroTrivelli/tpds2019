import { Component, OnInit } from '@angular/core';
import { LoadingService } from './loading.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnInit {

  constructor(private loadingService: LoadingService) {}

  isHidden() {
  	return this.loadingService.isHidden();
  }

  ngOnInit() {
  }

}
