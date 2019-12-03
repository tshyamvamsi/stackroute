import { Component, OnInit } from '@angular/core';
import { MuzixService } from '../../muzix.service';
import { Track } from '../../track';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  tracks: Array<Track>;
  wishData = true;
  statuscode: number;

  constructor(
    private muzixService: MuzixService,
    private snackbar: MatSnackBar
    ) {}

  ngOnInit() {
    const message = 'WishList is empty';
    this.muzixService.getAllTracksforWishList().subscribe( data => {
      this.tracks = data;
      if(data.length === 0) {
        this.snackbar.open(message, ' ', {
          duration: 1000
        });
      }
    });
  }

  deleteFromWishList(track){
    this.muzixService.deleteTrackFromWishList(track).subscribe(data => {
      console.log('deleted -->', data);
      const index = this.tracks.indexOf(track);
      this.tracks.splice(index, 1);
      this.snackbar.open('Successfully deleted', ' ', {
        duration: 1000
      });
    });
    return this.tracks;
  }

  updateComments(track) {
    this.muzixService.updateComments(track).subscribe(
      data => {
      this.snackbar.open('Successfully update', '', {
        duration: 1000
      });
    },
    error => {
      console.log('error', error);
    });
  }

}
