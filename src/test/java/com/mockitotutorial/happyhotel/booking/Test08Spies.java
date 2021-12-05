package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class Test08Spies {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	@BeforeEach
	void setup(){
		paymentServiceMock = mock(PaymentService.class);
		roomServiceMock = mock(RoomService.class);
		bookingDAOMock = spy(BookingDAO.class); //verschil tussen Mock en Spy; Door daar een spy van te maken (nog steeds mock) maar ipv leeg te maken, is de functionaliteit overgenomen
		mailSenderMock = mock(MailSender.class);
		bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}
	@Test
	void should_CancelBooking_When_CorrectInput() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2021, 7, 1),
				LocalDate.of(2021, 7, 5), 2, true);

		bookingRequest.setRoomId("1.3");
		String bookingId = "1";

		doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);
		//when
		bookingService.cancelBooking(bookingId);
		//then
		verify(bookingDAOMock).delete(bookingId);
	}

}
