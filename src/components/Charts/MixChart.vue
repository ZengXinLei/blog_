<template>
  <div :id="id" :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'
  import resize from './mixins/resize'
  import {getTimeCount} from "@/api/draft";
  import {computedTime} from "@/utils/parseTime";
  import {getArticleTimeCount} from "@/api/article";

  export default {
    mixins: [resize],
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      id: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '200px'
      },
      height: {
        type: String,
        default: '200px'
      }
    },
    data() {
      return {
        chart: null,
        draft:[],
        article:[],
        chartInfo:[]
      }
    },
    mounted() {
      getTimeCount().then(res => {
        this.draft=computedTime(res.data)

      }).then(()=>{
        getArticleTimeCount().then(res=>{
          this.article=computedTime(res.data)
        }).then(()=>{

          let o={
            l:this.draft.length>this.article.length?this.draft.length:this.article.length,
            o1:this.draft.length>this.article.length?this.draft:this.article,
            o2:this.draft.length>this.article.length?this.article:this.draft,
            o3:[]
          }
          for (let i = 0; i < o.l; i++) {
            if(o.o2.length!==0&&o.o1[i]['time']===o.o2[0]['time']){
              o.o3.push(o.o2[0])
              o.o2.splice(0,1)
            }else {
              o.o3.push({
                time:o.o1[i]['time'],
                count:0
              })
            }
          }
          for (let i = 0; i < o.o3.length; i++) {
            o.o2.push(o.o3[i])
          }

        }).then(()=>{
          this.initChart()
        })
      })

    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      this.chart.dispose()
      this.chart = null
    },
    methods: {
      initChart() {
        let a=this.article
        let d=this.draft
        this.chart = echarts.init(document.getElementById(this.id))
        const xData = (function(){
          const data = []
          for (let i = 0; i < a.length; i++) {
            data.push(a[i]['time'])
          }
          return data
        }())
        const article=(function() {
          let data=[]
          for (let i = 0; i < a.length; i++) {
            data.push(a[i]['count'])
          }
          return data
        }())

        const draft=(function() {
          let data=[]

          for (let i = 0; i <d.length; i++) {
            data.push(d[i]['count'])
          }
          return data
        }())

        this.chart.setOption({
          backgroundColor: '#344b58',
          title: {
            text: '博客数据',
            x: '20',
            top: '20',
            textStyle: {
              color: '#fff',
              fontSize: '22'
            },
            subtextStyle: {
              color: '#90979c',
              fontSize: '16'
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              textStyle: {
                color: '#fff'
              }
            }
          },
          grid: {
            left: '5%',
            right: '5%',
            borderWidth: 0,
            top: 150,
            bottom: 95,
            textStyle: {
              color: '#fff'
            }
          },
          legend: {
            x: '5%',
            top: '10%',
            textStyle: {
              color: '#90979c'
            },
            data: ['已发布', '未发布']
          },
          calculable: true,
          xAxis: [{
            type: 'category',
            axisLine: {
              lineStyle: {
                color: '#90979c'
              }
            },
            splitLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitArea: {
              show: false
            },
            axisLabel: {
              interval: 0

            },
            data: xData
          }],
          yAxis: [{
            type: 'value',
            splitLine: {
              show: false
            },
            axisLine: {
              lineStyle: {
                color: '#90979c'
              }
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              interval: 0
            },
            splitArea: {
              show: false
            }
          }],
          dataZoom: [{
            show: true,
            height: 30,
            xAxisIndex: [
              0
            ],
            bottom: 30,
            start: 10,
            end: 80,
            handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'

            },
            textStyle: {
              color: '#fff'
            },
            borderColor: '#90979c'

          }, {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }],
          series: [
            {
            name: '已发布',
            type: 'bar',
            stack: 'total',
            barMaxWidth: 35,
            barGap: '10%',
            itemStyle: {
              normal: {
                color: 'rgba(255,144,128,1)',
                label: {
                  show: true,
                  textStyle: {
                    color: '#fff'
                  },
                  position: 'insideTop',
                  formatter(p) {
                    return p.value > 0 ? p.value : ''
                  }
                }
              }
            },
            data: article
          },

            {
              name: '未发布',
              type: 'bar',
              stack: 'total',
              itemStyle: {
                normal: {
                  color: 'rgba(0,191,183,1)',
                  barBorderRadius: 0,
                  label: {
                    show: true,
                    position: 'top',
                    formatter(p) {
                      return p.value > 0 ? p.value : ''
                    }
                  }
                }
              },
              data: draft
            },
            // {
            //   name: '访问量',
            //   type: 'line',
            //   stack: 'total',
            //   symbolSize: 10,
            //   symbol: 'circle',
            //   itemStyle: {
            //     normal: {
            //       color: 'rgba(252,230,48,1)',
            //       barBorderRadius: 0,
            //       label: {
            //         show: true,
            //         position: 'top',
            //         formatter(p) {
            //           return p.value > 0 ? p.value : ''
            //         }
            //       }
            //     }
            //   },
            //   data: [
            //     1036,
            //     3693,
            //     2962,
            //     3810,
            //     2519,
            //     1915,
            //     1748,
            //     4675,
            //     6209,
            //     4323,
            //     2865,
            //     4298
            //   ]
            // }
          ]
        })
      }
    }
  }
</script>
