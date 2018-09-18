package meetingmanager.service;

import java.util.List;

import meetingmanager.dao.*;
import meetingmanager.vo.*;

public class MeetingRoomService {
	
	private MeetingRoomDAO dao = new MeetingRoomDAO();
	
	public List<MeetingRoom> getRooms() {
		return dao.selectAll();
	}
	
	public MeetingRoom getRoom(Integer roomid){
		return dao.selectByRoomid(roomid);
	}
	
	public int addRoom(MeetingRoom room) {
		int flag = 0;
		MeetingRoom meetingRoom = dao.selectByNumber(room.getNumber());
		if (meetingRoom == null) {
			flag = 1;
			dao.insert(room);
		}
		return flag;
	}
	
	public void updateRoom(MeetingRoom room){
		dao.updateStatus(room.getRoomid(), room.getNumber(), room.getName(), room.getCapacity(), room.getStatus(), room.getDescription());
		
	}
}
