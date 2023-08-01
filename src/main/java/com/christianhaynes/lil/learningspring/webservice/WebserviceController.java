package com.christianhaynes.lil.learningspring.webservice;


import com.christianhaynes.lil.learningspring.business.ReservationService;
import com.christianhaynes.lil.learningspring.business.RoomReservation;
import com.christianhaynes.lil.learningspring.data.Guest;
import com.christianhaynes.lil.learningspring.data.Room;
import com.christianhaynes.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtil;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtil = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date", required = false) String dateString) {
        Date date = dateUtil.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path="/guests", method = RequestMethod.GET)
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @RequestMapping(path="/guests", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @RequestMapping(path="/rooms", method = RequestMethod.GET)
    public List<Room> getRooms() {
        return this.reservationService.getRooms();
    }

}
