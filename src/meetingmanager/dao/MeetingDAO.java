package meetingmanager.dao;

import java.sql.*;
import java.util.*;

import meetingmanager.util.*;
import meetingmanager.vo.*;

public class MeetingDAO {
	private Connection conn = null;

	List<Meeting> getMeetingByMemberUid(Integer uid){
		conn = DataBaseConnection.getConnection();
		List<Meeting> meetings = new ArrayList<Meeting>();
		Meeting meeting = new Meeting();
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_meeting where member REGEXP '[[:<:]]" + uid + "[[:>:]]'";
			psd = conn.prepareStatement(sql);
			ResultSet rs = psd.executeQuery(sql);
			while (rs.next()) {
				meeting = new Meeting();
				meeting.setMeetingid(rs.getInt("meetingid"));
				meeting.setRoomid(rs.getInt("roomid"));
				meeting.setReserverid(rs.getInt("reserverid"));
				meeting.setName(rs.getString("name"));
				meeting.setCapacity(rs.getInt("capacity"));
				meeting.setStatus(rs.getInt("status"));
				meeting.setReservetime(rs.getTimestamp("reservetime"));
				meeting.setBegintime(rs.getTimestamp("begintime"));
				meeting.setEndtime(rs.getTimestamp("endtime"));
				meeting.setCanceltime(rs.getTimestamp("canceltime"));
				meeting.setMember(rs.getString("member"));
				meetings.add(meeting);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetings;
	}
	
	List<Meeting> getMeetingByReserverUid(Integer uid){
		conn = DataBaseConnection.getConnection();
		List<Meeting> meetings = new ArrayList<Meeting>();
		Meeting meeting = new Meeting();
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_meeting where reserverid=" + uid + "";
			psd = conn.prepareStatement(sql);
			ResultSet rs = psd.executeQuery(sql);
			while (rs.next()) {
				meeting = new Meeting();
				meeting.setMeetingid(rs.getInt("meetingid"));
				meeting.setRoomid(rs.getInt("roomid"));
				meeting.setReserverid(rs.getInt("reserverid"));
				meeting.setName(rs.getString("name"));
				meeting.setCapacity(rs.getInt("capacity"));
				meeting.setStatus(rs.getInt("status"));
				meeting.setReservetime(rs.getTimestamp("reservetime"));
				meeting.setBegintime(rs.getTimestamp("begintime"));
				meeting.setEndtime(rs.getTimestamp("endtime"));
				meeting.setCanceltime(rs.getTimestamp("canceltime"));
				meeting.setMember(rs.getString("member"));
				meetings.add(meeting);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetings;
	}
	
	public static void main(String[] args) {
		MeetingDAO dao = new MeetingDAO();
		List<Meeting> meetings = dao.getMeetingByMemberUid(2);
		System.out.println(meetings);
		
		meetings = dao.getMeetingByReserverUid(1);
		System.out.println(meetings);
	}

}
