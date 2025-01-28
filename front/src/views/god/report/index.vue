<template>
  <div class="report">
    <div class="year-selector">
      <el-select v-model="selectedYear" @change="updateChart">
        <el-option
          v-for="year in years"
          :key="year"
          :label="year"
          :value="year"
        />
      </el-select>
    </div>
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
  private selectedYear = new Date().getFullYear()
  private years = Array.from(
    { length: 5 },
    (_, i) => this.selectedYear - i
  )

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
    this.updateChart()
  }

  private updateChart() {
    if (!this.chart) return

    const option = {
      title: {
        text: `Product Growth Trend (${this.selectedYear})`
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: Array.from({ length: 12 }, (_, i) => {
          const date = new Date(this.selectedYear, i)
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
          data: this.getYearData(this.selectedYear),
          smooth: true
        }
      ]
    }

    this.chart.setOption(option)
  }

  private getYearData(year: number): number[] {
    if (year === new Date().getFullYear()) {
      return [150, 230, 224, 218, 335, 400, 0, 0, 0, 0, 0, 0]
    }
    return Array.from({ length: 12 }, () => Math.floor(Math.random() * 500))
  }
}
</script>

<style lang="scss" scoped>
.report {
  padding: 20px;
}

.year-selector {
  margin-bottom: 20px;
}
</style>
