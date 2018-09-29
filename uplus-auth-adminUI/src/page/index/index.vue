<!--
  -    Copyright (c) 2018-2025, lengleng All rights reserved.
  -
  - Redistribution and use in source and binary forms, with or without
  - modification, are permitted provided that the following conditions are met:
  -
  - Redistributions of source code must retain the above copyright notice,
  - this list of conditions and the following disclaimer.
  - Redistributions in binary form must reproduce the above copyright
  - notice, this list of conditions and the following disclaimer in the
  - documentation and/or other materials provided with the distribution.
  - Neither the name of the pig4cloud.com developer nor the names of its
  - contributors may be used to endorse or promote products derived from
  - this software without specific prior written permission.
  - Author: lengleng (wangiegie@gmail.com)
  -->

<template>
  <el-container class="avue-contail">
    <el-aside :style="{width: isCollapse ? asideWidthCollapse : asideWidth}">
      <!-- 左侧导航栏 -->
      <sidebar class="avue-sidebar"></sidebar>
    </el-aside>
    <el-container>
      <el-header height="auto"
                 class="avue-tabs">
        <!-- 顶部导航栏 -->
        <top />
        <!-- 顶部标签卡 -->
        <tags />
      </el-header>
      <el-main class="avue-main">
        <!-- 主体视图层 -->
        <keep-alive>
          <transition name="fade-transverse">
            <router-view class="avue-view"
                         v-if="$route.meta.keepAlive" />
          </transition>
        </keep-alive>
        <transition name="fade-transverse">
          <router-view class="avue-view"
                       v-if="!$route.meta.keepAlive" />
        </transition>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { mapGetters } from 'vuex'
import tags from './tags'
import top from './top/'
import sidebar from './sidebar/'
import { validatenull } from '@/util/validate'
import { calcDate } from '@/util/date.js'
import { setStore, getStore } from '@/util/store.js'
export default {
  components: {
    top,
    tags,
    sidebar
  },
  name: 'index',
  data() {
    return {
      // [侧边栏宽度] 正常状态
      asideWidth: '230px',
      // [侧边栏宽度] 折叠状态
      asideWidthCollapse: '65px',
      // 刷新token锁
      refreshLock: false
    }
  },
  created() {
  },
  mounted() { },
  computed: mapGetters(['isLock', 'isCollapse', 'website']),
  props: [],
  methods: {
  }
}
</script>

<style lang="scss" scoped>
.avue-contail {
  height: 100%;
}
.avue-sidebar {
  height: 100%;
}
.avue-tabs {
  padding: 0;
}
.avue-main {
  position: relative;
  padding: 0;
}
.avue-view {
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
}
</style>
