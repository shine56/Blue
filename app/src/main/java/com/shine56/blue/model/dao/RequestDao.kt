package com.shine56.blue.model.dao

import androidx.room.*
import com.shine56.blue.model.bean.RequestBean

@Dao
interface RequestDao {
    //插入
    @Insert
    fun insertBean(bean: RequestBean): Long
    //查询
    @Query("select * from request where id = :id")
    fun queryBeanById(id: Int): RequestBean
    @Query("select * from request")
    fun queryAllBeans(): List<RequestBean>
    //更新
    @Update
    fun updateBean(bean: RequestBean): Int
    //删除
    @Delete
    fun deleteBean(bean: RequestBean): Int
}