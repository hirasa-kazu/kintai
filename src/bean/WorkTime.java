package bean;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkTime implements java.io.Serializable{

	private LocalDate workDate;			//出勤日
	private LocalTime startTime;		//出勤時刻
	private LocalTime finishTime;		//退勤時刻
	private LocalTime breakStartTime;	//休憩開始時刻
	private LocalTime breakFinishTime;	//休憩終了時刻
	private Duration breakTime;			//休憩時間
	private Duration workingHours;		//勤務時間


	//出勤日の所得・セット
	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}


	//出勤時刻の所得・セット
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}


	//退勤時刻の所得・セット
	public LocalTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalTime finishTime){
		this.finishTime = finishTime;
	}


	//休憩開始時刻の所得・セット
	public LocalTime getBreakStartTime() {
		return breakStartTime;
	}

	public void setBreakStartTime(LocalTime breakStartTime) {
		this.breakStartTime = breakStartTime;
	}


	//休憩終了時刻の所得・セット
	public LocalTime getBreakFinishTime() {
		return breakFinishTime;
	}

	public void setBreakFinishTime(LocalTime breakFinishTime) {
		this.breakFinishTime = breakFinishTime;
	}


	//休憩時間の所得・セット
	public Duration getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(Duration breakTime) {
		this.breakTime = breakTime;
	}


	//勤務時間の所得・セット
	public Duration getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Duration workingHours) {
		this.workingHours = workingHours;
	}
}
