import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Track } from '../../track';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  track: Track;

  @Input()
  wishData: boolean;

  @Output()
  addToWishList = new EventEmitter();

  @Output()
  deleteFromWishList = new EventEmitter();

  @Output()
  updateComments = new EventEmitter();


  constructor(private dialog: MatDialog) { }

  ngOnInit() {
    console.log('wishdata -->', this.wishData);
  }

  addButtonClick(track){
    console.log('card component -->', track);
    this.addToWishList.emit(track);
  }

  deleteButtonClick(track){
    this.deleteFromWishList.emit(track);
  }

  addComments(){
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data: {comments: this.track.comments}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('result is --->: ', result);
      this.track.comments = result;
      this.updateComments.emit(this.track);
    });
  }
}
