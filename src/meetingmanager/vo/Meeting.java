package meetingmanager.vo;

import java.sql.Timestamp;

public class Meeting {
	private Integer meetingid;
	private Integer roomid;
	private Integer reserverid;
	private String name;
	private Integer capacity;
	private Integer status;
	private Timestamp reservetime;
	private Timestamp begintime;
	private Timestamp endtime;
	private Timestamp canceltime;
	private String description;
	private String member;

	public Meeting() {
		super();
	}

	public Meeting(Integer roomid, Integer reserverid, String name,
			Integer capacity, Integer status, Timestamp reservetime,
			Timestamp begintime, Timestamp endtime, Timestamp canceltime,
			String description, String member) {
		super();
		this.roomid = roomid;
		this.reserverid = reserverid;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.reservetime = reservetime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.canceltime = canceltime;
		this.description = description;
		this.member = member;
	}

	public Meeting(Integer meetingid, Integer roomid, Integer reserverid,
			String name, Integer capacity, Integer status,
			Timestamp reservetime, Timestamp begintime, Timestamp endtime,
			Timestamp canceltime, String description, String member) {
		super();
		this.meetingid = meetingid;
		this.roomid = roomid;
		this.reserverid = reserverid;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.reservetime = reservetime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.canceltime = canceltime;
		this.description = description;
		this.member = member;
	}

	public Integer getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(Integer meetingid) {
		this.meetingid = meetingid;
	}

	public Integer getRoomid() {
		return roomid;
	}

	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}

	public Integer getReserverid() {
		return reserverid;
	}

	public void setReserverid(Integer reserverid) {
		this.reserverid = reserverid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getReservetime() {
		return reservetime;
	}

	public void setReservetime(Timestamp reservetime) {
		this.reservetime = reservetime;
	}

	public Timestamp getBegintime() {
		return begintime;
	}

	public void setBegintime(Timestamp begintime) {
		this.begintime = begintime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Timestamp getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(Timestamp canceltime) {
		this.canceltime = canceltime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public static void main(String args[]) {
		Timestamp timestamp = new Timestamp(1498705015);
		Meeting meeting = new Meeting(2, 101, 1, "Test", 20, 0, timestamp,
				timestamp, timestamp, timestamp, "", "[1,2,3]");
		System.out.println(meeting);
	}

	public String toString() {
		return "{\"meetingid\":" + meetingid + ",\"roomid\":" + roomid
				+ ",\"reserverid\":" + reserverid + ",\"name\":\"" + name
				+ "\",\"capacity\":" + capacity + ",\"status\":" + status
				+ ",\"reservetime\":\"" + reservetime + "\",\"begintime\":\""
				+ begintime + "\",\"endtime\":\"" + endtime
				+ "\",\"canceltime\":\"" + canceltime + "\",\"member\":"
				+ member + "}";
	}
}
