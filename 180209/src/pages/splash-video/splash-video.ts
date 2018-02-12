import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

import { HomePage } from '../home/home';

/**
 * Generated class for the SplashVideoPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-splash-video',
  templateUrl: 'splash-video.html',
})
export class SplashVideoPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    setTimeout(()=>{
      this.navCtrl.push(HomePage);
    }, 4000);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad SplashVideoPage');
  }

}
