package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class Test10ArgumentCaptors {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	private ArgumentCaptor<Double> doubleCaptor;
//	private ArgumentCaptor<BookingRequest> bookingRequestArgumentCaptor;


	@BeforeEach
	void setup(){
		paymentServiceMock = mock(PaymentService.class);
		roomServiceMock = mock(RoomService.class);
		bookingDAOMock = mock(BookingDAO.class);
		mailSenderMock = mock(MailSender.class);
		bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

		this.doubleCaptor = ArgumentCaptor.forClass(Double.class);
	}
	@Test
	void should_CalculateCorrectPrice_When_CorrectInput() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2021, 7, 1),
				LocalDate.of(2021, 7, 5), 2, true);

		//when
		bookingService.makeBooking(bookingRequest);
		//then

		verify(paymentServiceMock, times(1)).pay(any(), doubleCaptor.capture());
		double captureArgument = doubleCaptor.getValue();
		verifyNoMoreInteractions(paymentServiceMock);
	}
}
