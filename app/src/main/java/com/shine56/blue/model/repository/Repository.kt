package com.shine56.blue.model.repository

import com.shine56.blue.model.bean.RequestBean
import com.shine56.blue.model.dao.AppDatabase

object Repository {
    private val database = AppDatabase.getDataBase()
    private val requestDao = database.requestDao()

    /**
     * 查询
     */
    fun loadRequestBeanById(id: Int) = requestDao.queryBeanById(id)
    fun loadAllRequestBeans() = requestDao.queryAllBeans()

    /**
     * 插入
     */
    fun saveRequestBean(bean: RequestBean) = requestDao.insertBean(bean)

    /**
     * 删除
     */
    fun deleteRequestBean(bean: RequestBean) = requestDao.deleteBean(bean)

    /**
     * 更新
     */
    fun updateRequest(bean: RequestBean) = requestDao.updateBean(bean)
}