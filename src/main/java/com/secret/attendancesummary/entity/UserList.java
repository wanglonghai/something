package com.secret.attendancesummary.entity;

import com.secret.attendancesummary.Service.AttendanceCheckService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "users")
@Data
public class UserList {
	private List<UserInfo> list=new ArrayList<UserInfo>();

 
	public UserList() {
		super();
	}
 
	public UserList(List<UserInfo> list) {
		super();
		this.list = list;
	}
 
	public List<UserInfo> getList() {
		return list;
	}
 
	public void setList(List<UserInfo> list) {
		this.list = list;
	}
	
}