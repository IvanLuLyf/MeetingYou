package meetingmanager.dao;

import java.sql.*;
import java.util.*;

import meetingmanager.util.*;
import meetingmanager.vo.*;

public class MeetingRoomDAO {
	private Connection conn = null;

	public List<MeetingRoom> selectAll() {
		conn = DataBaseConnection.getConnection();

		List<MeetingRoom> meetingRooms = new ArrayList<MeetingRoom>();

		try {
			Statement st = null;
			String sql = "select * from mm_room";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			MeetingRoom meetingRoom;
			while (rs.next()) {
				meetingRoom = new MeetingRoom();
				meetingRoom.setRoomid(rs.getInt("roomid"));
				meetingRoom.setName(rs.getString("name"));
				meetingRoom.setNumber(rs.getInt("number"));
				meetingRoom.setCapacity(rs.getInt("capacity"));
				meetingRoom.setDescription(rs.getString("description"));
				meetingRoom.setStaus(rs.getInt("status"));
				meetingRooms.add(meetingRoom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetingRooms;
	}

	public MeetingRoom selectByNumber(Integer number) {
		MeetingRoom meetingRoom = null;
		conn = DataBaseConnection.getConnection();
		try {
			PreparedStatement st = null;
			String sql = "select * from mm_room where number=" + number + " ";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next() == true) {
				meetingRoom = new MeetingRoom();
				meetingRoom.setRoomid(rs.getInt("roomid"));
				meetingRoom.setName(rs.getString("name"));
				meetingRoom.setNumber(rs.getInt("number"));
				meetingRoom.setCapacity(rs.getInt("capacity"));
				meetingRoom.setDescription(rs.getString("description"));
				meetingRoom.setStaus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetingRoom;
	}

	public MeetingRoom selectByRoomid(Integer roomid) {
		MeetingRoom meetingRoom = null;
		conn = DataBaseConnection.getConnection();
		try {
			PreparedStatement st = null;
			String sql = "select * from mm_room where roomid=" + roomid + " ";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next() == true) {
				meetingRoom = new MeetingRoom();
				meetingRoom.setRoomid(rs.getInt("roomid"));
				meetingRoom.setName(rs.getString("name"));
				meetingRoom.setNumber(rs.getInt("number"));
				meetingRoom.setCapacity(rs.getInt("capacity"));
				meetingRoom.setDescription(rs.getString("description"));
				meetingRoom.setStaus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetingRoom;
	}
	
	public void insert(MeetingRoom meetingRoom) {
		conn = DataBaseConnection.getConnection();
		String sql = "insert into mm_room"
				+ "(number,name,capacity,status,description)"
				+ " values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingRoom.getNumber());
			pstmt.setString(2, meetingRoom.getName());
			pstmt.setInt(3, meetingRoom.getCapacity());
			pstmt.setInt(4, meetingRoom.getStatus());
			pstmt.setString(5, meetingRoom.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
	}

	public void updateStatus(int roomid, Integer number, String name,
			Integer capacity, Integer status, String description) {
		conn = DataBaseConnection.getConnection();
		String sql = "update mm_room set number=" + number + ",name='" + name
				+ "',capacity=" + capacity + ",status=" + status
				+ ",description='" + description + "'" + " where roomid="
				+ roomid;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
	}

	public static void main(String[] args) {
		MeetingRoomDAO dao = new MeetingRoomDAO();
		// MeetingRoom meetingRoom = new MeetingRoom(110, "—˝—˝¡È", 20, 1, "≤‚ ‘ÃÌº”");
		// dao.insert(meetingRoom);
		List<MeetingRoom> meetingRooms = dao.selectAll();
		System.out.println(meetingRooms);
	}

}
