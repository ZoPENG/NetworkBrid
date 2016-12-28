<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="小云,小云工作室,编程技术,java,web,开源">
<meta name="description" content="小云工作室是一个分享与交流编程技术的开源网站">
<title>小云工作室</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="icon" sizes="any" href="img/logo/logo.svg" />
<link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
</head>
<body onload="window_load()">
	<nav class="navbar navbar-default" id="navbar-main" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="https://www.xiaoyun.studio"> 
			  <img src="img/logo/logo.svg" class="logo">小云工作室 <small> V0.3.0</small>
			</a>
			<button class="navbar-toggle" id="navbar-button" onclick="navbar_button_click()"
				data-target="#navbar" data-toggle="collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="app/index.html">应用</a></li>
				<li><a href="#">介绍</a></li>
				<li><a href="article/index.html">文档</a></li>
				<li><a href="article/sourceTutorial.html">源码</a></li>
				<li><a href="other/feedback.html">留言</a></li>
			</ul>
		</div>
	</nav>
	<div id="map">
		<svg xmlns:xlink="http://www.w3.org/1999/xlink"></svg>
	</div>
	<script src="plugins/d3/d3-4.2.2.min.js"></script>
	<script src="js/home.js"></script>
</body>
</html>