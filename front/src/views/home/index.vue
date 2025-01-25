<template>
  <div class="main">
    <div class="menu">
      <Menu />
    </div>
    <div class="content-wrapper">
      <div class="header">
        <Header />
      </div>
      <div class="content">
        <RouterView></RouterView>
        <div v-if="$route.path === '/'" id="main" class="chart-container"></div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-facing-decorator'

import Header from '@/components/Header.vue'
import Menu from '@/components/Menu.vue'

// 引入 echarts 核心模块，核心模块提供了 echarts 使用必须要的接口。
import * as echarts from 'echarts/core'
// 引入柱状图图表，图表后缀都为 Chart
import { BarChart } from 'echarts/charts'
// 引入标题，提示框，直角坐标系，数据集，内置数据转换器组件，组件后缀都为 Component
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent
} from 'echarts/components'
// 标签自动布局、全局过渡动画等特性
import { LabelLayout, UniversalTransition } from 'echarts/features'
// 引入 Canvas 渲染器，注意引入 CanvasRenderer 或者 SVGRenderer 是必须的一步
import { CanvasRenderer } from 'echarts/renderers'

// 注册必须的组件
echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent,
  BarChart,
  LabelLayout,
  UniversalTransition,
  CanvasRenderer
])

@Component({
  components: {
    Header,
    Menu
  }
})
export default class Home extends Vue {
  private myChart: echarts.ECharts | null = null

  mounted() {
    if (this.$route.path === '/') {
      this.initChart()
    }
  }

  // 监听路由变化
  @Watch('$route.path')
  onRouteChange(newPath: string) {
    if (newPath === '/') {
      // 确保DOM更新后再初始化图表
      this.$nextTick(() => {
        this.initChart()
      })
    } else {
      // 其他路由时销毁图表
      if (this.myChart) {
        this.myChart.dispose()
        this.myChart = null
      }
    }
  }

  // 将图表初始化逻辑抽取为单独的方法
  private initChart() {
    if (this.myChart) {
      this.myChart.dispose()
    }
    this.myChart = echarts.init(document.getElementById('main'))
    this.myChart.setOption({
      title: {
        text: 'ECharts 入门示例'
      },
      tooltip: {},
      xAxis: {
        data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
      },
      yAxis: {},
      series: [
        {
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
        }
      ]
    })
  }

  beforeDestroy() {
    if (this.myChart) {
      this.myChart.dispose()
    }
  }
}
</script>

<style lang="scss" scoped>
.main {
  display: flex;
  height: 100%;
  .menu {
    width: 200px;
    height: 100%;
  }
  .content-wrapper {
    width: 100%;
    .content {
      padding: 24px 0px;
      margin: 10px 10px;
      .chart-container {
        width: 100%;
        height: 400px;
      }
    }
    .header {
      margin: 10px 10px;
    }
  }
}
</style>
