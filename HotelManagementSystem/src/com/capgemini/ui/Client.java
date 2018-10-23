package com.capgemini.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.capgemini.bean.BookingDetailsBean;
import com.capgemini.bean.HotelBean;
import com.capgemini.bean.RoomDetailsBean;
import com.capgemini.bean.UsersBean;
import com.capgemini.dbutil.IMsgMapper;
import com.capgemini.exception.HotelBookingException;
import com.capgemini.service.HotelService;
import com.capgemini.service.IHotelService;


public class Client {
    static Scanner scanner = new Scanner(System.in);
	IHotelService service= new HotelService();         // Service Object creation
	static RoomDetailsBean room = new RoomDetailsBean();  // Room Details Bean  Object creation 
	static char ch;
	public static void main(String[] args) throws HotelBookingException 
	{


		System.out.println("---------Hotel Booking Management System------------- ");
		
		System.out.println("*******Welcome Admin*******");
		/**************************************************************************************
		 *                            Admin  Login                                   *
		 **************************************************************************************/
		
			System.out.println("Enter Admin Id: ");
			String	adminId=scanner.next();
			System.out.println("Enter Admin Password: ");
			String	password=scanner.next();
			System.out.println();
			if(adminId.equals("admin")&password.equals("admin"))
			{
				System.out.println("Admin Login Successfull!!!!");
				
				/*********************************************************************************
				 *                        Admin Functions                               *
				 *********************************************************************************/
				
				System.out.println("*********************************************");
				System.out.println("\tMENU");
				System.out.println("*********************************************");
				System.out.println("1.Perform Hotel Management ");
				System.out.println("2.Perform Room Management ");
				System.out.println("3.Generate various Reports ");
				System.out.println("4.Exit ");
				System.out.println("*********************************************");
				System.out.println();
				System.out.println();
				System.out.println("Choose Option");
				int option1 = scanner.nextInt();
				switch (option1) {
				case 1:
					/************************************************************************************
					 * Hotel Management
					 *************************************************************************************/
					System.out.println();
					System.out.println("*********************************************");
					System.out.println("1.Add Hotel");
					System.out.println("2.Update Hotel");
					System.out.println("3.Delete Hotel");
					System.out.println("*********************************************");
					System.out.println();
					System.out.println("Choose Option");
					int option2 = scanner.nextInt();
					switch (option2) 
					{
					/************************************************************************************
					 *                                 ADD HOTEL                                          *
					 **************************************************************************************/
					case 1:
						new Client().addHotel();

						break;
						/***********************************************************************************
						 *                             UPDATE HOTEL                                        *
						 ***********************************************************************************/

					case 2:
						new Client().updateHotel();
						break;
						/***********************************************************************************
						 *                             DELETE HOTEL                                         *
						 *************************************************************************************/
					case 3:
						new Client().deleteHotel(); // invokes delete method
						break;

					default:
						System.err.println("Enter valid option");
						break;
					}

					break;
					/*****************************************************************************
					 *                             ROOM MANAGEMENT                                *
					 ******************************************************************************/
				case 2:
					System.out.println();
					System.out.println("*********************************************");
					System.out.println("1.ADD ROOM");
					System.out.println("2.UPDATE ROOM");
					System.out.println("3.DELETE ROOM");
					System.out.println("*********************************************");
					System.out.println();
					System.out.println("choose option");
					option2 = scanner.nextInt();

					switch (option2) {
					/**************************************************************************
					 *                                ADD ROOM                                 *
					 ***************************************************************************/
					case 1:
						new Client().addRoom();
						break;

						/*********************************************************************************
						 *                             UPDATE ROOM                                       *
						 *********************************************************************************/
					case 2:
						new Client().updateRoom();
						break;

						/********************************************************************************
						 *                             DELETE ROOM                                      *
						 ********************************************************************************/
					case 3:
						new Client().deleteRoom();
						break;

					default:
						System.out.println("Enter valid option");
						break;
					}

					break;

					/*************************************************************************************
					 *                                GENERATE REPORTS                                   *                                                           
					 *************************************************************************************/
				case 3:
					System.out.println();
					System.out.println("*********************************************");
					System.out.println("1.View List of Hotels");
					System.out.println("2.View Bookings of specific hotel");
					System.out.println("3.View guest list of specific hotel");
					System.out.println("4. View bookings for specified date");
					System.out.println("*********************************************");
					System.out.println();
					System.out.println("Choose Option");
					int generateOption = scanner.nextInt();
					switch (generateOption) {
					/*************************************************************************************
					 *                              VIEW LIST OF ALL HOTELS                              *
					 *************************************************************************************/
					case 1:

						new Client().allHotelsList();

						break;
						/*************************************************************************************
						 *                          VIEW BOOKINGS OF SPECIFIC HOTEL                          *
						 *************************************************************************************/
					case 2:
						new Client().viewSpecificHotelBooking();
						break;

						/*************************************************************************************
						 *                          VIEW GUEST LIST OF SPECIFIC HOTEL                         *
						 *************************************************************************************/
					case 3:
						new Client()
						.viewSpecificHotelGuestList();
						break;
						/*************************************************************************************
						 *                           VIEW BOOKINGS FOR SPECIFIED DATE                         *
						 *************************************************************************************/
					case 4:
						new Client().viewSpecificDateBooking();
						break;

					default:
						break;
					}
					break;
				case 4:
					System.exit(0);
					break;
				default:
					System.err.println("Enter valid option");
					break;
				}
				System.out.println("Do you want to continue? Yes or No");
				ch = scanner.next().charAt(0);

				while (ch == 'y' || ch == 'Y');
			}
			else {
				System.out.println("Not a Valid Admin");
				System.out.println("Do you wish to continue?");
				System.out.println("Y/N");

			}
			

	}	
	/********************************************************PERFORM HOTEL MANAGEMENT*********************************************************/
	/******************************************************************************************************************************************
	 *                                                          ADD HOTEL METHOD                                                              *
	 *****************************************************************************************************************************************/
	private void addHotel() throws HotelBookingException 
	{

		System.out.println("Enter Hotel Name");
		String hotelName = scanner.next();
		while (!service.isValidName(hotelName))                 //VALIDATION OF HOTEL NAME
		{
			System.err.println(IMsgMapper.NAME_ERROR);
			hotelName = scanner.next();
		}
		System.out.println("Enter Hotel Address : ");
		String hotelAddress = scanner.next();
		while (!service.isValidAddress(hotelAddress))            //VALIDATION OF HOTEL ADDRESS
		{
			System.err.println(IMsgMapper.ADDRESS_ERROR);
			hotelAddress = scanner.next();
		}
		System.out.println("Enter City : "); 
		String city = scanner.next();
		while (!service.isValidCity(city))                        //VALIDATION OF HOTEL CITY
		{ 
			System.err.println(IMsgMapper.CITY_ERROR);
			city = scanner.next();
		}
		System.out.println("Enter Phone No1 : ");
		String phoneNo1 = scanner.next();
		while (!service.isValidMobileNo(phoneNo1))               //VALIDATION OF HOTEL PHONE NUMBER -1
		{
			System.err.println(IMsgMapper.MOBILE_NO_ERROR);
			phoneNo1 = scanner.next();
		}
		System.out.println("Enter Phone No2 : ");
		String phoneNo2 = scanner.next();
		while (!service.isValidMobileNo(phoneNo2))               //VALIDATION OF HOTEL PHONE NUMBER -2
		{
			System.err.println(IMsgMapper.MOBILE_NO_ERROR);
			phoneNo2 = scanner.next();
		}
		System.out.println("Enter Email : ");
		String hotelEmail = scanner.next();
		while (!service.isValidEmail(hotelEmail))                //VALIDATION OF HOTEL EMAIL ID 
		{

			System.err.println(IMsgMapper.EMAIL_ERROR);
			hotelEmail = scanner.next();
		}
		System.out.println("Enter fax : ");
		String fax = scanner.next();
		while (!service.isValidFax(fax))                         //VALIDATION OF HOTEL FAX NUMBER
		{
			System.err.println(IMsgMapper.FAX_ERROR);
			fax = scanner.next();
		} 
		System.out.println("Enter Description : ");
		String description = scanner.next();
		while (!service.isValidDescription(description))         //VALIDATION OF HOTEL DESCRIPTION
		{

			System.err.println(IMsgMapper.DESCRIPTION_ERROR);
			description = scanner.next();
		}
		System.out.println("Enter average cost per night : ");
		String avgRatePerNightStr = scanner.next();
		while (!service.isValidCost(avgRatePerNightStr)) 
		{
          	System.err.println(IMsgMapper.COST_ERROR);            //VALIDATION OF HOTEL COST
			avgRatePerNightStr = scanner.next();
		}
		Double avgRatePerNight=Double.parseDouble(avgRatePerNightStr);
		System.out.println("Enter Hotel rating (0-5) : ");
		String ratingstr = scanner.next();
		while (!service.isValidRating(ratingstr))                   //VALIDATION OF HOTEL RATING
		{

			System.err.println(IMsgMapper.RATING_ERROR);
			ratingstr = scanner.next();
		}

		Float rating=Float.parseFloat(ratingstr);
		HotelBean hotelBean = new HotelBean(city, hotelName, hotelAddress,
				description, avgRatePerNight, phoneNo1, phoneNo2, rating,
				hotelEmail, fax);
		// Add hotel method
		int hotelAddStatus = service.addHotel(hotelBean);
		if (hotelAddStatus == 1) {
			System.out.println("Hotel Id "+hotelBean.getHotelId()+" Added Successfully");
		} else {
			System.err.println("Hotel is not added");
		}

	}
	/******************************************************************************************************************************************
	 *                                                       UPDATE HOTEL METHOD                                                              *
	 *****************************************************************************************************************************************/

	private void updateHotel() throws HotelBookingException 
	{
		ArrayList<HotelBean> hotelList = new ArrayList<HotelBean>();
		System.out.println("Enter hotel id to update");
		int hotelId = scanner.nextInt();
        System.out.println("update method");
		hotelList = service.viewHotel(hotelId);
		if (hotelList.isEmpty()) {
			System.err.println("No records found to update");
			return;
		}
		for (HotelBean hotelBean1 : hotelList) {
			 System.out.println("update method");
			System.out.println("Below Details stored in the database");
			System.out.println();
			System.out.println("1) Hotel Name : " + hotelBean1.getHotelName());
			System.out.println("2) Address : " + hotelBean1.getAddress());
			System.out.println("3) Contact Number 1 : "+ hotelBean1.getPhoneNo1());
			System.out.println("4) Contact Number 2 : "+ hotelBean1.getPhoneNo2());
			System.out.println("5) Email : " + hotelBean1.getEmail());
			System.out.println("6) Fax : " + hotelBean1.getFax());
			System.out.println("7) Average Cost per Night : "+ hotelBean1.getAvgRatePerNight());
			System.out.println("8) Hotel Rating : " + hotelBean1.getRating());
			System.out.println();
			System.out.println();
		}
		System.out.println("Update Hotel Name : ");
		String hotelName = scanner.next();
		System.out.println("Update Hotel Address");
		String hotelAddress = scanner.next();
		System.out.println("Update City");
		String city = scanner.next();
		System.out.println("Update Phone No1");
		String phoneNo1 = scanner.next();
		System.out.println("Update Phone No2");
		String phoneNo2 = scanner.next();
		System.out.println("Update Email");
		String hotelEmail = scanner.next();
		System.out.println("Update fax");
		String fax = scanner.next();
		System.out.println("Update Description");
		String description = scanner.next();
		System.out.println("Update average cost per night");
		double avgRatePerNight = scanner.nextDouble();
		System.out.println("Update Hotel rating");
		float rating = scanner.nextFloat();
		HotelBean hotelBean = new HotelBean(hotelId, city, hotelName,
				hotelAddress, description, avgRatePerNight, phoneNo1, phoneNo2,
				rating, hotelEmail, fax);
		int hotelUpdateStatus = service.modifyHotel(hotelBean);
		if (hotelUpdateStatus == 1) {
			System.out.println("Hotel Id " + hotelBean.getHotelId()+ " updated successfully");
		} else {
			System.err.println("Hotel Id " + hotelBean.getHotelId()+ " is not updated");
		}
		}

	


	/******************************************************************************************************************************************
	 *                                                       DELETE HOTEL METHOD                                                              *
	 *****************************************************************************************************************************************/
        private void deleteHotel() throws HotelBookingException {
		System.out.println("Enter hotel id to delete");
		int deleteHotelId = scanner.nextInt();

		int validDelete = service.deleteHotelValidate(deleteHotelId);
		if (validDelete == 0) {
			int deleteStatus = service.deleteHotel(deleteHotelId);

			if (deleteStatus == 1) {
				System.out.println("Hotel " + deleteHotelId+ " deleted Successfully");

			} else {
				System.err.println("Delete failed");
			}
		} else {
			System.err.println("Hotel is already booked");
		}

	}
  /*******************************************************PERFORM ROOM MANAGEMENT***********************************************************************/
  /******************************************************************************************************************************************
  *                                                          ADD ROOM METHOD                                                              *
  *****************************************************************************************************************************************/

	public void addRoom() throws HotelBookingException {
		System.out.println("Enter Hotel Id : ");
		String hotelIdstr = scanner.next();
		while (!service.isValidId(hotelIdstr)) {

			System.err.println(IMsgMapper.ID_ERROR);
			hotelIdstr = scanner.next();
		}
		int hotelId=Integer.parseInt(hotelIdstr);
		System.out.println("Enter Room No : ");
		String roomNo = scanner.next();
		while (!service.isValidRoomNo(roomNo)) {

			System.err.println(IMsgMapper.ROOM_NO_ERROR);
			roomNo = scanner.next();
		}
		System.out.println("Enter Room Type : ");
		String roomType = scanner.next();
		while (!service.isValidRoomType(roomType)) {

			System.err.println(IMsgMapper.ROOM_TYPE_ERROR);
			roomType = scanner.next();
		}
		System.out.println("Enter Room Size");
		String roomSizestr = scanner.next();
		while (!service.isValidRoomSize(roomSizestr)) {

			System.err.println(IMsgMapper.ROOM_SIZE_ERROR);
			roomSizestr = scanner.next();
		}
		int roomSize=Integer.parseInt(roomSizestr);

		System.out.println("Enter cost per night : ");
		String perNightRatestr = scanner.next();
		while (!service.isValidCost(perNightRatestr)) {

			System.err.println(IMsgMapper.COST_ERROR);
			perNightRatestr = scanner.next();
		}
		Double perNightRate=Double.parseDouble(perNightRatestr);
		System.out.println("Enter availability (true or false) : ");
		String availability = scanner.next();
		while (!service.isValidAvailability(availability)) {

			System.err.println(IMsgMapper.AVAILABILITY_ERROR);
			availability = scanner.next();
		}
		RoomDetailsBean roomDetailsBean = new RoomDetailsBean(hotelId, roomNo,
				roomType, perNightRate, availability, roomSize);
		// Add room method
		int roomAddStatus = service.addRoom(roomDetailsBean);
		if (roomAddStatus == 1) {
			System.out.println("Room Id "+roomDetailsBean.getRoomId()+" added successfully");
		} else {
			System.err.println("Room not added");
		}
	}
	 /******************************************************************************************************************************************
	  *                                                          UPDATE ROOM METHOD                                                              *
	  *****************************************************************************************************************************************/

	public void updateRoom() throws HotelBookingException {
		System.out.println("Enter hotel id to update");
		int hotelId = scanner.nextInt();
		System.out.println("Enter room id to update");
		int roomId = scanner.nextInt();

		room = service.viewRoom(roomId, hotelId);
		if (room.getRoomId()==0) {
			System.err.println("No records found to update");
			return;
		}
		System.out.println("Below Details stored in the database");
		System.out.println();
		System.out.println("Room Number : " + room.getRoomNo());
		System.out.println("Room type : " + room.getRoomType());
		System.out.println("Room Cost : " + room.getPerNightRate());
		System.out.println("Room size : " + room.getRoomSize());
		System.out.println("Availability Status : "+ room.getAvailability());
		System.out.println();
		System.out.println();
		System.out.println("Update Room No");
		String roomNo = scanner.next();
		System.out.println("Update Room Type");
		String roomType = scanner.next();
		System.out.println("Update Room Size");
		int roomSize = scanner.nextInt();
		System.out.println("Update cost per night");
		double perNightRate = scanner.nextDouble();
		System.out.println("Update availability");
		String availability = scanner.next();

		RoomDetailsBean roomDetailsBean = new RoomDetailsBean(hotelId, roomId,
				roomNo, roomType, perNightRate, availability, roomSize);

		// update room method
		int roomUpdateStatus = service.modifyRoom(roomDetailsBean);
		if (roomUpdateStatus == 1) {
			System.out.println("Room " + roomId + " updated Successfuly");
		} else {
			System.err.println("Room " + roomId + " not updated");
		}

	}
	 /******************************************************************************************************************************************
	  *                                                          DELETE ROOM METHOD                                                              *
	  *****************************************************************************************************************************************/

	public void deleteRoom() throws HotelBookingException {
		System.out.println("Enter hotel id ");
		int hotelId = scanner.nextInt();
		System.out.println("Enter room id to delete");
		int roomId = scanner.nextInt();

		RoomDetailsBean roomDetailsBean = new RoomDetailsBean();

		roomDetailsBean.setHotelId(hotelId);
		roomDetailsBean.setRoomId(roomId);
		int validDelete = service.deleteRoomValidate(hotelId, roomId);
		if (validDelete == 0) {
			int deleteStatus = service.deleteRoom(roomDetailsBean);

			if (deleteStatus == 1) {
				System.out.println("Room " + roomId+ " deleted Successfully in Hotel " + hotelId);

			} else {
				System.out.println("Delete failed");
			}
		} else {
			System.out.println("Room is already booked");
			System.out.println();
		}
	}


	/*******************************************************GENERATE VARIOUS REPORTS**************************************************************/
	/*********************************************************************************************************************************************
	 *                                                  VIEW LIST OF ALL HOTELS METHOD                                                           
	 * @throws HotelBookingException *
	 *********************************************************************************************************************************************/


	private void allHotelsList() throws HotelBookingException {
		ArrayList<HotelBean> hotelList = new ArrayList<HotelBean>();
		hotelList = service.viewAllHotels();
		if (hotelList.isEmpty()) {
			System.err.println("No hotels found");
			return;
		}

		for (HotelBean hotelBean1 : hotelList) {
			System.out.println("---------------------------Hotel Id  "+ hotelBean1.getHotelId() + "-------------------------");
			System.out.println();
			System.out.println("Hotel Name : " + hotelBean1.getHotelName());
			System.out.println("Address : " + hotelBean1.getAddress());
			System.out.println("City : " + hotelBean1.getCity());
			System.out.println("Contact Number 1 : " + hotelBean1.getPhoneNo1());
			System.out.println("Contact Number 2 : " + hotelBean1.getPhoneNo2());
			System.out.println("Email : " + hotelBean1.getEmail());
			System.out.println("Fax : " + hotelBean1.getFax());
			System.out.println("Average Cost per Night : "+ hotelBean1.getAvgRatePerNight());
			System.out.println("Hotel Rating : " + hotelBean1.getRating());
			System.out.println();
			System.out.println();
			System.out.println();
		}

	}
	/*********************************************************************************************************************************************
	 *                                                    VIEW BOOKINGS OF SPECIFIC HOTEL METHOD                                                 *
	 *********************************************************************************************************************************************/
	void viewSpecificHotelBooking() throws HotelBookingException {

		ArrayList<BookingDetailsBean> hotelBookingList = new ArrayList<BookingDetailsBean>();
		System.out.println("Enter hotel id ");
		int hotelId = scanner.nextInt();
		hotelBookingList = service.viewSpecificHotelBooking(hotelId);
		if (hotelBookingList.isEmpty()) {
			System.err.println("No booking found");

			return;
		}

		for (BookingDetailsBean bookingDetailsBean : hotelBookingList) {
			System.out.println("--------------Booking Id "+ bookingDetailsBean.getBookingId()+ " ----------------------");
			System.out.println();
			System.out.println("User Id : " + bookingDetailsBean.getUserId());
			System.out.println("Room Id : " + bookingDetailsBean.getRoomId());
			System.out.println("Check-in date (yyyy-mm-dd) : "+ bookingDetailsBean.getBookedFrom().substring(0, 10));
			System.out.println("check-out date (yyyy-mm-dd) : "+ bookingDetailsBean.getBookedTo().substring(0, 10));
			System.out.println("Number of adults : "+ bookingDetailsBean.getNoOfAdults());
			System.out.println("Amount Paid : "+ bookingDetailsBean.getAmount());
			System.out.println();
			System.out.println();
		}

	}
	/**********************************************************************************************************************************************
	 *                                                        VIEW GUEST LIST OF SPECIFIC HOTEL  METHOD                                           *
	 **********************************************************************************************************************************************/

	public void viewSpecificHotelGuestList() throws HotelBookingException {

		ArrayList<UsersBean> hotelGuestList = new ArrayList<UsersBean>();
		System.out.println("Enter hotel id ");
		int hotelId = scanner.nextInt();
		hotelGuestList = service.viewSpecificHotelGuestList(hotelId);
		if (hotelGuestList.isEmpty()) {
			System.out.println("No guest list found");
			System.out.println();
			return;
		}

		for (UsersBean usersBean : hotelGuestList) {
			System.out.println("--------------User Id " + usersBean.getUserId()+ " ----------------------");
			System.out.println();
			System.out.println("Name : " + usersBean.getUserName());
			System.out.println("Mobile number : " + usersBean.getMobileNo());
			System.out.println("Address : " + usersBean.getAddress());
			System.out.println("Email : " + usersBean.getEmail());
			System.out.println();
			System.out.println();
		}

	}
	/*************************************************************************************************************************************************
	 *                                                  VIEW BOOKINGS FOR SPECIFIED DATE METHOD                                                      *
	 ************************************************************************************************************************************************/

	public void viewSpecificDateBooking() throws HotelBookingException {
		ArrayList<BookingDetailsBean> specificDateBookingList = new ArrayList<BookingDetailsBean>();
		System.out.println("Enter date : ");
		String date = scanner.nextLine();
		specificDateBookingList = service.viewSpecificDateBooking(date);
		if (specificDateBookingList.isEmpty()) {
			System.err.println("No booking found");
			return;
		}
		for (BookingDetailsBean bookingDetailsBean : specificDateBookingList) {
			System.out.println("--------------Booking Id "+ bookingDetailsBean.getBookingId()+ " ----------------------");
			System.out.println();
			System.out.println("User Id : " + bookingDetailsBean.getUserId());
			System.out.println("Hotel Id : " + bookingDetailsBean.getHotelId());
			System.out.println("Room Id : " + bookingDetailsBean.getRoomId());
			System.out.println("Check-in date (yyyy-mm-dd) : "+ bookingDetailsBean.getBookedFrom().substring(0, 10));
			System.out.println("check-out date (yyyy-mm-dd) : "+ bookingDetailsBean.getBookedTo().substring(0, 10));
			System.out.println("Number of adults : "+ bookingDetailsBean.getNoOfAdults());
			System.out.println("Amount Paid : "+ bookingDetailsBean.getAmount());
			System.out.println();
			System.out.println();
		}

	}








}


