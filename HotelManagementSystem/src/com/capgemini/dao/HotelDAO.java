package com.capgemini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.capgemini.bean.BookingDetailsBean;
import com.capgemini.bean.HotelBean;
import com.capgemini.bean.RoomDetailsBean;
import com.capgemini.bean.UsersBean;
import com.capgemini.dbutil.DBUtil;
import com.capgemini.dbutil.IQueryMapper;
import com.capgemini.exception.HotelBookingException;

public class HotelDAO implements IHotelDAO
{

	@Override
	public int addHotel(HotelBean hotelBean) throws HotelBookingException {
		Connection connection = null;
		int hotelStatus = 0;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(IQueryMapper.HOTEL_INSERT_QUERY);
			preparedStatement.setString(1, hotelBean.getCity());
			preparedStatement.setString(2, hotelBean.getHotelName());
			preparedStatement.setString(3, hotelBean.getAddress());
			preparedStatement.setString(4, hotelBean.getDescription());
			preparedStatement.setDouble(5, hotelBean.getAvgRatePerNight());
			preparedStatement.setString(6, hotelBean.getPhoneNo1());
			preparedStatement.setString(7, hotelBean.getPhoneNo2());
			preparedStatement.setFloat(8, hotelBean.getRating());
			preparedStatement.setString(9, hotelBean.getEmail());
			preparedStatement.setString(10, hotelBean.getFax());

			hotelStatus = preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement(IQueryMapper.HOTEL_ID_QUERY_SEQUENCE);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				hotelBean.setHotelId(id);
			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return hotelStatus;
	}

	@Override
	public ArrayList<HotelBean> viewHotel(int hotelId) throws HotelBookingException {
		ArrayList<HotelBean> hotelList = new ArrayList<HotelBean>();
         Connection connection=null;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.VIEW_HOTEL);
			preparedStatement.setInt(1, hotelId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				HotelBean hotelBean = new HotelBean(resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getDouble(6),
						resultSet.getString(7), resultSet.getString(8),
						resultSet.getFloat(9), resultSet.getString(10),
						resultSet.getString(11));

				hotelList.add(hotelBean);

			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return hotelList;
	}

	@Override
	public int modifyHotel(HotelBean hotelBean) throws HotelBookingException {
		Connection connection = null;
		int hotelStatus = 0;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.HOTEL_UPDATE_QUERY);
			preparedStatement.setString(1, hotelBean.getCity());
			preparedStatement.setString(2, hotelBean.getHotelName());
			preparedStatement.setString(3, hotelBean.getAddress());
			preparedStatement.setString(4, hotelBean.getDescription());
			preparedStatement.setDouble(5, hotelBean.getAvgRatePerNight());
			preparedStatement.setString(6, hotelBean.getPhoneNo1());
			preparedStatement.setString(7, hotelBean.getPhoneNo2());
			preparedStatement.setFloat(8, hotelBean.getRating());
			preparedStatement.setString(9, hotelBean.getEmail());
			preparedStatement.setString(10, hotelBean.getFax());
			preparedStatement.setInt(11, hotelBean.getHotelId());

			hotelStatus = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return hotelStatus;
	}

	@Override
	public int deleteHotel(int deleteHotelId) throws HotelBookingException {
		int hotelDeleteStatus = 0;
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(IQueryMapper.HOTEL_DELETE_QUERY);
			preparedStatement.setInt(1, deleteHotelId);

			hotelDeleteStatus = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return hotelDeleteStatus;
	}

	@Override
	public int deleteHotelValidate(int deleteHotelId) throws HotelBookingException {
		int idDeleteStatus=0;
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(IQueryMapper.HOTEL_DELETE_VALIDATE_QUERY);
			preparedStatement.setInt(1, deleteHotelId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				idDeleteStatus=resultSet.getInt(1);

			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return idDeleteStatus;
	
	}

	@Override
	public int modifyRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException {
		int roomStatus = 0;
		Connection connection =null;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(IQueryMapper.ROOM_UPDATE_QUERY);

			preparedStatement.setString(1, roomDetailsBean.getRoomNo());
			preparedStatement.setString(2, roomDetailsBean.getRoomType());
			preparedStatement.setDouble(3, roomDetailsBean.getPerNightRate());
			preparedStatement.setString(4, roomDetailsBean.getAvailability());
			preparedStatement.setInt(5, roomDetailsBean.getRoomSize());
			preparedStatement.setInt(6, roomDetailsBean.getHotelId());
			preparedStatement.setInt(7, roomDetailsBean.getRoomId());
			roomStatus = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return roomStatus;

	}

	@Override
	public int deleteRoomValidate(int hotelId, int roomId) throws HotelBookingException {
		Connection connection =null;

		int roomIdDeleteStatus=0;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.ROOM_DELETE_VALIDATE_QUERY);
			preparedStatement.setInt(1, hotelId);
			preparedStatement.setInt(2, roomId);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				roomIdDeleteStatus=resultSet.getInt(1);

			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return roomIdDeleteStatus;
	
	}
	// delete room
	@Override
	public int deleteRoom(RoomDetailsBean roomDetailsBean)throws HotelBookingException {
		Connection connection =null;

		int DeleteStatus = 0;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.ROOM_DELETE_QUERY);
			preparedStatement.setInt(1, roomDetailsBean.getHotelId());
			preparedStatement.setInt(2, roomDetailsBean.getRoomId());
			DeleteStatus = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return DeleteStatus;
	}

	@Override
	public int addRoom(RoomDetailsBean roomDetailsBean) throws HotelBookingException {
		Connection connection =null;

		int roomStatus = 0;
		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(IQueryMapper.ROOM_INSERT_QUERY);
			preparedStatement.setInt(1, roomDetailsBean.getHotelId());
			preparedStatement.setString(2, roomDetailsBean.getRoomNo());
			preparedStatement.setInt(3, roomDetailsBean.getRoomSize());
			preparedStatement.setString(4, roomDetailsBean.getRoomType());
			preparedStatement.setDouble(5, roomDetailsBean.getPerNightRate());
			preparedStatement.setString(6, roomDetailsBean.getAvailability());

			roomStatus = preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement(IQueryMapper.ROOM_ID_QUERY_SEQUENCE);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
                roomDetailsBean.setRoomId(id);
			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return roomStatus;
	}

	@Override
	public RoomDetailsBean viewRoom(int roomId, int hotelId) throws HotelBookingException {
		Connection connection =null;

		RoomDetailsBean roomDetailsBean = null;
		try {
			connection = DBUtil.getConnection();
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.VIEW_ROOM);
			preparedStatement.setInt(1, roomId);
			preparedStatement.setInt(2, hotelId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				roomDetailsBean = new RoomDetailsBean(resultSet.getInt(1),resultSet.getInt(2),
						resultSet.getString(3), resultSet.getString(5),
						resultSet.getDouble(6), resultSet.getString(7),
						resultSet.getInt(4));

			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return roomDetailsBean;
	}

	@Override
	public ArrayList<HotelBean> viewAllHotels() throws HotelBookingException {
		Connection connection =null;
		ArrayList<HotelBean> hotelList = new ArrayList<HotelBean>();

		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.VIEW_ALL_HOTELS_QUERY);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				HotelBean hotelBean = new HotelBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getDouble(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getFloat(9),
						resultSet.getString(10), resultSet.getString(11));

				hotelList.add(hotelBean);

			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return hotelList;
	}

	@Override
	public ArrayList<BookingDetailsBean> viewSpecificHotelBooking(int hotelId) throws HotelBookingException {
		Connection connection =null;
		
		ArrayList<BookingDetailsBean> bookingList = new ArrayList<BookingDetailsBean>();

		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.VIEW_SPECIFIC_HOTEL_BOOKING_QUERY);
			preparedStatement.setInt(1, hotelId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				BookingDetailsBean bookingDetailsBean = new BookingDetailsBean(
						resultSet.getInt(1), resultSet.getInt(2),
						resultSet.getInt(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getInt(6),
						resultSet.getDouble(7), resultSet.getInt(8));

				bookingList.add(bookingDetailsBean);

			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return bookingList;
	}

	@Override
	public ArrayList<UsersBean> viewSpecificHotelGuestList(int hotelId) throws HotelBookingException {
		Connection connection =null;
		ArrayList<UsersBean> guestList = new ArrayList<UsersBean>();

		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.VIEW_SPECIFIC_HOTEL_GUESTLIST_QUERY);
			preparedStatement.setInt(1, hotelId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				UsersBean usersBean=new UsersBean(resultSet.getInt(1), resultSet.getString(2)
						,resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
						resultSet.getString(6),resultSet.getString(7));

				guestList.add(usersBean);

			}

		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}

		return guestList;
	}

	@Override
	public ArrayList<BookingDetailsBean> viewSpecificDateBooking(String date) throws HotelBookingException {
		Connection connection =null;
		ArrayList<BookingDetailsBean> specificDateBookingList = new ArrayList<BookingDetailsBean>();

		try {
			connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection
					.prepareStatement(IQueryMapper.VIEW_SPECIFIC_DATE_BOOKING);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, date);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				BookingDetailsBean bookingDetailsBean = new BookingDetailsBean(
						resultSet.getInt(1), resultSet.getInt(2),
						resultSet.getInt(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getInt(6),
						resultSet.getDouble(7), resultSet.getInt(8));

				specificDateBookingList.add(bookingDetailsBean);
			}
		} catch (Exception e) {
			throw new HotelBookingException(e.getMessage());
		}
		return specificDateBookingList;
	}

}
