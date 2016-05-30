<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>hello world</title>
</hear>
<body>
	<div id="main" style="width:1000px;height:700px;margin-top:100px;"></div>
	<script src="js/echarts/echarts.min.js" type="text/javascript"></script>
	<script src="js/echarts/dataTool.js" type="text/javascript"></script>
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		var chart=echarts.init(document.getElementById('main'));
		function loadData(){
			chart.clear();
			chart.showLoading({text:'loading...'});
			$.getJSON('data/webkit-dep.json',function(data){
				chart.hideLoading();
			    option = {
			        legend: {
			            data: ['HTMLElement', 'WebGL']
			        },
			        series: [{
			            type: 'graph',
			            layout: 'force',
			            animation: true,
			            label: {
			                normal: {
			                    position: 'right',
			                    formatter: '{b}'
			                }
			            },
			            draggable: false,
			            data: data.nodes.map(function (node, idx) {
			                node.id = idx;
			                return node;
			            }),
			            roam:true,
			            categories: data.categories,
			            edges: data.links
			        }]
			    };
				//option={"legend": {"data": ["HTMLElement","WebGl","SVG","CSS","Others"],"x": "center","y": "top","borderWidth": 1},"series": [{"layout": "force","roam": true,"draggable": true,"type": "graph","data": [[{"name": "早上","value": 6.37,"category": 0,"symbolSize": 6.37},{"name": "会计","value": 8.78,"category": 3,"symbolSize": 8.78},{"name": "时间","value": 7.07,"category": 3,"symbolSize": 7.07},{"name": "早上好心","value": 5.27,"category": 0,"symbolSize": 5.27},{"name": "公司","value": 8.75,"category": 3,"symbolSize": 8.75},{"name": "朋友人生财务","value": 5.0,"category": 0,"symbolSize": 5.0},{"name": "工资","value": 7.68,"category": 1,"symbolSize": 7.68},{"name": "考试","value": 4.61,"category": 0,"symbolSize": 4.61},{"name": "注册","value": 4.28,"category": 0,"symbolSize": 4.28},{"name": "请问","value": 7.98,"category": 3,"symbolSize": 7.98},{"name": "工作","value": 8.61,"category": 1,"symbolSize": 8.61},{"name": "聊天","value": 4.16,"category": 0,"symbolSize": 4.16},{"name": "财务","value": 8.54,"category": 3,"symbolSize": 8.54},{"name": "审计","value": 8.01,"category": 1,"symbolSize": 8.01},{"name": "税务","value": 7.16,"category": 3,"symbolSize": 7.16},{"name": "企业","value": 8.38,"category": 3,"symbolSize": 8.38},{"name": "发票","value": 8.01,"category": 3,"symbolSize": 8.01},{"name": "会计师","value": 3.69,"category": 0,"symbolSize": 3.69},{"name": "项目","value": 6.9,"category": 3,"symbolSize": 6.9},{"name": "经验","value": 7.51,"category": 1,"symbolSize": 7.51},{"name": "感觉","value": 7.01,"category": 3,"symbolSize": 7.01},{"name": "报名","value": 3.58,"category": 0,"symbolSize": 3.58},{"name": "北京","value": 8.86,"category": 1,"symbolSize": 8.86},{"name": "朋友","value": 3.35,"category": 0,"symbolSize": 3.35},{"name": "费用","value": 7.21,"category": 3,"symbolSize": 7.21},{"name": "回来","value": 3.35,"category": 0,"symbolSize": 3.35},{"name": "收入领导","value": 3.34,"category": 0,"symbolSize": 3.34},{"name": "企业所得税","value": 14.42,"category": 3,"symbolSize": 14.42},{"name": "工程回来","value": 3.33,"category": 0,"symbolSize": 3.33},{"name": "店经理","value": 3.33,"category": 0,"symbolSize": 3.33},{"name": "喜欢会计","value": 3.33,"category": 0,"symbolSize": 3.33},{"name": "现金流量","value": 9.57,"category": 3,"symbolSize": 9.57},{"name": "爱好平时","value": 3.33,"category": 0,"symbolSize": 3.33},{"name": "电子版盗版","value": 3.33,"category": 0,"symbolSize": 3.33},{"name": "用友凭证","value": 3.33,"category": 0,"symbolSize": 3.33},{"name": "单位","value": 6.9,"category": 3,"symbolSize": 6.9},{"name": "资料","value": 3.22,"category": 0,"symbolSize": 3.22},{"name": "资格","value": 3.22,"category": 0,"symbolSize": 3.22},{"name": "回去","value": 3.22,"category": 0,"symbolSize": 3.22},{"name": "帮忙","value": 3.22,"category": 0,"symbolSize": 3.22},{"name": "分享","value": 3.22,"category": 0,"symbolSize": 3.22},{"name": "请教","value": 7.01,"category": 3,"symbolSize": 7.01},{"name": "真心","value": 3.22,"category": 0,"symbolSize": 3.22},{"name": "增值税","value": 3.12,"category": 0,"symbolSize": 3.12},{"name": "好心","value": 3.03,"category": 0,"symbolSize": 3.03},{"name": "收入","value": 7.34,"category": 3,"symbolSize": 7.34},{"name": "事务所","value": 8.76,"category": 1,"symbolSize": 8.76},{"name": "学习","value": 6.25,"category": 1,"symbolSize": 6.25},{"name": "地方","value": 6.64,"category": 1,"symbolSize": 6.64},{"name": "银行","value": 2.78,"category": 0,"symbolSize": 2.78},{"name": "建议","value": 2.77,"category": 0,"symbolSize": 2.77},{"name": "会计师事务所","value": 14.2,"category": 1,"symbolSize": 14.2},{"name": "会计业务","value": 13.31,"category": 1,"symbolSize": 13.31},{"name": "分所","value": 11.66,"category": 1,"symbolSize": 11.66},{"name": "增值税专用发票","value": 11.59,"category": 3,"symbolSize": 11.59},{"name": "券商公司","value": 8.39,"category": 1,"symbolSize": 8.39},{"name": "财务总监","value": 8.05,"category": 1,"symbolSize": 8.05},{"name": "助理","value": 7.3,"category": 1,"symbolSize": 7.3},{"name": "毕业","value": 7.1,"category": 1,"symbolSize": 7.1},{"name": "辛苦事务所","value": 7.06,"category": 1,"symbolSize": 7.06},{"name": "面试","value": 6.98,"category": 1,"symbolSize": 6.98},{"name": "好像","value": 6.91,"category": 1,"symbolSize": 6.91},{"name": "上班","value": 6.88,"category": 1,"symbolSize": 6.88},{"name": "大学","value": 6.86,"category": 1,"symbolSize": 6.86},{"name": "专业","value": 7.02,"category": 3,"symbolSize": 7.02},{"name": "招聘","value": 6.75,"category": 1,"symbolSize": 6.75},{"name": "实习","value": 6.71,"category": 1,"symbolSize": 6.71},{"name": "英语","value": 6.71,"category": 1,"symbolSize": 6.71},{"name": "基本工资","value": 6.66,"category": 1,"symbolSize": 6.66},{"name": "货币资金","value": 8.05,"category": 3,"symbolSize": 8.05},{"name": "规范财务","value": 6.66,"category": 1,"symbolSize": 6.66},{"name": "工作杭州","value": 6.66,"category": 1,"symbolSize": 6.66},{"name": "懂得工作","value": 6.66,"category": 1,"symbolSize": 6.66},{"name": "水蜜桃北京","value": 6.65,"category": 1,"symbolSize": 6.65},{"name": "国税事务所","value": 6.65,"category": 1,"symbolSize": 6.65},{"name": "事务所妹子","value": 6.65,"category": 1,"symbolSize": 6.65},{"name": "美女好好","value": 6.39,"category": 1,"symbolSize": 6.39},{"name": "经理","value": 6.35,"category": 1,"symbolSize": 6.35},{"name": "电话","value": 6.29,"category": 1,"symbolSize": 6.29},{"name": "谢谢","value": 6.96,"category": 2,"symbolSize": 6.96},{"name": "喜欢","value": 6.24,"category": 1,"symbolSize": 6.24},{"name": "广州","value": 6.22,"category": 1,"symbolSize": 6.22},{"name": "能力","value": 6.12,"category": 1,"symbolSize": 6.12},{"name": "吃饭","value": 6.08,"category": 1,"symbolSize": 6.08},{"name": "老师","value": 6.93,"category": 3,"symbolSize": 6.93},{"name": "学校","value": 6.0,"category": 1,"symbolSize": 6.0},{"name": "东西","value": 6.93,"category": 3,"symbolSize": 6.93},{"name": "谢谢请教","value": 6.65,"category": 2,"symbolSize": 6.65},{"name": "中兴谢谢谢谢","value": 4.99,"category": 2,"symbolSize": 4.99},{"name": "办法谢谢","value": 3.33,"category": 2,"symbolSize": 3.33},{"name": "谢谢老板","value": 3.33,"category": 2,"symbolSize": 3.33},{"name": "吃惊谢谢","value": 3.33,"category": 2,"symbolSize": 3.33},{"name": "发生","value": 2.78,"category": 2,"symbolSize": 2.78},{"name": "回答","value": 2.77,"category": 2,"symbolSize": 2.77},{"name": "合同","value": 2.77,"category": 2,"symbolSize": 2.77},{"name": "办法","value": 2.43,"category": 2,"symbolSize": 2.43},{"name": "建设","value": 2.21,"category": 2,"symbolSize": 2.21},{"name": "管理","value": 7.2,"category": 3,"symbolSize": 7.2},{"name": "工程","value": 2.2,"category": 2,"symbolSize": 2.2},{"name": "希望","value": 2.2,"category": 2,"symbolSize": 2.2},{"name": "资产","value": 6.93,"category": 3,"symbolSize": 6.93},{"name": "基金","value": 2.2,"category": 2,"symbolSize": 2.2},{"name": "理解","value": 2.2,"category": 2,"symbolSize": 2.2},{"name": "感谢","value": 2.2,"category": 2,"symbolSize": 2.2},{"name": "金额","value": 2.2,"category": 2,"symbolSize": 2.2},{"name": "清风","value": 2.2,"category": 2,"symbolSize": 2.2},{"name": "中国","value": 2.03,"category": 4,"symbolSize": 2.03},{"name": "晚上","value": 1.53,"category": 2,"symbolSize": 1.53},{"name": "干部","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "部门","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "资金","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "经营","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "理论","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "报告","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "学生","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "交流","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "价值","value": 1.39,"category": 2,"symbolSize": 1.39},{"name": "损益","value": 11.89,"category": 3,"symbolSize": 11.89},{"name": "银行存款","value": 11.86,"category": 3,"symbolSize": 11.86},{"name": "公司计算","value": 11.48,"category": 3,"symbolSize": 11.48},{"name": "交税","value": 11.24,"category": 3,"symbolSize": 11.24},{"name": "避税","value": 9.98,"category": 3,"symbolSize": 9.98},{"name": "小时会计","value": 9.58,"category": 3,"symbolSize": 9.58},{"name": "报税公司","value": 9.15,"category": 3,"symbolSize": 9.15},{"name": "客服","value": 8.87,"category": 3,"symbolSize": 8.87},{"name": "普通发票","value": 8.6,"category": 3,"symbolSize": 8.6},{"name": "地址工作","value": 8.6,"category": 3,"symbolSize": 8.6},{"name": "项目妈","value": 8.6,"category": 3,"symbolSize": 8.6},{"name": "签合同","value": 8.6,"category": 3,"symbolSize": 8.6},{"name": "税务总局","value": 8.6,"category": 3,"symbolSize": 8.6},{"name": "岗位职责","value": 8.39,"category": 3,"symbolSize": 8.39},{"name": "财务规范","value": 8.21,"category": 3,"symbolSize": 8.21},{"name": "无形资产","value": 8.05,"category": 3,"symbolSize": 8.05},{"name": "全体成员发言","value": 7.92,"category": 3,"symbolSize": 7.92},{"name": "北京户口","value": 7.73,"category": 3,"symbolSize": 7.73},{"name": "请教公告","value": 7.73,"category": 3,"symbolSize": 7.73},{"name": "会计快乐","value": 7.73,"category": 3,"symbolSize": 7.73},{"name": "成本","value": 7.61,"category": 3,"symbolSize": 7.61},{"name": "库存企业","value": 7.49,"category": 3,"symbolSize": 7.49},{"name": "清公司","value": 7.06,"category": 3,"symbolSize": 7.06},{"name": "链接","value": 8.05,"category": 4,"symbolSize": 8.05},{"name": "商务英语","value": 5.27,"category": 4,"symbolSize": 5.27},{"name": "考研","value": 5.27,"category": 4,"symbolSize": 5.27},{"name": "链接培训","value": 5.0,"category": 4,"symbolSize": 5.0},{"name": "辞职基本工资","value": 5.0,"category": 4,"symbolSize": 5.0},{"name": "无心","value": 4.16,"category": 4,"symbolSize": 4.16},{"name": "告诉","value": 3.58,"category": 4,"symbolSize": 3.58},{"name": "朋友圈","value": 3.33,"category": 4,"symbolSize": 3.33},{"name": "信心恩","value": 3.33,"category": 4,"symbolSize": 3.33},{"name": "提成","value": 3.22,"category": 4,"symbolSize": 3.22},{"name": "离职","value": 3.22,"category": 4,"symbolSize": 3.22},{"name": "业务","value": 2.77,"category": 4,"symbolSize": 2.77},{"name": "国企","value": 2.43,"category": 4,"symbolSize": 2.43},{"name": "辞职","value": 2.43,"category": 4,"symbolSize": 2.43},{"name": "计算机","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "估计","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "明白","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "刺激","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "想法","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "鲜肉","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "肥皂","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "指教","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "豆包","value": 2.2,"category": 4,"symbolSize": 2.2},{"name": "坐等","value": 1.83,"category": 4,"symbolSize": 1.83},{"name": "信心","value": 1.74,"category": 4,"symbolSize": 1.74},{"name": "没意思","value": 1.61,"category": 4,"symbolSize": 1.61},{"name": "去年","value": 1.53,"category": 4,"symbolSize": 1.53},{"name": "关系","value": 1.39,"category": 4,"symbolSize": 1.39},{"name": "水平","value": 1.39,"category": 4,"symbolSize": 1.39},{"name": "事业","value": 1.39,"category": 4,"symbolSize": 1.39},{"name": "条件","value": 1.39,"category": 4,"symbolSize": 1.39},{"name": "行业","value": 1.39,"category": 4,"symbolSize": 1.39},{"name": "方法","value": 1.39,"category": 4,"symbolSize": 1.39},{"name": "风险","value": 1.39,"category": 4,"symbolSize": 1.39},{"name": "小时","value": 1.39,"category": 4,"symbolSize": 1.39}]],"animation": false}]};
			    chart.setOption(option);
			})
		}
		loadData();
	</script>
	 <!-- <script type="text/javascript">
		var myChart=echarts.init(document.getElementById('main'));
		myChart.showLoading();
		$.get('data/les-miserables.gexf', function (xml) {
		    myChart.hideLoading();

		    var graph = echarts.dataTool.gexf.parse(xml);
		    var categories = [];
		    for (var i = 0; i < 9; i++) {
		        categories[i] = {
		            name: '类目' + i
		        };
		    }
		    graph.nodes.forEach(function (node) {
		        node.itemStyle = null;
		        node.value = node.symbolSize;
		        node.label = {
		            normal: {
		                show: node.symbolSize > 30
		            }
		        };
		        node.category = node.attributes.modularity_class;
		    });
		    option = {
		        title: {
		            text: 'Les Miserables',
		            subtext: 'Default layout',
		            top: 'bottom',
		            left: 'right'
		        },
		        tooltip: {},
		        legend: [{
		            // selectedMode: 'single',
		            data: categories.map(function (a) {
		                return a.name;
		            })
		        }],
		        animationDuration: 1500,
		        animationEasingUpdate: 'quinticInOut',
		        series : [
		            {
		                name: 'Les Miserables',
		                type: 'graph',
		                layout: 'none',
		                data: graph.nodes,
		                links: graph.links,
		                categories: categories,
		                roam: true,
		                label: {
		                    normal: {
		                        position: 'right',
		                        formatter: '{b}'
		                    }
		                },
		                lineStyle: {
		                    normal: {
		                        curveness: 0.3
		                    }
		                }
		            }
		        ]
		    };

		    myChart.setOption(option);
		}, 'xml');-->
	</script>
</body>
</html>