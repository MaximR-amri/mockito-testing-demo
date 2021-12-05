package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class Test06Matches {

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
				LocalDate.of(2021, 7, 18), 2, true);
		BookingRequest bookingRequest2 = new BookingRequest("1", LocalDate.of(2021, 7, 8),
				LocalDate.of(2021, 7, 18), 2, true);
		when(paymentServiceMock.pay(any(), anyDouble()))
				.thenThrow(BusinessException.class);
		//when
		Executable executable = () -> bookingService.makeBooking(bookingRequest2);
		//then
		assertThrows(BusinessException.class, executable);

	}


}
