var theme = 0,
    picture = 0;

function ChangeColour() {

  var body = document.getElementsByTagName("BODY")[0],
      colourButton = document.getElementById("colour-button"),
      picturesButton = document.getElementById("pictures-button");

  if (theme == 0) {
    body.setAttribute("class", "blue");
    colourButton.setAttribute("class", "btn btn-primary");
    picturesButton.setAttribute("class", "btn btn-primary");
    theme = 1;
  }
  else {
    body.setAttribute("class", "green");
    colourButton.setAttribute("class", "btn btn-success");
    picturesButton.setAttribute("class", "btn btn-success");
    theme = 0;
  }
}

function TriStateButton() {

  var canvas = document.getElementById("canvas"),
      context = canvas.getContext("2d"),
      canvasSide = canvas.width;

  context.strokeStyle = "black";
  context.beginPath();

  switch (picture) {
    case 0:
      var houseSide = (2/5) * canvasSide,
          halfCanvasSide = canvasSide / 2;

      context.moveTo(halfCanvasSide - houseSide / 2, canvasSide - houseSide);
      context.lineTo(halfCanvasSide + houseSide / 2, canvasSide - houseSide);
      context.lineTo(halfCanvasSide + houseSide / 2, canvasSide);
      context.lineTo(halfCanvasSide - houseSide / 2, canvasSide);
      context.lineTo(halfCanvasSide - houseSide / 2, canvasSide - houseSide);
      context.lineTo(halfCanvasSide, houseSide * Math.sin( Math.PI / 3 ));
      context.lineTo(halfCanvasSide + houseSide / 2, canvasSide - houseSide);
      context.stroke();

      picture = 1;
      break;
    case 1:
    var sunX = (9/12) * canvasSide,
        sunY = (2/12) * canvasSide,
        sunRadius = (1/15) * canvasSide,
        i;

    context.arc(sunX, sunY, sunRadius, 0, 2 * Math.PI);

    for (i = 0; i < 7; i++) {
      context.moveTo(
        sunX + 1.2*sunRadius * Math.cos( i * 2*Math.PI/7 ),
        sunY + 1.2*sunRadius * Math.sin( i * 2*Math.PI/7 )
      );
      context.lineTo(
        sunX + 2*sunRadius * Math.cos( i * 2*Math.PI/7 ),
        sunY + 2*sunRadius * Math.sin( i * 2*Math.PI/7 )
      );
    }

    context.stroke();
      picture = 2;
      break;
    case 2:
      context.clearRect(0, 0, canvasSide, canvasSide);
      picture = 0;
  }
}

window.addEventListener("load", function() {canvas.height = canvas.width}, false);
