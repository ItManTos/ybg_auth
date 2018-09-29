/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

import {
    validatenull
} from '@/util/validate'
/**
 * 存储localStorage
 */
export const setStore = (params) => {
  const {
            name,
            content,
            type,
            datetime
        } = params
        let obj = {
          dataType: typeof (content),
          content: content,
          type: type,
          datetime: new Date().getTime()
        }
  if (type) window.sessionStorage.setItem(name, JSON.stringify(obj))
        else window.localStorage.setItem(name, JSON.stringify(obj))
    }
    /**
     * 获取localStorage
     */

export const getStore = (params) => {
  const {
            name,
            type,
            debug
        } = params
        let obj = {},
          content
        obj = window.localStorage.getItem(name)
        if (validatenull(obj)) obj = window.sessionStorage.getItem(name)
        if (validatenull(obj)) return
        obj = JSON.parse(obj)
        if (debug) {
          return obj
        }
  if (obj.dataType == 'string') {
          content = obj.content
        } else if (obj.dataType == 'number') {
          content = Number(obj.content)
        } else if (obj.dataType == 'boolean') {
          content = eval(obj.content)
        } else if (obj.dataType == 'object') {
          content = obj.content
        }
  return content
    }
    /**
     * 删除localStorage
     */
export const removeStore = params => {
  const {
        name
    } = params
    window.localStorage.removeItem(name)
    window.sessionStorage.removeItem(name)
}

/**
 * 获取全部localStorage
 */
export const getAllStore = (params) => {
  const list = []
    let {
        type
    } = params
    for (let i = 1; i <= window.sessionStorage.length; i++) {
      if (type) {
          list.push({
              name: window.sessionStorage.key(i),
              content: getStore({
                  name: window.sessionStorage.key(i),
                  type: 'session'
                })
            })
        } else {
          list.push(getStore({
              name: window.localStorage.key(i),
              content: getStore({
                  name: window.localStorage.key(i)
                })
            }))
        }
    }

  return list

}

/**
 * 清空全部localStorage
 */
export const clearStore = (params) => {
  const {
        type
    } = params
    if (type) {
      window.sessionStorage.clear()
        return
    }
  window.localStorage.clear()
}
