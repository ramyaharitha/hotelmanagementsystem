package com.capgemini.service;

import java.util.ArrayList;

import com.capgemini.bean.BookingDetailsBean;
import com.capgemini.bean.HotelBean;
import com.capgemini.bean.RoomDetailsBean;
import com.capgemini.bean.UsersBean;
import com.capgemini.exception.HotelBookingException;

public interface IHotelService 
{

	//Perform Hotel Management
	int addHotel(HotelBean hotelBean) throws HotelBookingException;
	int modifyHotel(HotelBean hotelBean) throws HotelBookingException;
	int deleteHotel(int deleteHotelId) throws HotelBookingException;
	ArrayList<HotelBean> viewHotel(int hotelId) throws HotelBookingException;

	//Perform Room Management
	int addRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException;
	int modifyRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException;
	int deleteRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException;
	RoomDetailsBean viewRoom(int roomId, int hotelId) throws HotelBookingException;



	//Generate Report
	ArrayList<HotelBean> viewAllHotels() throws HotelBookingException;

	ArrayList<BookingDetailsBean> viewSpecificHotelBooking(int hotelId) throws HotelBookingException;

	ArrayList<UsersBean> viewSpecificHotelGuestList(int hotelId) throws HotelBookingException;

	ArrayList<BookingDetailsBean> viewSpecificDateBooking(String date) throws HotelBookingException;



	//validation
	boolean isValidRating(String ratingstr);

	boolean isValidCost(String avgRatePerNightStr);

	boolean isValidDescription(String description);

	boolean isValidFax(String fax);

	boolean isValidEmail(String hotelEmail);

	boolean isValidMobileNo(String phoneNo2);

	boolean isValidCity(String city);

	boolean isValidAddress(String hotelAddress);

	boolean isValidName(String hotelName);

	boolean isValidId(String hotelIdstr);

	boolean isValidRoomNo(String roomNo);

	boolean isValidRoomType(String roomType);

	boolean isValidRoomSize(String roomSizestr);

	boolean isValidAvailability(String availability);

	int deleteHotelValidate(int deleteHotelId) throws HotelBookingException;

	int deleteRoomValidate(int hotelId, int roomId) throws HotelBookingException;







}
