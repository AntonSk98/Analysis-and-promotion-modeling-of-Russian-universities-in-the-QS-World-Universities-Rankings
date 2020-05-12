import {Component, OnInit} from '@angular/core';
import {NotificationsService} from 'angular2-notifications';

@Component({
  selector: 'app-notification-utils',
  templateUrl: './notification-utils.component.html',
  styleUrls: ['./notification-utils.component.css']
})
export class NotificationUtilsComponent implements OnInit {

  constructor(
    private notificationService: NotificationsService
  ) {
  }

  ngOnInit(): void {
  }

  errorMessage() {
    this.notificationService.error(
      'Error occurred',
      'Something went wrong...'
    )
  }

  successMessage(title: string, message: string) {
    this.notificationService.success(
      title,
      message,
      {
        timeOut: 5000,
        pauseOnHover: false,
        showProgressBar: true,
        animate: "scale"
      });
  }

  clearAll() {
    this.notificationService.remove()
  }

}
