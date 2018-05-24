var setLikeButtonText = function (liked) {
    alert("setting likebuttontext");
    document.getElementById("target").innerHTML = "<br/> poop <br/>";

    var buttonHTML = "<button name='rate' class='table-btn btn-inverse' value='Edit' onclick=\"location.href='/ideas/likevote/${idea.id}';\">";
    console.log(`Button: `,buttonHTML);

    if (liked) {
        console.log(`liked!`,);
    } else {
        console.log(`not liked`,);
    }


};