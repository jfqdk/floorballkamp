(function ($) {
  $(function () {

    $('.sidenav').sidenav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

function gameUpdate(state) {
  $("#homeTeamName").html(state.homeTeam.name);
  $("#awayTeamName").html(state.awayTeam.name);
  $("#homeTeamScore").html(state.homeScore);
  $("#awayTeamScore").html(state.awayScore);
}

$.ajax({
  url: "g/load",
  success: gameUpdate
});

$("#homeScoreAdd").on("click", function (event) {
  $.ajax({
    url: "g/homeScoreAdd",
    type: "PUT",
    success: gameUpdate
  });
});

$("#awayScoreAdd").on("click", function (event) {
  $.ajax({
    url: "g/awayScoreAdd",
    type: "PUT",
    success: gameUpdate
  });
});
