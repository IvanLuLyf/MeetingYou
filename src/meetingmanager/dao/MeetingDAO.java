package meetingmanager.dao;

import java.sql.*;
import java.util.*;

import meetingmanager.util.*;
import meetingmanager.vo.*;

public class MeetingDAO {
	private Connection conn = null;

	public List<Meeting> getMeetingByMemberUid(Integer uid){
		conn = DataBaseConnection.getConnection();
		List<Meeting> meetings = new ArrayList<Meeting>();
		Meeting meeting = new Meeting();
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_meeting where member REGEXP '[[:<:]]" + uid + "[[:>:]]' ";
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
				meeting.setReason(rs.getString("reason"));
				meetings.add(meeting);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetings;
	}
	
	public List<Meeting> getRecentMeetingByMemberUid(Integer uid){
		conn = DataBaseConnection.getConnection();
		List<Meeting> meetings = new ArrayList<Meeting>();
		Meeting meeting = new Meeting();
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_meeting where member REGEXP '[[:<:]]" + uid + "[[:>:]]' and TIMESTAMPDIFF(SECOND,NOW(),begintime) BETWEEN 0 AND 604800 ";
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
				meeting.setReason(rs.getString("reason"));
				meetings.add(meeting);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetings;
	}
	
	public List<Meeting> getCancelMeetingByMemberUid(Integer uid){
		conn = DataBaseConnection.getConnection();
		List<Meeting> meetings = new ArrayList<Meeting>();
		Meeting meeting = new Meeting();
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_meeting where member REGEXP '[[:<:]]" + uid + "[[:>:]]' and status=-1 order by meetingid desc";
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
				meeting.setReason(rs.getString("reason"));
				meetings.add(meeting);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetings;
	}
	
	public List<Meeting> getMeetingByReserverUid(Integer uid){
		conn = DataBaseConnection.getConnection();
		List<Meeting> meetings = new ArrayList<Meeting>();
		Meeting meeting = new Meeting();
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_meeting where reserverid=" + uid + " order by meetingid desc";
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
				meeting.setReason(rs.getString("reason"));
				meetings.add(meeting);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return meetings;
	}
	
	public void insert(Meeting meeting) {
		conn = DataBaseConnection.getConnection();
		String sql = "insert into mm_meeting"
				+ "(roomid,reserverid,name,capacity,status,reservetime,begintime,endtime,canceltime,description,member,reason)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meeting.getRoomid());
			pstmt.setInt(2, meeting.getReserverid());
			pstmt.setString(3, meeting.getName());
			
			pstmt.setInt(4, meeting.getCapacity());
			pstmt.setInt(5, meeting.getStatus());
			
			pstmt.setTimestamp(6, meeting.getReservetime());
			pstmt.setTimestamp(7, meeting.getBegintime());
			pstmt.setTimestamp(8, meeting.getEndtime());
			pstmt.setTimestamp(9, meeting.getCanceltime());

			pstmt.setString(10, meeting.getDescription());
			pstmt.setString(11, meeting.getMember());
			pstmt.setString(12, meeting.getReason());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
	}
	
	public static void main(String[] args) {
		MeetingDAO dao = new MeetingDAO();
		List<Meeting> meetings = dao.getCancelMeetingByMemberUid(1);
		System.out.println(meetings);	
	}

}
