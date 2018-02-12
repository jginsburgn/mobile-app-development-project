import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { OmnibarPage } from '../omnibar/omnibar';
import { CounterPage } from '../counter/counter';
import { CalculatorPage } from '../calculator/calculator';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  private omnibar :any;
  private counter :any;
  private calculator: any;

  constructor(public navCtrl: NavController) {
    this.omnibar = OmnibarPage;
    this.counter = CounterPage;
    this.calculator = CalculatorPage;
  }

}
