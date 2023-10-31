import { Component, HostListener } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faClock } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/services/AuthService';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  isCaptureLeadRoute: boolean = false;
  faClock = faClock

  constructor(private route: ActivatedRoute, public authservice: AuthService) {
    this.route.url.subscribe((segments) => {
      this.isCaptureLeadRoute = segments.some(segment => segment.path === 'capturelead');
    });
  }

  isNavbarHidden = false;
  prevScrollPos = window.pageYOffset;

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {
    const currentScrollPos = window.pageYOffset;
    if (currentScrollPos > this.prevScrollPos) {
      // Scrolling down
      this.isNavbarHidden = true;
    } else {
      // Scrolling up
      this.isNavbarHidden = false;
    }
    this.prevScrollPos = currentScrollPos;
  }

  logout() {
    this.authservice.logout();
  }
}
