package com.capgemini.service;



import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.bean.BookingDetailsBean;
import com.capgemini.bean.HotelBean;
import com.capgemini.bean.RoomDetailsBean;
import com.capgemini.bean.UsersBean;
import com.capgemini.dao.HotelDAO;
import com.capgemini.dao.IHotelDAO;
import com.capgemini.exception.HotelBookingException;

public class HotelService implements IHotelService {
	IHotelDAO dao = new HotelDAO();
	@Override
	public boolean isValidRating(String ratingstr) {
		Pattern pattern = Pattern.compile("[0-5]{1}|[0-5]{1}.[0-9]{1}");
		Matcher matcher = pattern.matcher(ratingstr);

		return matcher.matches();
	}

	@Override
	public boolean isValidCost(String avgRatePerNightStr) {
		Pattern pattern = Pattern.compile("[0-9]{0,6}");
		Matcher matcher = pattern.matcher(avgRatePerNightStr);
		return matcher.matches();
	}

	@Override
	public boolean isValidDescription(String description) {
		Pattern pattern = Pattern.compile("[0-9A-Za-z, ]{2,50}");
		Matcher matcher = pattern.matcher(description);
		return matcher.matches();
	}

	@Override
	public boolean isValidFax(String fax) {
		Pattern pattern = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{7}");
		Matcher matcher = pattern.matcher(fax);

		return matcher.matches();
	}

	@Override
	public boolean isValidEmail(String hotelEmail) {
		Pattern pattern = Pattern
				.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
		Matcher matcher = pattern.matcher(hotelEmail);

		return matcher.matches();

	}

	@Override
	public boolean isValidMobileNo(String phoneNo2) {
		Pattern pattern = Pattern.compile("^[7-9]{1}[0-9]{9}");
		Matcher matcher = pattern.matcher(phoneNo2);

		return matcher.matches();
	}

	@Override
	public boolean isValidCity(String city) {
		Pattern pattern = Pattern.compile("[A-Za-z]{2,10}");
		Matcher matcher = pattern.matcher(city);
		return matcher.matches();
	}

	@Override
	public boolean isValidAddress(String hotelAddress) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9#, ]{5,100}");
		Matcher matcher = pattern.matcher(hotelAddress);

		return matcher.matches();
	}

	@Override
	public boolean isValidName(String hotelName) {
		Pattern pattern = Pattern.compile("[A-Za-z ]{2,}");
		Matcher matcher = pattern.matcher(hotelName);

		return matcher.matches();
	}
	/*delete validation*/
	@Override
	public int deleteHotelValidate(int deleteHotelId) throws HotelBookingException {

		return dao.deleteHotelValidate(deleteHotelId);
	}


	/*****************************************************************************************************
	 * 
	 * Hotel Methods
	 * @throws HotelBookingException 
	 * 
	 *****************************************************************************************************/

	@Override
	public int addHotel(HotelBean hotelBean) throws HotelBookingException {



		return dao.addHotel(hotelBean);
	}

	@Override
	public ArrayList<HotelBean> viewHotel(int hotelId) throws HotelBookingException {
		return dao.viewHotel(hotelId);
	}

	@Override
	public int modifyHotel(HotelBean hotelBean) throws HotelBookingException {

		return dao.modifyHotel(hotelBean);
	}



	@Override
	public int deleteHotel(int deleteHotelId) throws HotelBookingException {

		return dao.deleteHotel(deleteHotelId);
	}

	@Override
	public boolean isValidId(String hotelIdstr) {
		Pattern pattern = Pattern.compile("[0-9]{1,4}");
		Matcher matcher = pattern.matcher(hotelIdstr);

		return matcher.matches();
	}

	@Override
	public boolean isValidRoomNo(String roomNo) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9]{1,4}");
		Matcher matcher = pattern.matcher(roomNo);

		return matcher.matches();
	}

	@Override
	public boolean isValidRoomType(String roomType) {
		Pattern pattern = Pattern.compile("[A-Za-z ]{2,20}");
		Matcher matcher = pattern.matcher(roomType);
		return matcher.matches();
	}

	@Override
	public boolean isValidRoomSize(String roomSizestr) {
		Pattern pattern = Pattern.compile("[0-9]{1}");
		Matcher matcher = pattern.matcher(roomSizestr);
		return matcher.matches();
	}

	@Override
	public boolean isValidAvailability(String availability) {
		Pattern pattern = Pattern.compile("true|false");
		Matcher matcher = pattern.matcher(availability);
		return matcher.matches();
	}

	@Override
	public RoomDetailsBean viewRoom(int roomId, int hotelId) throws HotelBookingException {
		return dao.viewRoom(roomId,hotelId);
	}
	/*******************************************************************************************************************
	 * 
	 * Room methods
	 * @throws HotelBookingException 
	 * 
	 ********************************************************************************************************************/
	@Override
	public int modifyRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException {
		
		return dao.modifyRoom(roomDetailsBean);
	}

	@Override
	public int deleteRoomValidate(int hotelId, int roomId) throws HotelBookingException {
		
		return dao.deleteRoomValidate(hotelId,roomId);
	}

	@Override
	public int deleteRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException {
		
		return dao.deleteRoom(roomDetailsBean);
	}

	@Override
	public int addRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException {
		

		return dao.addRoom(roomDetailsBean);
	}

	@Override
	public ArrayList<HotelBean> viewAllHotels() throws HotelBookingException {
		dao = new HotelDAO();
		return dao.viewAllHotels();
	}

	@Override
	public ArrayList<BookingDetailsBean> viewSpecificHotelBooking(int hotelId) throws HotelBookingException {
		dao = new HotelDAO();
		return dao.viewSpecificHotelBooking(hotelId);
	}

	@Override
	public ArrayList<UsersBean> viewSpecificHotelGuestList(int hotelId) throws HotelBookingException {
		dao = new HotelDAO();
		return dao.viewSpecificHotelGuestList(hotelId);
	}

	@Override
	public ArrayList<BookingDetailsBean> viewSpecificDateBooking(String date) throws HotelBookingException {
		dao = new HotelDAO();
		return dao.viewSpecificDateBooking(date);
	}

}
