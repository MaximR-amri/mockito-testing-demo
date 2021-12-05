package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Test01FirstMocks {

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
	void should_CalculateCorrectPrice_When_CorrectInput() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2021, 7, 8),
				LocalDate.of(2021, 7, 18), 2, false);
		double expected = 10 * 2 * 50;

		//when
		double actual = bookingService.calculatePrice(bookingRequest);
		//then
		assertEquals(expected, actual);
	}

}
