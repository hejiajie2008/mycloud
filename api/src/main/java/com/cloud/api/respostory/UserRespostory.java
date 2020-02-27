package com.cloud.api.respostory;

import com.cloud.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRespostory {

    @Autowired
    private DataSource dataSource;

    public boolean save(UserVo userVo){
        try{
            Connection conn=dataSource.getConnection();

           PreparedStatement preparedStatement= conn.prepareStatement("insert user(id,username) values(?,?)");
           preparedStatement.setLong(1,userVo.getId());
           preparedStatement.setString(2,userVo.getUsername());
          Boolean result= preparedStatement.execute();
          return result;
        }catch (Exception e){

        }

        return true;
    }

    public List<UserVo> queryList(){
        List<UserVo> list=new ArrayList<>();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement("select id,username from user");
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                UserVo userVo=new UserVo();
                userVo.setId(resultSet.getLong(1));
                userVo.setUsername(resultSet.getString(2));
                list.add(userVo);
            }
        }catch (Exception e){

        }
        return list;
    }


}
