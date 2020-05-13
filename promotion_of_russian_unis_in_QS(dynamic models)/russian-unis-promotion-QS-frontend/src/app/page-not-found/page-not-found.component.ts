import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.css']
})
export class PageNotFoundComponent implements OnInit {
  time = 3;

  constructor(
    private _location: Location
  ) { }

  ngOnInit(): void {

    setInterval(()=> {
      this.time --;
    }, 1000);

    setTimeout(()=> {
      this._location.go('/main-page');
      location.reload()
    },3300)

  }

}
