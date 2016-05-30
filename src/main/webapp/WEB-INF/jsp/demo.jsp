<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
	<head>
		<title>Booster &mdash; A free HTML5 Template </title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta property="og:title" content=""/>
		<meta property="og:image" content=""/>
		<meta property="og:url" content=""/>
		<meta property="og:site_name" content=""/>
		<meta property="og:description" content=""/>
		<meta name="twitter:title" content="" />
		<meta name="twitter:image" content="" />
		<meta name="twitter:url" content="" />
		<meta name="twitter:card" content="" />
		<link rel="shortcut icon" href="favicon.ico">
		<link href='http://fonts.useso.com/css?family=Roboto:400,300,100,500' rel='stylesheet' type='text/css'>
		<link href='http://fonts.useso.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="css/animate.css">
		<link rel="stylesheet" href="css/icomoon.css">
		<link rel="stylesheet" href="css/owl.carousel.min.css">
		<link rel="stylesheet" href="css/owl.theme.default.min.css">
		<link rel="stylesheet" href="css/magnific-popup.css">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/mycss.css">
		<script src="js/modernizr-2.6.2.min.js"></script>
	</head>
	<body>
		<header id="fh5co-header" role="banner">
			<nav class="navbar navbar-default" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header"> 
					<!-- Mobile Toggle Menu Button -->
					<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle visible-xs-block" data-toggle="collapse" data-target="#fh5co-navbar" aria-expanded="false" aria-controls="navbar"><i></i></a>
					<a class="navbar-brand" href="index">STCS</a>
					</div>
					<div id="fh5co-navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="index"><span>HOME <span class="border"></span></span></a></li>
							<li><a href="algorithm"><span>ALGORITHM <span class="border"></span></span></a></li>
							<li class="active"><a href="demo"><span>DEMO <span class="border"></span></span></a></li>
							<li><a href="contact"><span>CONTACT <span class="border"></span></span></a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<!-- END .header -->
		<div class="seconddiv">
			<div id="contentdiv">
				<img id="loading" src="images/loading.gif" style="position:absolute;top:50%;left:43%;display:none;">
				<label id="tips">文件上传中...</label>
				<input type="text" id="pathtext" class="pathtext"/>
				<button class="white" id="uploadBtn" type="button">上传</button>
			</div>
			<div id="complete">
				<div class="guidediv">
					<div class="space"></div>
					<ul id="main-nav" class="nav nav-tabs nav-stacked">
						<li class="active">
								<a href="">
									<i class="icon-home"></i>
									Cluster Result 		
								</a>
							</li>
							<li>
								<a href="">
									<i class="icon-thermometer"></i>
									Message Info	
								</a>
							</li>
							<li>
								<a href="">
									<i class="icon-shop"></i>
									User Info	
								</a>
							</li>
							<li>
								<a href="">
									<i class="icon-lock"></i>
									KeyWord
									<span class="label label-warning pull-right">5</span>
								</a>
							</li>
							<li>
								<a href="">
									<i class="icon-signal"></i>
									Inner Text	
								</a>
							</li>
							<li>
								<a href="">
									<i class="icon-user"></i>
									User Account							
								</a>
							</li>
							<li>
								<a href="">
									<i class="icon-lock"></i>
									History	
								</a>
							</li>
					</ul>
					<hr>
					<div class="sidebar-extra">
						<p>The only way to get what you really want, is to know what you really want. And the only way to know what you really want, is to know yourself</p>
					</div> <!-- .sidebar-extra -->
				</div>
				<div id="datadiv">
					<h1 class="page-title">
						<i class="icon-home"> Message </i>
					</h1>
					<div class="stat-container">
						<div class="stat-holder">
							<div class="stat">
								<span>27031</span>
								消息数量
							</div>
						</div>
						<div class="stat-holder">
							<div class="stat">
								<span>997</span>
								用户数量
							</div>
						</div>
						<div class="stat-holder">
							<div class="stat">
								<span>2015/01/04</span>
								起始时间
							</div>
						</div>
						<div class="stat-holder">
							<div class="stat">
								<span>2015/04/04</span>
								截止时间
							</div>
						</div>
					</div>
					<div class="widget">
						<div class="widget-header">
							<i class="icon-signal">
								<h3> Cluster</h3>
							</i>
						</div>
						<div id="graph-chart">		
						</div>
					</div>
					<div class="widget">
						<div class="widget-header">
							<i class="icon-flow-line">
								<h3> Record</h3>
							</i>
						</div>
						<div id="line-chart">		
						</div>
					</div>
					<div class="widget">
						<div class="widget-header">
							<i class="icon-users">
								<h3> User</h3>
							</i>
						</div>				
						<div class="widget-content">
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>ID</th>
										<th>NickName</th>
										<th>Frequency</th>
									</tr>
								</thead>
								<tbody id="tbody">
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- jQuery -->
		<script src="js/echarts/echarts.min.js" type="text/javascript"></script>
		<script src="js/echarts/dataTool.js" type="text/javascript"></script>
		<script src="js/ajaxupload.3.6.js"></script>
		<script src="js/jquery.min.js"></script>
		<!-- jQuery Easing -->
		<script src="js/jquery.easing.1.3.js"></script>
		<!-- Bootstrap -->
		<script src="js/bootstrap.min.js"></script>
		<!-- Owl carousel -->
		<script src="js/owl.carousel.min.js"></script>
		<!-- Waypoints -->
		<script src="js/jquery.waypoints.min.js"></script>
		<!-- Magnific Popup -->
		<script src="js/jquery.magnific-popup.min.js"></script>
		<!-- Main JS -->
		<script src="js/main.js"></script>
		<script>
			var text=document.getElementById("pathtext");
			var uploadBtn=document.getElementById("uploadBtn");
			var loading=document.getElementById("loading");
			var tips=document.getElementById("tips");
			var btn = $('#uploadBtn');
			var clusterchart=echarts.init(document.getElementById("graph-chart"));
			var messagechart=echarts.init(document.getElementById("line-chart"));
			var contentdiv=$('#comtentdiv');
			var completediv=$('#complete');
			var path;
			new AjaxUpload(btn, {
				action:'upload',
				name:'files',
				multiple: false,
				responseType: 'json',
				method:'post',
				onSubmit : function(file, ext) {
					if (ext && /^(txt)$/.test(ext)){
						this.setData({
							'info':'文件类型正确'
						});
					} else {
						alert('文件类型错误', '只能上传类型为：JPG|PNG|JPEG|GIF|BMP 的图片文件!');
						return false;               
					}
					text.value=file;
					this.disable();
					uploadBtn.style.display='none';
					text.style.display='none';
					loading.style.display='block';
					tips.style.display='block';
				},
				onComplete: function(files, response){
					if ('file_exist' == response.result)
						alert('上传文件失败，该文件已上传');
					else {
						path=response.path;
						tips.innerText='正在导入数据库..';
						prehandle();
					} 
				}
			});
			function prehandle(){
				$.ajax({
				     type: 'POST',
				     url: '${pageContext.request.contextPath}/preHandle' ,
				    data: {path:path} ,
				    success: function(data){
				    	tips.innerText='预处理中...';
				    } ,
				    complete:function(data){
				    	tips.innerText='加载文本向量..';
				    	loadVector();
				    },
				    dataType: 'json'
				});
			}
			function loadVector(){
				$.ajax({
				     type: 'POST',
				     url: 'loadVector' ,
				    data: {msg:'loadVector'} ,
				    success: function(data){
				    } ,
				    complete:function(data){
				    	tips.innerText='会话抽取中..';
				    	sessionextract();
				    },
				    dataType: 'json'
				});
			}
			function sessionextract(){
				$.ajax({
				     type: 'POST',
				     url: 'sessionextract' ,
				    data: {msg:'sessionextract'} ,
				    success: function(data){
				    } ,
				    complete:function(data){
				    	tips.innerText='频繁项集挖掘中..';
				    	frequentword();
				    },
				    dataType: 'json'
				});
			}
			function frequentword(){
				$.ajax({
				     type: 'POST',
				     url: 'frequentword' ,
				    data: {msg:'frequentword'} ,
				    success: function(data){
				    } ,
				    complete:function(data){
				    	tips.innerText='频繁词集融合中...';
				    	eliminatefw();
				    },
				    dataType: 'json'
				});
			}
			function eliminatefw(){
				$.ajax({
				     type: 'POST',
				     url: 'eliminatefw' ,
				    data: {msg:'eliminatefw'} ,
				    success: function(data){
				    } ,
				    complete:function(data){
				    	tips.innerText='聚类中...';
				    	sessionkmeans(data.responseText);
				    },
				    dataType: 'json'
				});
			}
			function sessionkmeans(fwcluster){
				$.ajax({
				     type: 'POST',
				     url: 'sessionkmeans' ,
				    data:  {fwcluster:fwcluster},
				    success: function(data){
				    } ,
				    complete:function(data){
				    	tips.innerText='正在生成可视化图表...';
				    	showClusterGraph();
				    	showMessageLine();
				    	showTable();
				    	$("#contentdiv").css("display","none");
				    	$("#complete").css("display","block");
				    },
				    dataType: 'json'
				});
			}
			function showClusterGraph(){
				clusterchart.clear();
				clusterchart.showLoading({text:'loading...'});
				$.getJSON('clusterdata',function(option){
					clusterchart.hideLoading();
				    clusterchart.setOption(option);
				})
			}
			function showMessageLine(){
				messagechart.clear();
				messagechart.showLoading({text:'loading...'});
				var result=$.getJSON('loadmessagestatistics',function(option){
					messagechart.hideLoading();
					messagechart.setOption(option);
				})
			}
			function showTable(){
				var tbody=document.getElementById("tbody");
				$.getJSON('loaduserstatistics',function(json){
					//var json=[{"names":["在路上","叫我无心"],"id":"467845350","frequency":1171},{"names":["财务（叫我小菜）"],"id":"82542813","frequency":633},{"names":[" 王鹏"],"id":"16504917","frequency":472},{"names":["我和另一个小黄人不一样","清风朗月"],"id":"453108684","frequency":438},{"names":["大只虾"],"id":"479644862","frequency":386},{"names":["小雨","870933984"],"id":"870933984","frequency":334},{"names":["✿                       ","审计（叫我老虎）","今晚打老虎"],"id":"498867606","frequency":316},{"names":["财务（叫我妍妍）","卖菜（叫我妍妍）","菜贩（叫我妍妍）"],"id":"654580332","frequency":288},{"names":["会计（叫我湘思）"],"id":"893710188","frequency":249},{"names":["审计（叫我泰勒）"],"id":"837331319","frequency":242}];
					for(var i=0;i<json.length;i++){
						var row=tbody.insertRow();
						var num=row.insertCell();
						num.innerText=i;
						var id=row.insertCell();
						id.innerText=json[i].id;
						var names=row.insertCell();
						names.innerText=json[i].names;
						var frequency=row.insertCell();
						frequency.innerText=json[i].frequency;
					}
				})
			}
			function demotest(){
				$.ajax({
				     type: 'POST',
				     url: 'test' ,
				    data:  {},
				    success: function(data){
				    } ,
				    complete:function(data){
				    	tips.innerText='正在生成可视化图表...';
				    	
				    	$("#contentdiv").css("display","none");
				    	$("#complete").css("display","block");
				    	//showClusterGraph();
				    	showMessageLine();
				    },
				    dataType: 'json'
				});
			}
		</script>
	
	</body>
</html>
