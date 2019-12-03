package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MuzixRepositoryTest {

    @Autowired
    private MuzixRepository muzixRepository;

    private Image image;
    private Artist artist;
    private Track track;

    @Before
    public void setUp() {
        image = new Image(1, "http:url", "large");
        artist = new Artist(101, "Jonhn", "new url", image);
        track = new Track("Track1", "Mynewtrack", "new comments", "123", "new track url", artist);
    }

    @After
    public void tearDown() {
        image = null;
        artist = null;
        track = null;
        muzixRepository.deleteAll();
    }

    @Test
    public void testSaveTrack() {
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(fetchTrack.getTrackName(), track.getTrackName());
    }

    @Test
    public void testUpdateComments() {
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        fetchTrack.setComments("updating the comments");
        muzixRepository.save(fetchTrack);
        Track fetchTrackobj = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals("updating the comments", fetchTrackobj.getComments());
    }

    @Test
    public void testDeleteTrack() {
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        muzixRepository.delete(fetchTrack);
        Assert.assertEquals(Optional.empty(), muzixRepository.findById(track.getTrackId()));
    }

    @Test
    public void testGetAllTrack() {
        muzixRepository.insert(track);
        image = new Image(2, "http:url:another", "extralarge");
        artist = new Artist(102, "Jonhna", "new url", image);
        track = new Track("Track2", "Mynewanothertrack", "new updated comments", "123", "new track url", artist);
        muzixRepository.insert(track);
        List<Track> list = muzixRepository.findAll();

        Assert.assertEquals(2, list.size());
        Assert.assertEquals("Track2", list.get(1).getTrackId());
    }


}
