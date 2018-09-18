package meetingmanager.vo;

public class MeetingRoom {
	private Integer roomid;
	private Integer number;
	private String name;
	private Integer capacity;
	private Integer status;
	private String description;

	public MeetingRoom() {
		super();
	}

	public MeetingRoom(Integer number, String name, Integer capacity,
			Integer status, String description) {
		super();
		this.number = number;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.description = description;
	}

	public MeetingRoom(Integer roomid, Integer number, String name,
			Integer capacity, Integer status, String description) {
		super();
		this.roomid = roomid;
		this.number = number;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.description = description;
	}

	public Integer getRoomid() {
		return roomid;
	}

	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

	public void setStaus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String decription) {
		this.description = decription;
	}

	@Override
	public String toString() {
		return "{\"roomid\":" + roomid + ",\"number\":" + number
				+ ",\"name\":\"" + name + "\",\"capacity\":" + capacity
				+ ",\"status\":" + status + ",\"description\":\"" + description
				+ "\"}";
	}

	public static void main(String[] args) {
		MeetingRoom meetingRoom = new MeetingRoom(1, 101, "Room One O One", 50,
				0, "Haha");
		System.out.println(meetingRoom);
	}

}
