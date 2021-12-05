package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test05ThrowingExceptions {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	@BeforeEach
	void setup(){
		paymentServiceMock = mock(PaymentService.class);
		roomServiceMock = mock(RoomService.class);
		bookingDAOMock = mock(BookingDAO.class);
		mailSenderMock = mock(MailSender.class);
		bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}

	@Test
	void should_CountAvailablePlaces_When_OneRoomAvailable() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2021, 7, 8),
				LocalDate.of(2021, 7, 18), 2, false);
		BookingRequest bookingRequest2 = new BookingRequest("1", LocalDate.of(2021, 7, 8),
				LocalDate.of(2021, 7, 18), 2, true);
		when(roomServiceMock.findAvailableRoomId(bookingRequest))
				.thenThrow(BusinessException.class);
		//when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);
		//then
		assertThrows(BusinessException.class, executable);

	}


}
