import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ToastController } from 'ionic-angular';

/**
 * Generated class for the CounterPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-counter',
  templateUrl: 'counter.html',
})
export class CounterPage {

  private n: number = 0;

  constructor(public navCtrl: NavController, public navParams: NavParams, private toastCtrl: ToastController) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CounterPage');
  }

  count() {
    this.n++;
    this.toastCtrl.create({
      position: 'bottom',
      message: `${this.n}`
    }).present();
  }

}
