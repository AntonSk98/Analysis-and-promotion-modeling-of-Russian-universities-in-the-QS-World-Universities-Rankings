import { Component, OnInit } from '@angular/core';
import {faUniversity} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  rightArrow = faUniversity;
  universities = [];
  isLoading = true;

  constructor() { }

  ngOnInit(): void {
    setTimeout(() => {
      this.isLoading = false;
      this.universities = [
        {
          id: 1,
          year2020: '84',
          year2019: '90',
          university: 'Lomonosov Moscow State University',
        },
        {
          id: 2,
          year2020: '231',
          year2019: '244',
          university: 'Novosibirsk State University',
        },
        {
          id: 3,
          year2020: '234',
          year2019: '235',
          university: 'Saint-Petersburg State University',
        },
        {
          id: 4,
          year2020: '268',
          year2019: '267',
          university: 'Tomsk State University',
        },
        {
          id: 5,
          year2020: '284',
          year2019: '289',
          university: 'Bauman Moscow State Technical University',
        },
        {
          id: 6,
          year2020: '302',
          year2019: '312',
          university: 'Moscow Institute of Physics and Technology State University',
        },
        {
          id: 7,
          year2020: '322',
          year2019: '343',
          university: 'National Research University - Higher School of Economics (HSE)',
        },
        {
          id: 8,
          year2020: '329',
          year2019: '329',
          university: 'National Research Nuclear University "MEPhI" (Moscow Engineering Physics Institute)',
        },
        {
          id: 9,
          year2020: '364',
          year2019: '412',
          university: 'Ural Federal University',
        },
        {
          id: 10,
          year2020: '366',
          year2019: '355',
          university: 'Moscow State Institute of International Relations – MGIMO University',
        }
      ];
    }, 3000);
  }

}
