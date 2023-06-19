package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.user.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserAccountMapper {

    @Select("select * from blog.user_account where user_account_username = #{text}")
    UserAccount findByUsername(String text);

    @Insert("insert into blog.user_account(user_account_username, password,email) values(#{userAccountName},#{password},#{email})")
    int registerAccount(UserAccount userAccount);

    @Update("update blog.user_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);

}
