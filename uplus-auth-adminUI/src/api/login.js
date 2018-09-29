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
import request from '@/router/axios'
export const loginByUsername = (username, password, code, randomStr) => {
  var grant_type = 'password'
  var scope = 'read'
  console.log(password)
  return request({
      url: '/uplus_auth/oauth/token',
      headers: {
        // dHJ1c3RlZC1hcHAlM0FzZWNyZXQ= dHJ1c3RlZC1hcHA=
          'Authorization': 'Basic dHJ1c3RlZC1hcHA6c2VjcmV0'//用base64加密
        },
      method: 'post',
      params: { username, password, randomStr, code, grant_type, scope }
    })
}

export const getUserInfo = () => {
  return request({
      url: '/uplus_admin/user/info',
      method: 'get'
    })
}

export const logout = () => {
  return request({
      url: '/uplus_auth/oauth/removeToken',
      method: 'get'
    })
}
