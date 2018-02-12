import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { SplashVideoPage } from '../pages/splash-video/splash-video';
import { OmnibarPage } from '../pages/omnibar/omnibar';
import { CalculatorPage } from '../pages/calculator/calculator';
import { CounterPage } from '../pages/counter/counter';
import { WebviewPage } from '../pages/webview/webview';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    SplashVideoPage,
    OmnibarPage,
    CalculatorPage,
    CounterPage,
    WebviewPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    SplashVideoPage,
    OmnibarPage,
    CalculatorPage,
    CounterPage,
    WebviewPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
