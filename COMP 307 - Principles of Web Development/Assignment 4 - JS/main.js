var theme = 0,
    body = document.getElementsByTagName("BODY")[0],
    button = document.getElementById("button");

function ChangeColour() {
  if (theme == 0) {
    body.setAttribute("class", "blue");
    button.setAttribute("class", "btn btn-primary");
    theme = 1;
  }
  else {
    body.setAttribute("class", "green");
    button.setAttribute("class", "btn btn-success");
    theme = 0;
  }
}
