import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, private authService: AuthenticationService) { }

  logout() {
    this.authService.logout();
    console.log( 'I am called' );
    this.router.navigate(['/login']);
  }
  ngOnInit() {
  }

}
