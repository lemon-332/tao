<template>
  <view class="container">
    <view class="search-wrap">
      <view class="search-input">
        <uni-easyinput
          v-model="searchValue"
          trim="all"
          prefixIcon="search"
          placeholder="请输入搜索内容"
          @iconClick="searchClick"
        ></uni-easyinput>
      </view>
    </view>
    <view class="content-wrap">
      <uni-card
        v-for="(item, index) in godList"
        :title="item.godName"
        :key="index"
        :extra="'￥' + item.godPrice"
      >
        <view class="god-info">
          <text class="uni-body">商品描述：{{ item.godDesc }}</text>
          <img class="god-img" :src="getGodImage(item)" alt="" srcset="" />
        </view>
      </uni-card>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { goodList } from '@/api/good'

const searchValue = ref('')
const godList = ref([])

const getGodImage = (item) => {
  return `http://192.168.0.63:8848/api/god/godDownload/?fileName=${
    item.godImage.split(',')[0]
  }`
}

const fetchGodList = async (searchTerm = '') => {
  const res = await goodList({
    godNameFuzzy: searchTerm,
    startTimeStart: ''
  })
  godList.value = res.list
}

// 搜索按钮点击事件
const searchClick = () => {
  fetchGodList(searchValue.value)
}

// 页面加载时获取数据
onMounted(() => {
  fetchGodList()
})
</script>

<style scoped lang="scss">
.search-input {
  padding: 10px;
  padding-bottom: 0px;
  display: flex;

  ::v-deep .is-input-border {
    border-radius: 35px;
  }
}
.content-wrap {
  padding: 20px;
  padding-top: 10px;

  :deep(.uni-card__content) {
    padding: 0;
  }

  .god-info {
    display: flex;
    flex-direction: column;
    .god-img {
      width: 100%;
      height: 200px;
      object-fit: cover;
      border-radius: 0 0 8px 8px;
      display: block;
    }
  }
}

:deep(.uni-card) {
  margin: 0 0 20px 0 !important;
  overflow: hidden;
}
</style>
