$(document).ready(function() {
	$(document).on("change", "select.set-kategorie", function() {
		$.get(
			"ZimmerServlet",
			{
				action: "set",
				zimmer: $(this).attr("data-zimmer"),
				kategorie: $(this).val(),
				hotel: $("select.set-hotel").val()
			},
			function(response) {
				$("textarea").text(response);
			}
		);
	});
	
	$("select.set-hotel").change(function() {
		$("#managerooms").load(
			"zimmerverwalten.jsp?hotel=" + $(this).val() +" #managerooms");
	});
});