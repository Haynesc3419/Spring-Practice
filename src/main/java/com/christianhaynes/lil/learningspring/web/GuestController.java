package com.christianhaynes.lil.learningspring.web;

import com.christianhaynes.lil.learningspring.business.ReservationService;
import com.christianhaynes.lil.learningspring.business.RoomReservation;
import com.christianhaynes.lil.learningspring.data.Guest;
import com.christianhaynes.lil.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(DateUtils dateUtils, ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuest(String dateString, Model model) {
        List<Guest> guestList = this.reservationService.getHotelGuests();
        model.addAttribute("guestList", guestList);

        return "guestlist";
    }
}
