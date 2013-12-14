<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>
  <title>UI Kategorien verwalten</title>
  <meta name="description" content="Little Sharky Fish - SWE Projekt Hotelmanagement">
  <meta charset="UTF-8">
</head>

<body>

	<h1>Kategorien verwalten</h1>

	<!--  <p>Kategorie:<br>
  		<select>
    		<option>Presidential Suite</option>
    		<option>Economy Room </option>
  		</select> 
	</p> -->
	
	<form action="http://localhost:8080/LittleSharkyFish/KategorieManagement" method="get">
	
		<table>
			<tr>
    			<td>Hotel:</td>
    			<td><input type="text" name="hotel"></td>
  			</tr>
  			<tr>
    			<td>Name:</td>
    			<td><input type="text" name="name"></td>
  			</tr>
  			<tr>
    			<td>Preis:</td>
    			<td><input type="text" name="preis" value=""></td>
  			</tr>
  			<tr>
    			<td>Neuer Name:</td>
    			<td><input type="text" name="newname"></td>
  			</tr>
		</table>

		<div>Ausstattung:<br>
  			<textarea cols="50" rows="5" name="ausstattung"></textarea>
		</div>

	
    	<input type="submit" name="action" value="create">
		<input type="submit" name="action" value="edit">
		<input type="submit" name="action" value="delete">
	
	</form>

</body>

</html>