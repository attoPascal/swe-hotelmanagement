<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<!DOCTYPE html>

<html>
<head>
	<title>Zimmer verwalten</title>
	<meta charset="US-ASCII">
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("select").change(function() {
				$("#response").load(
					"ZimmerManagement?action=set&zimmer=" + $(this).attr("data-zimmer") +
					"&kategorie=" + $(this).val() +
					"&hotel=BigSharkyFish"
				);
			});
		});
	</script>
</head>

<body>

	<h1>Zimmer verwalten</h1>

	<textarea rows="6" cols="50" id="response"></textarea>

	<table>
		<tr>
			<th>Zimmer</th>
			<th>Kategorie</th>
			<th></th>
		</tr>

		<% for (int i = 1; i <= 10; i++) { %>
		<tr>
			<td><%=i%></td>
			<td>
				<select size="1" data-zimmer="<%=i%>">
					<option value="be">Budget Einzel</option>
					<option value="bd">Budget Doppel</option>
					<option value="ar">Apartment Romance</option>
					<option value="af">Apartment Family</option>
					<option value="ps">Presidential Suite</option>
				</select>
			</td>
			<td><input type="button" value="-"></td>
		</tr>
		<% } %>

	</table>

</body>

</html>