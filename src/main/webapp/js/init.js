(function ($) {
  $(function () {

    $('.sidenav').sidenav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function(){
  $('.modal').modal();
});

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

// One click events
$("#homeScoreAdd").on("click", function (event) {$.ajax({url: "g/homeScoreAdd", type: "PUT", success: gameUpdate});});
$("#awayScoreAdd").on("click", function (event) {$.ajax({url: "g/awayScoreAdd", type: "PUT", success: gameUpdate});});
$("#homeScoreSubtract").on("click", function (event) {$.ajax({url: "g/homeScoreSubtract", type: "PUT", success: gameUpdate});});
$("#awayScoreSubtract").on("click", function (event) {$.ajax({url: "g/awayScoreSubtract", type: "PUT", success: gameUpdate});});

var time_model = {}

$("#time_period > div > div > a").each(function () {
  $(this).on("click", function (event) {
    $("#time_period").addClass("hide");
    $("#time_minute").removeClass("hide");
    time_model.p = $(this).attr("data-time");
    time_model.m = null;
    time_model.s = null;
    console.log(time_model)
  });
});

$("#time_minute > div > div > a").each(function () {
  $(this).on("click", function (event) {
    $("#time_minute").addClass("hide");
    $("#time_second").removeClass("hide");
    time_model.m = $(this).attr("data-time");
    time_model.s = null;
    console.log(time_model)
  });
});

$("#time_second > div > div > a").each(function () {
  $(this).on("click", function (event) {
    $("#time_second").addClass("hide");
    $("#time_period").removeClass("hide");
    time_model.s = $(this).attr("data-time");
    console.log(time_model)
  });
});
