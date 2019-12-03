package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
<<<<<<< HEAD
@RequestMapping("api/v1/muzixservice/")
public class MuzixController {

    private ResponseEntity responseEntity;
    private MuzixService muzixservice;

    @Autowired
    public MuzixController(MuzixService muzixservice) {
        this.muzixservice = muzixservice;
    }


    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping("track")
    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<?> saveTrackToWishList(@RequestBody Track track) throws TrackAlreadyExistsException {

        try{
            muzixservice.SaveTrackToWishList(track);
            responseEntity = new ResponseEntity(track, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            throw new TrackAlreadyExistsException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error !!! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackToWishList(@PathVariable("id") String id) throws TrackNotFoundException {
        try{
            muzixservice.deleteTrackFromWishList(id);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        }catch (TrackNotFoundException e) {
            throw new TrackNotFoundException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error !!! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateCommentForTrack(@RequestBody Track track, @PathVariable("id") String id ) throws TrackNotFoundException {
        try{
            Track updatedTrack = muzixservice.updateCommentForTrack(track.getComments(), id);
            responseEntity = new ResponseEntity(track, HttpStatus.OK);
        }catch (TrackNotFoundException e) {
            throw new TrackNotFoundException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error !!! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTrackFromWishLisst(){
        try{
            responseEntity = new ResponseEntity(muzixservice.getAllTrackFromWishList(), HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity("Error !!! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
