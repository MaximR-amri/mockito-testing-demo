package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Test07VerifyBehaviour {

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
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2021, 7, 1),
				LocalDate.of(2021, 7, 5), 2, true);

		//when
		bookingService.makeBooking(bookingRequest);
		//then
//		verify(paymentServiceMock).pay(bookingRequest, 400); //gaat verifieren of die methode wordt aangeroepen.
//		verify(paymentServiceMock, times(1)).pay(bookingRequest, 400);
//		verify(paymentServiceMock, times(1)).pay(any(), anyDouble());
		verify(paymentServiceMock, times(1)).pay(any(), eq(400));

		verifyNoMoreInteractions(paymentServiceMock);
	}

	@Test
	void should_NotInvokePayment_When_Not_Prepaid() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2021, 7, 1),
				LocalDate.of(2021, 7, 5), 2, false);

		//when
		bookingService.makeBooking(bookingRequest);
		//then
		verify(paymentServiceMock, never()).pay(any(), anyDouble());
	}



}
