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
			<a class="navbar-brand" href="allekategorien.jsp">Little Sharky Fish</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Gäste <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="allekategorien.jsp">Zimmer buchen</a></li>
						<li><a href="buchungstornieren.jsp">Buchung stornieren</a></li>
						<li><a href="#">Services buchen</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Hoteliers <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="kategorienverwalten.jsp">Kategorien verwalten</a></li>
						<li><a href="zimmerverwalten.jsp">Zimmer verwalten</a></li>
						<li><a href="buchungenverwalten.jsp">Buchungen verwalten</a></li>
						<li><a href="servicesverwalten.jsp">Services verwalten</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Analysten <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="hotelsvergleichen.jsp">Hotels vergleichen</a></li>
						<li><a href="statistikerstellen.jsp">Statistik erstellen</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Debugging <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="CreateTestData" target="_blank">Testdaten erstellen</a></li>
						<li><a href="login.jsp">Login</a></li>
						<li><a href="buchungstest.jsp">Buchungs-Test</a></li>
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