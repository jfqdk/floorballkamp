(function ($) {
  $(function () {

    $('.sidenav').sidenav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function () {
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

var floorball_command = {};
function floorball_command_prepare(command) {
  floorball_command = {
    'action': `${command}`,
    'time': {
      'p': null,
      'm': null,
      's': null
    }
  };
  floorball_log(floorball_command)
}

function floorball_goal_action(event) {
  floorball_command_prepare($(this).attr("data-command"));
  $("#time_period").removeClass("hide");
  $("#time_minute").addClass("hide");
  $("#time_second").addClass("hide");
}

$("#homeScoreAdd").on("click", floorball_goal_action);
$("#awayScoreAdd").on("click", floorball_goal_action);
$("#homeScoreSubtract").on("click", floorball_goal_action);
$("#awayScoreSubtract").on("click", floorball_goal_action);

// onClick for perioden skifter til minuttet
$("#time_period > div > div > a").each(function () {
  $(this).on("click", function (event) {
    $("#time_period").addClass("hide");
    $("#time_minute").removeClass("hide");
    floorball_command.time.p = $(this).attr("data-time");
    floorball_command.time.m = null;
    floorball_command.time.s = null;
    floorball_log(floorball_command)
  });
});

// onClick for minuttet skifter til sekundet
$("#time_minute > div > div > a").each(function () {
  $(this).on("click", function (event) {
    $("#time_minute").addClass("hide");
    $("#time_second").removeClass("hide");
    floorball_command.time.m = $(this).attr("data-time");
    floorball_command.time.s = null;
    floorball_log(floorball_command)
  });
});

// onClick for sekundet gemmer målet
$("#time_second > div > div > a").each(function () {
  $(this).on("click", function (event) {
    $("#time_second").addClass("hide");
    floorball_command.time.s = $(this).attr("data-time");
    floorball_log(floorball_command)
    $.ajax({ url: `g/${floorball_command.action}`, type: "PUT", success: gameUpdate });
  });
});


/*
 * ------- Logging -------
 */
function floorball_log(message) {
  if (true) {
    console.log(message)
  }
}