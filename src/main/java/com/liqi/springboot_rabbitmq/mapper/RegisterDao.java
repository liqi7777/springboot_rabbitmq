package com.liqi.springboot_rabbitmq.mapper;

import com.liqi.springboot_rabbitmq.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * user mapper
 */
@Mapper
@Repository
public interface RegisterDao {

    /**
     * 增加user
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` (`id`,`name`, `age`, `email`, `alias_name`, `sex`) VALUES (6,'liqi', '22', 'liqi@ixiaoshuidi.com', 'liqi_ll', 'man')")
    void addUser(@Param("user") User user);

    /**
     * 删除user
     *
     * @param id
     * @return
     */
    @Delete("delete from `user` where _id = #{id}")
    void deleteUser(int id);


}
