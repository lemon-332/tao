<template>
  <div class="report">
    <div id="growth-chart" style="width: 100%; height: 400px"></div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import * as echarts from 'echarts'

@Component({
  components: {}
})
export default class Report extends Vue {
  private chart: echarts.ECharts | null = null

  mounted() {
    this.initChart()
  }

  beforeDestroy() {
    if (this.chart) {
      this.chart.dispose()
    }
  }

  private initChart() {
    const chartDom = document.getElementById('growth-chart')
    if (!chartDom) return

    this.chart = echarts.init(chartDom)
    const option = {
      title: {
        text: 'Product Growth Trend'
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: Array.from({ length: 12 }, (_, i) => {
          const date = new Date(2024, i)
          return date.toLocaleString('en', { month: 'short' })
        })
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: 'Product Count',
          type: 'line',
          data: [150, 230, 224, 218, 335, 400, 0, 0, 0, 0, 0, 0],
          smooth: true
        }
      ]
    }

    this.chart.setOption(option)
  }
}
</script>

<style lang="scss" scoped>
.report {
  padding: 20px;
}
</style>
