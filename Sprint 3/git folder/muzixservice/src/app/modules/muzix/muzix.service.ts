import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Track } from './track';
import { USER_NAME } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class MuzixService {


  thirdpartyApi: string;
  apiKey: string;
  springEndPoint: string;
  username: string;
  constructor(private httpClient: HttpClient) {

    this.thirdpartyApi =
      'http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=';
    this.apiKey = '&api_key=658ec1c4e49224f4fed00cb2b109386b&format=json';
    // this.springEndPoint = 'http://localhost:8083/api/v1/muzixservice/';
    this.springEndPoint = 'http://localhost:8085/api/v1/usertrackservice/';
  }

   getTrackDetails(country: string): Observable<any> {
    const url = `${this.thirdpartyApi}${country}${this.apiKey}`;

    return this.httpClient.get(url);
   }

   addTrackToWishList(track: Track){
    this.username = sessionStorage.getItem(USER_NAME);
    //  const url  = this.springEndPoint + 'track';
    const url  = this.springEndPoint + 'user/' + this.username + 'track';
    return this.httpClient.post(url, track, {
      observe: 'response'
    });
   }

   getAllTracksforWishList(): Observable<Track[]> {
    this.username = sessionStorage.getItem(USER_NAME);
    // const url  = this.springEndPoint + 'tracks';
    const url  = this.springEndPoint + 'user/' + this.username + 'tracks';
    return this.httpClient.get<Track[]>(url);
   }

   deleteTrackFromWishList(track: Track) {
     this.username = sessionStorage.getItem(USER_NAME);
    // const url  = this.springEndPoint + 'track/' + `${track.trackId}`;
     const url  = this.springEndPoint + 'user/' + this.username + '/track';
     const options = {
       headers: new HttpHeaders({
         'Content-Type' : 'application/json',
       }),
       body: track
     };
     console.log('In delete ', track);
    //  return this.httpClient.delete(url, { responseType: 'text'});
     return this.httpClient.delete(url, options);
  }

  updateComments(track) {
    this.username = sessionStorage.getItem(USER_NAME);
    // const id = track.trackId;
    // const com = track.comments;
    // const url = this.springEndPoint + 'track/' +`${id}`;
    const url = this.springEndPoint + 'user/' + this.username + '/track';
    return this.httpClient.patch(url, track, {observe: 'response'});
  }

  filterArtists(tracks: Array<Track>, artistName: string) {
    const results = tracks.filter(track => {
      return track.artist.name.match(artistName);
    });
    console.log('Filtered data', results);
    return results;
  }
}
