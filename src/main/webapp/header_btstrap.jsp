<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="/WorkPlus/js/jquery-3.7.0.js"></script>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>nav/sidebar</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/navbar-fixed/">
<link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sidebars/">

<!-- Bootstrap core CSS -->
<link href="assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/sidebars.css" rel="stylesheet">
<script src="assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/sidebars.js"></script>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body>


	<nav class="navbar navbar-expand-md navbar-dark fixed-top" id="navbar">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Fixed navbar</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					<li class="nav-item"><a class="nav-link disabled">Disabled</a></li>
				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>

	<div class="flex-shrink-0 p-3" id="sidebar" style="width: 270px;">
		<ul class="list-unstyled ps-0" id="sidebar-list">
			<li class="mb-1">
				<button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">Home</button>
				<div class="collapse" id="home-collapse">
					<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						<li><a href="deptAdmin.dt" class="link-dark rounded">테스트1</a></li>
						<li><a href="deptGetChart.dt" class="link-dark rounded">테스트2</a></li>
						<li><a href="#" class="link-dark rounded">Reports</a></li>
					</ul>
				</div>
			</li>
			<li class="mb-1">
				<button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">Dashboard</button>
				<div class="collapse" id="dashboard-collapse">
					<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						<li><a href="#" class="link-dark rounded">Overview</a></li>
						<li><a href="#" class="link-dark rounded">Weekly</a></li>
						<li><a href="#" class="link-dark rounded">Monthly</a></li>
						<li><a href="#" class="link-dark rounded">Annually</a></li>
					</ul>
				</div>
			</li>
			<li class="mb-1">
				<button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">Orders</button>
				<div class="collapse" id="orders-collapse">
					<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						<li><a href="#" class="link-dark rounded">New</a></li>
						<li><a href="#" class="link-dark rounded">Processed</a></li>
						<li><a href="#" class="link-dark rounded">Shipped</a></li>
						<li><a href="#" class="link-dark rounded">Returned</a></li>
					</ul>
				</div>
			</li>
			<li class="border-top my-3"></li>
			<li class="mb-1">
				<button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">Account</button>
				<div class="collapse" id="account-collapse">
					<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						<li><a href="#" class="link-dark rounded">New...</a></li>
						<li><a href="#" class="link-dark rounded">Profile</a></li>
						<li><a href="#" class="link-dark rounded">Settings</a></li>
						<li><a href="#" class="link-dark rounded">Sign out</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>