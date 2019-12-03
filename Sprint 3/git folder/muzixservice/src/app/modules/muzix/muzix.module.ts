
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { CardComponent } from './components/card/card.component';
import { AngularmaterialModule } from '../angularmaterial/angularmaterial.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';

import { AppRoutingModule } from '../../app-routing.module';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { FooterComponent } from './components/footer/footer.component';

import { DialogComponent } from './components/dialog/dialog.component';
import { MuzixService } from './muzix.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './interceptor.service';
import { FormsModule } from '@angular/forms';
import { from } from 'rxjs';

@NgModule({
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AngularmaterialModule,
    FormsModule
  ],
  declarations: [
    CardContainerComponent,
    CardComponent,
    HeaderComponent,
    WishlistComponent,
    FooterComponent,
    DialogComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
   exports: [
    CardContainerComponent,
    HeaderComponent,
    AppRoutingModule,
    FooterComponent
   ],
   providers: [
     MuzixService,
     {
       provide: HTTP_INTERCEPTORS,
       useClass: InterceptorService,
       multi: true
     }
   ],
   entryComponents: [
    DialogComponent
  ]
})
export class MuzixModule { }
