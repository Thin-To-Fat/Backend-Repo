package com.ant.ttf.domain.savings.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.ant.ttf.domain.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Savings {
	private long savings_id;
	private String name;
	private double maxRate;
	private double basicRate;
	private String link;
	private int joinMoney;
	private String joinWho;
	private String saveHow;
	
}
