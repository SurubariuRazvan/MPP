package com.jderu;

import com.jderu.repository.JPARepository.DestinationJPARepository;
import com.jderu.repository.JPARepository.TripJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {
    private final TripJPARepository tripRepo;
    private final DestinationJPARepository destinationRepo;

    @Autowired
    public WebController(TripJPARepository tripRepo, DestinationJPARepository destinationRepo) {
        this.tripRepo = tripRepo;
        this.destinationRepo = destinationRepo;
    }


    @GetMapping(value = "/trip")
    public Iterable<TripDTO> findAllTrip() {
        List<TripDTO> tripDTOList = new ArrayList<>();
        tripRepo.findAll().forEach(trip -> tripDTOList.add(
                new TripDTO(trip.getId(),
                        destinationRepo.findById(trip.getDestinationID()).orElse(new Destination(0, "null")).getName(),
                        trip.getDeparture(),
                        trip.getFreeSeats())
        ));
        return tripDTOList;
    }

    @PostMapping("/trip")
    public String saveTrip(@RequestBody TripDTO tripDTO) {
        System.out.println("here");
        tripDTO.setId(0);
        try {
            Destination destination = destinationRepo.findByName(tripDTO.getDestinationName());
            if (destination == null) {
                destinationRepo.save(new Destination(0, tripDTO.getDestinationName()));
                destination = destinationRepo.findByName(tripDTO.getDestinationName());
            }

            tripRepo.save(new Trip(tripDTO.getId(), destination.getId(), tripDTO.getDeparture(), tripDTO.getFreeSeats()));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
        return "Trip saved";
    }

    @PutMapping("/trip/{id}")
    public String updateTrip(@RequestBody TripDTO tripDTO, @PathVariable Integer id) {
        tripDTO.setId(id);
        try {
            Destination destination = destinationRepo.findByName(tripDTO.getDestinationName());
            if (destination == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination doesnt exist");
            tripRepo.save(new Trip(tripDTO.getId(), destination.getId(), tripDTO.getDeparture(), tripDTO.getFreeSeats()));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
        return "Trip updated";
    }

    @DeleteMapping("/trip/{id}")
    public String deleteTrip(@PathVariable Integer id) {
        try {
            tripRepo.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
        return "Trip deleted";
    }

    @GetMapping("/trip/{id}")
    public TripDTO findByIDTrip(@PathVariable Integer id) {
        Trip trip = tripRepo.findById(id).orElse(null);
        if (trip == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip with id:" + id + " not found.");
        return new TripDTO(trip.getId(),
                destinationRepo.findById(trip.getDestinationID()).orElse(new Destination(0, "null")).getName(),
                trip.getDeparture(),
                trip.getFreeSeats());
    }

    @GetMapping("/destination/all")
    public Iterable<Destination> findAllDestination() {
        return destinationRepo.findAll();
    }

    @GetMapping("/destination/{id}")
    public Destination findByIDDestination(@PathVariable Integer id) {
        Destination destination = destinationRepo.findById(id).orElse(null);
        if (destination == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination with id " + id + " not found.");
        return destination;
    }
}
