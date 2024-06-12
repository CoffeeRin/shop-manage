<template>
    <el-row>
      <el-col :span="12">
        <el-card>
          <div ref="pieChart" style="width: 850px; height: 850px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div ref="barChart" style="width: 850px; height: 850px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </template>
  
  <script>
  import axios from 'axios';
  import * as echarts from 'echarts';
  
  export default {
    data() {
      return {
        chartData: [],
      };
    },
    created() {
      // 在组件创建时立即获取数据
      this.fetchData();
    },
    methods: {
      async fetchData() {
        try {
          // 调用后端接口获取数据
          const response = await axios.get('http://localhost:8089/api/category-count');
          this.chartData = response.data;
          // 数据加载完成后渲染图表
          this.renderPieChart();
          this.renderBarChart();
        } catch (error) {
          console.error('Failed to fetch data:', error);
        }
      },
      renderPieChart() {
        const chartDom = this.$refs.pieChart;
        const myChart = echarts.init(chartDom);
        const option = {
          title: {
            text: '商品类别数量分布',
            left: 'center',
            textStyle: {
              fontSize: 24 // 调整字体大小
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)',
          },
          legend: {
            orient: 'vertical',
            left: 10,
            data: this.chartData.map(item => item.name),
          },
          series: [
            {
              name: '商品数量',
              type: 'pie',
              radius: '50%',
              center: ['50%', '60%'],
              avoidLabelOverlap: true,
              label: {
                show: true,
                fontSize: '20',
                position: 'outside',
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '20',
                  fontWeight: 'bold',
                },
              },
              labelLine: {
                show: true,
              },
              data: this.chartData.map(item => ({ value: item.product_count, name: item.name })),
            },
          ],
        };
        myChart.setOption(option);
      },
      renderBarChart() {
        const chartDom = this.$refs.barChart;
        const myChart = echarts.init(chartDom);
        const option = {
          title: {
            text: '商品类别数量分布',
            left: 'center',
            textStyle: {
              fontSize: 24 // 调整字体大小
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            data: ['商品数量'],
            left: 'right'
          },
          xAxis: {
            type: 'category',
            data: this.chartData.map(item => item.name),
            axisLabel: {
            }
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              fontSize: 16 // 调整字体大小
            }
          },
          series: [
            {
              name: '商品数量',
              type: 'bar',
              data: this.chartData.map(item => item.product_count),
              label: {
                show: true,
                fontSize: 16 // 调整字体大小
              }
            }
          ]
        };
        myChart.setOption(option);
      }
    },
  };
  </script>
  
  <style scoped>
  .el-card {
    margin: 20px;
  }
  </style>
  