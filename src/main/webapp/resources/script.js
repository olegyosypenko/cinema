(function (global) {
    if (document.getElementsByClassName("welcome").length === 0 && document.getElementsByClassName("goodbye").length === 0) {
        return;
    }
    if(typeof (global) === "undefined") {
        throw new Error("window is undefined");
    }

    var _hash = "!";
    var noBackPlease = function () {
        global.location.href += "#";

        // making sure we have the fruit available for juice (^__^)
        global.setTimeout(function () {
            global.location.href += "!";
        }, 50);
    };

    global.onhashchange = function () {
        if (global.location.hash !== _hash) {
            global.location.hash = _hash;
        }
    };

    global.onload = function () {
        noBackPlease();

        // disables backspace on page except on input fields and textarea..
        document.body.onkeydown = function (e) {
            var elm = e.target.nodeName.toLowerCase();
            if (e.which === 8 && (elm !== 'input' && elm  !== 'textarea')) {
                e.preventDefault();
            }
            // stopping event bubbling up the DOM tree..
            e.stopPropagation();
        };
    }

})(window);

function init(parameters, lang, price) {
    if (parameters === undefined) return;
    var hall = document.getElementById("hall");
    for (var i = 0; i < parameters.taken.length; i++) {
        var html = "<div>";
        for (var j = 0; j < parameters.taken[i].length; j++) {
            if (lang === "uk") {
                if (parameters.taken[i][j] === 1) {
                    html += "<div class='taken seat hint hint--top' data-hint='Занято'></div>"
                } else {
                    html += "<div class='free seat hint hint--top' data-hint='Ряд: " + (i + 1) + " Місце: " + (j + 1) + " Ціна: " + price + " грн.' data-seat='" + (j + 1) + "' data-row='" + (i + 1) + "' data-price=" + price + "></div>"
                }
            } else {
                if (parameters.taken[i][j] === 1) {
                    html += "<div class='taken seat hint hint--top' data-hint='Taken'></div>"
                } else {
                    html += "<div class='free seat hint hint--top' data-hint='Row: " + (i + 1) + " Seat: " + (j + 1) + " Price: " + price + " uah.' data-seat='" + (j + 1) + "' data-row='" + (i + 1) + "' data-price=" + price + "></div>"
                }
            }
        }
        html += "</div>";
        hall.innerHTML += html;
    }
    addListeners();
}

function addListeners() {
    var seats = document.getElementsByClassName("free");
    for (var i = 0; i < seats.length; i++) {
        seats[i].addEventListener("mouseover", function() {
            this.classList.toggle("darkblue");
        })
        seats[i].addEventListener("mouseout", function() {
            this.classList.toggle("darkblue");
        })
        seats[i].addEventListener("click", function() {
            var seat = this.getAttribute("data-seat");
            var row = this.getAttribute("data-row");
            var seancePrice = this.getAttribute("data-price");
            if (this.classList.contains("orange")) {
                var seatId = "seat" + seat;
                var rowId = "row" + row;
                document.getElementById(seatId).outerHTML = "";
                document.getElementById(rowId).outerHTML = "";
                var price = parseInt(document.getElementById("whole-price").innerText, 10);
                price -= parseInt(seancePrice);
                document.getElementById("whole-price").innerText = price.toString();

            } else {
                var form = document.getElementById("buy-tickets-form");
                var html1 = "<input type=\"hidden\" id='seat" + seat + "' name=\"seat\" value=" + seat + ">"
                var html2 = "<input type=\"hidden\" id='row" + row + "' name=\"row\" value=" + row + ">"
                console.log("seat: " + seat + "row: " + row);
                form.innerHTML = html1 + html2 + form.innerHTML;
                var price = parseInt(document.getElementById("whole-price").innerText, 10);
                price += parseInt(seancePrice);
                document.getElementById("whole-price").innerText = price.toString();
            }
            this.classList.toggle("orange");
        })
    }
}