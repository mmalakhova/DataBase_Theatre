package ru.nsu.fit.bdcourse.theatredemo.service;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.fit.bdcourse.theatredemo.model.ConcertTour;
import ru.nsu.fit.bdcourse.theatredemo.model.Musician;
import ru.nsu.fit.bdcourse.theatredemo.repository.ConcertTourRepository;
import ru.nsu.fit.bdcourse.theatredemo.repository.MusicianRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class ConcertTourService {
    private final ConcertTourRepository concertTourRepository;

    public ConcertTourService(ConcertTourRepository concertTourRepository) {
        this.concertTourRepository = concertTourRepository;
    }

    public Page<ConcertTour> getAllConcertTours(Integer pageNo, Integer pageSize, String sortBy,
                                             String title, String descr, String startDate, String endDate) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        ExampleMatcher matcher = ExampleMatcher
//                .matchingAll()
//                .withMatcher("title", contains().ignoreCase())
//                .withMatcher("descr", contains().ignoreCase())
//                .withMatcher("startDate", contains().ignoreCase())
//                .withMatcher("endDate", contains().ignoreCase());
//        ConcertTour concertTourToFind = ConcertTour
//                .builder()
//                .title(title)
//                .description(descr)
////                .startDate(startDate.equals("") ? null : LocalDate.parse(startDate))
////                .endDate(endDate.equals("") ? null : LocalDate.parse(endDate))
//                .build();
        return concertTourRepository.findAll(/*Example.of(concertTourToFind, matcher),*/ paging);
    }

    public ResponseEntity<ConcertTour> getConcertTourById(Long id) {
        Optional<ConcertTour> concertTourData = concertTourRepository.findById(id);
        return concertTourData.map(t ->
                new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ConcertTour> createNewConcertTour(ConcertTour concertTour) {
        return new ResponseEntity<>(concertTourRepository.save(concertTour), HttpStatus.CREATED);
    }

    public ResponseEntity<ConcertTour> updateConcertTour(ConcertTour concertTour) {
        Optional<ConcertTour> concertTourData = concertTourRepository.findById(concertTour.getId());
        if (concertTourData.isPresent()) {
            ConcertTour updateConcertTour = concertTourData.get();
            if (concertTour.getTitle() != null) {
                updateConcertTour.setTitle(concertTour.getTitle());
            }
            if (concertTour.getDescription() != null) {
                updateConcertTour.setDescription(concertTour.getDescription());
            }
            if (concertTour.getStartDate() != null) {
                updateConcertTour.setStartDate(concertTour.getStartDate());
            }
            if (concertTour.getEndDate() != null) {
                updateConcertTour.setEndDate(concertTour.getEndDate());
            }
            return new ResponseEntity<>(concertTourRepository.save(updateConcertTour), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteConcertTourById(Long id) {
        Optional<ConcertTour> concertTourToDelete = concertTourRepository.findById(id);
        if (concertTourToDelete.isPresent()) {
            concertTourRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
