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

// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')
var baseUrl = 'http://127.0.0.1:9999';


module.exports = {
    build: {
        env: require('./prod.env'),
        index: path.resolve(__dirname, '../dist/index.html'),
        assetsRoot: path.resolve(__dirname, '../dist'),
        assetsSubDirectory: 'static',
        assetsPublicPath: './',
        productionSourceMap: false,
        
        productionGzip: false,
        productionGzipExtensions: ['js', 'css'],
       
        bundleAnalyzerReport: process.env.npm_config_report
    },
    dev: {
        env: require('./dev.env'),
        port: 8000,
        autoOpenBrowser: true,
        assetsSubDirectory: 'static',
        host: 'localhost',
        assetsPublicPath: '/',
        proxyTable: {
             
            '/uplus_admin': {
                target: 'http://127.0.0.1:8085',
                changeOrigin: true,
                pathRewrite: {
                    '/uplus_admin': '/uplus_admin'
                }
            }
            //,
            // '/code': {
            //     target: 'http://127.0.0.1:8087',
            //     changeOrigin: true,
            //     pathRewrite: {
            //         '/uplus_admin': '/uplus_admin'
            //     }
            // }
        },
        cssSourceMap: false
    }
}