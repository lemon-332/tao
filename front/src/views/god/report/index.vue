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
import { godReport } from '@/api/god'

@Component({
  components: {}
})
export default class Report extends Vue {
  private chart: echarts.ECharts | null = null
  private selectedYear = new Date().getFullYear()
  private years: number[] = []
  private reportData: Record<string, number[]> = {}

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
        text: `商品出售情况(${this.selectedYear})`
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: Array.from({ length: 12 }, (_, i) => {
          const date = new Date(this.selectedYear, i)
          return date.toLocaleString('zh-CN', { month: 'long' })
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
    return this.reportData[year] || Array(12).fill(0)
  }

  private async getReportData() {
    const res = await godReport()
    this.reportData = res.data
    this.years = Object.keys(res.data)
      .map(Number)
      .sort((a, b) => b - a)
    this.selectedYear = this.years[0]
    this.updateChart()
  }

  created() {
    this.getReportData()
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
