package com.ant.ttf.domain.users.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import com.ant.ttf.domain.users.dto.request.UsersRequestDTO;
import com.ant.ttf.domain.users.dto.response.UserDashboardInfoDTO;
import com.ant.ttf.domain.users.entity.Users;

@Mapper
public interface UsersMapper {
	Optional<Users> findUserByUserEmail(String email);
    Optional<Users> findByUserId(Long userId);
    Users findUserInfoByPk(String userPk);
    void userSignUp(UsersRequestDTO sigUpdto);
    void updateIncome(String userPk, int income);
    void updateGoalBudget(String userPk, int goalBudget);
}
