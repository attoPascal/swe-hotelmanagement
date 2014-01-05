<%@ page import="hm.users.AbstractUser" %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Little Sharky Fish</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="allekategorien.jsp">Gäste</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Hoteliers <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="kategorienverwalten.jsp">Kategorien verwalten</a></li>
						<li><a href="zimmerverwalten.jsp">Zimmer verwalten</a></li>
					</ul>
				</li>
				<li><a href="#">Analysten</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Debugging <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="CreateTestData" target="_blank">Testdaten erstellen</a></li>
						<li><a href="login.jsp">Login</a></li>
					</ul>
				</li>
			</ul>
			<p class="navbar-text navbar-right">
				<%
				AbstractUser u = (AbstractUser) session.getAttribute("user");
				if (u != null) {
					out.print(u.getUsername());
					out.print(" <a href='UserServlet?action=logout'>(logout)</a>");
				} else {
					out.print("Nicht eingeloggt");
				}
				%>
			</p>
		</div>
	</div>
</nav>