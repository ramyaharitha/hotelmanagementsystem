package com.capgemini.dao;

import java.util.ArrayList;

import com.capgemini.bean.BookingDetailsBean;
import com.capgemini.bean.HotelBean;
import com.capgemini.bean.RoomDetailsBean;
import com.capgemini.bean.UsersBean;
import com.capgemini.exception.HotelBookingException;

public interface IHotelDAO {

	int addHotel(HotelBean hotelBean) throws HotelBookingException;

	ArrayList<HotelBean> viewHotel(int hotelId) throws HotelBookingException;

	int modifyHotel(HotelBean hotelBean) throws HotelBookingException;

	int deleteHotel(int deleteHotelId) throws HotelBookingException;

	int deleteHotelValidate(int deleteHotelId) throws HotelBookingException;

	int modifyRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException;

	int deleteRoomValidate(int hotelId, int roomId) throws HotelBookingException;

	int deleteRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException;

	int addRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException;

	RoomDetailsBean viewRoom(int roomId, int hotelId) throws HotelBookingException;

	ArrayList<HotelBean> viewAllHotels() throws HotelBookingException;

	ArrayList<BookingDetailsBean> viewSpecificHotelBooking(int hotelId) throws HotelBookingException;

	ArrayList<UsersBean> viewSpecificHotelGuestList(int hotelId) throws HotelBookingException;

	ArrayList<BookingDetailsBean> viewSpecificDateBooking(String date) throws HotelBookingException;

}
